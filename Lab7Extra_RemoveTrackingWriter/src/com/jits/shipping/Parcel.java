package com.jits.shipping;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jits.shipping.library.Validator;
import com.jits.shipping.routing.Location;

public class Parcel implements Serializable, Comparable<Parcel> {
	private static final long serialVersionUID = -5916893597051354825L;
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
	private List<Location> route = new LinkedList<Location>();
	private List<String> trackingBarcodes = new ArrayList<String>();
	private Iterator<Location> routeIterator;

	private static final Logger logger = LogManager.getLogger(Parcel.class.getSimpleName());

	public void shipParcel() throws JitsException {

		if (this.routeIterator == null) {
			this.routeIterator = this.route.iterator();
		}

		if (this.routeIterator.hasNext()) {

			this.setLocation(this.routeIterator.next());

			String tracking = this.getLocation().scanParcel(this.getId());
			this.setTrackingBarcodes(tracking);
			
			try(Writer writer = new BufferedWriter(new FileWriter("tracker.txt", true))) {
				writer.write(tracking);
				writer.write("\n");
			} catch (IOException e) {

				e.printStackTrace();

			}
		}
	}

	public String getEntireRoute() {
		String rtn = "";
		for (int i = 0; i < route.size(); i++) {
			rtn += "\t" + route.get(i).location();
		}

		return rtn;
	}

	public String report() {
		return this.getId() + "\t" + this.getShippingType() + "\n";
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

	public String getFromZip() {
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
			logger.error(e.getMessage());
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
		this.location = location;
	}

	public List<Location> getRoute() {
		return route;
	}

	List<String> getTrackingBarcodes() {
		return trackingBarcodes;
	}

	private void setTrackingBarcodes(String bc) {
		this.trackingBarcodes.add(bc);
	}

	@Override
	public int compareTo(Parcel o) {
		int shipping = this.getShippingType().toString().compareTo(o.getShippingType().toString());

		if (id != 0) {
			return shipping;
		} else {
			return Long.compare(this.getId(), o.getId());
		}
	}

}
