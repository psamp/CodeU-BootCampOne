package com.jits.parcel;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jits.library.Validator;
import com.jits.shipping.Destination;
import com.jits.shipping.Location;
import com.jits.shipping.Router;
import com.jits.shipping.util.TrackingWriter;

public class Parcel implements Serializable {
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
	private String routeHistory;
	private int routeIndex;
	private ArrayList<Location> route = new ArrayList<Location>();
	private ArrayList<String> trackingBarcodes = new ArrayList<String>();

	private static final Logger logger = LogManager.getLogger(Parcel.class.getSimpleName());
	
	private void determineRoute() throws JitsException {
		
		route = new ArrayList<Location>();
		Router router = new Router();

		route.add(new Warehouse());
		route.add(router.distributionCenterLookup(this.getToZip()));
		route.add(new Destination(this.getAddress(), this.getToZip()));
		
	}
	
	public void shipParcel() throws JitsException {

		if (this.getRouteIndex() >= 3) {

			logger.error("Package has arrived at destination.");

		} else {
			
			this.determineRoute();
			this.setLocation(route.get(this.getRouteIndex()));
			
			String trackingStr = this.getLocation().scanParcel(this.getId());
			this.setTrackingBarcodes(trackingStr);
			
			TrackingWriter writer = new TrackingWriter("tracker.txt", true);
			writer.write(trackingStr);

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

	public String getShippingType() {
		return shippingType.toString();
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

			Parcel.logger.error(e.getMessage());
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
			if(this.getShippingType() == "GRD") {
				this.location = location;
				this.setRouteHistory(this.getLocation().location());
				++this.routeIndex;
			}
	}

	public int getRouteIndex() {
		return routeIndex;
	}
	
	public String getRouteHistory() {
		return routeHistory;
	}

	private void setRouteHistory(String routeMssg) {
		if(this.getRouteIndex() == 0) {
			this.routeHistory = routeMssg;
		} else {
			this.routeHistory += routeMssg;
		}
	}
	
	ArrayList<String> getTrackingBarcodes() {
		return trackingBarcodes;
	}

	private void setTrackingBarcodes(String bc) {
		this.trackingBarcodes.add(bc);
	}

}
