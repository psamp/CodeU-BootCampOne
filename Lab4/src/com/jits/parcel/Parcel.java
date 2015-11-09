package com.jits.parcel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jits.library.Validator;
import com.jits.shipping.Destination;
import com.jits.shipping.Location;
import com.jits.shipping.GroundRouter;
import com.jits.shipping.util.TrackingWriter;

public class Parcel implements Serializable, Comparable<Parcel> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1188081902829370890L;
	private ShippingMethod shippingType;
	private long id;
	private String fromZip;
	private String toZip;
	private String address;
	private double weight;
	private int height;
	private int width;
	private int depth;
	private Location location;
	private LinkedList<Location> route = new LinkedList<Location>();
	private ArrayList<String> trackingBarcodes = new ArrayList<String>();
	private Iterator<Location> routeIterator = null;
	
	private static final Logger logger = LogManager.getLogger(Parcel.class.getSimpleName());
	
	void determineRoute() throws JitsException {
		
		GroundRouter router = new GroundRouter();
		this.route = new LinkedList<Location>();

		route.add(new Warehouse());
		route.add(router.distributionCenterLookup(this.getToZip()));
		route.add(new Destination(this.getAddress(), this.getToZip()));
		this.shipParcel();

	}
	
	public void shipParcel() throws JitsException {

		if (this.routeIterator == null) {

			this.routeIterator = this.route.iterator();

		} if(this.routeIterator.hasNext()) {
			
			this.setLocation(this.routeIterator.next());
			
//			String trackingStr = this.getLocation().scanParcel(this.getId());
//			this.setTrackingBarcodes(trackingStr);
			
			TrackingWriter writer = new TrackingWriter("tracker.txt", false);
//			writer.write(trackingStr);
		}
	}

	public long getId() {
		return id;
	}

	public void setId(String id) throws JitsException {

		try {

			this.id = Long.parseLong(id);

		} catch (NumberFormatException e) {

			JitsException d = new JitsException("Digits only.", e);
			Parcel.logger.error(d.getMessage());
			e.printStackTrace();
			throw d;

		}

	}

	public ShippingMethod getShippingType() {
		return shippingType;
	}

	public void setShippingType(String incoming) throws JitsException {
		try {

			this.shippingType = ShippingMethod.valueOf(incoming);

		} catch (IllegalArgumentException e) {

			JitsException d = new JitsException("We only ship by ground (GRD), air (AIR) or railway (RAL.)", e);
			Parcel.logger.error(d.getMessage());
			e.printStackTrace();
			throw d;

		}
	}

	String getFromZip() {
		return fromZip;
	}

	void setFromZip(String fromZip) throws JitsException {
		try {

			if (Validator.checkNumericalString(fromZip, 5)) {
				this.fromZip = fromZip;
			}

		} catch (JitsException e) {

			Parcel.logger.error(e.getMessage());
			e.printStackTrace();
			throw e;

		}
	}

	public String getToZip() {
		return toZip;
	}

	void setToZip(String toZip) throws JitsException {
		try {

			if (Validator.checkNumericalString(toZip, 5)) {
				this.toZip = toZip;
			}

		} catch (JitsException e) {
			e.printStackTrace();
			throw e;

		}
	}

	public String getAddress() {
		return this.address;
	}

	void setAddress(String address) {
		this.address = address;
	}

	int getHeight() {
		return height;
	}

	void setHeight(String height) {

		try {

			this.height = Validator.getInt(height);

		} catch (JitsException e) {

			System.out.println(e.getMessage());
			e.printStackTrace();

		}

	}

	int getWidth() {
		return width;
	}

	void setWidth(String width) {
		try {

			this.width = Validator.getInt(width);

		} catch (JitsException e) {

			System.out.println(e.getMessage());
			e.printStackTrace();

		}
	}

	double getWeight() {
		return weight;
	}

	void setWeight(String weight) {
		this.weight = Math.ceil(Double.parseDouble(weight));
	}

	int getDepth() {
		return depth;
	}

	void setDepth(String depth) {
		try {
			
			this.depth = Validator.getInt(depth);
		
		} catch (JitsException e) {
		
			Parcel.logger.error(e.getMessage());
			e.printStackTrace();
		
		}
	}

	public Location getLocation() {
		return location;
	}

	private void setLocation(Location location) {
			if(this.getShippingType().equals(ShippingMethod.GRD)) {
				this.location = location;
			}
	}
	
	public LinkedList<Location> getRoute() {
		return route;
	}

	ArrayList<String> getTrackingBarcodes() {
		return trackingBarcodes;
	}

	@Override
	public int compareTo(Parcel o) {
		int id = Long.compare(this.getId(), o.getId());
		
		if(id!=0){
			return id;
		} else {
			return this.getShippingType().compareTo(o.getShippingType());
		}
	}

}
