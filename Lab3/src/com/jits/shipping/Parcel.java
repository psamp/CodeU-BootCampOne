package com.jits.shipping;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jits.library.Validator;
import com.jits.routing.Destination;
import com.jits.routing.Location;
import com.jits.routing.Router;

public class Parcel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1188081902829370890L;
	private long id;
	private ShippingMethod shippingType;
	private String fromZip;
	private String toZip;
	private String address;
	private double weight;
	private int height;
	private int width;
	private int depth;
	private static final Logger logger = LogManager.getLogger(Parcel.class.getSimpleName());
	private String location;
	private int routeIndex;

	public void shipParcel() throws JitsException {

		if (this.getRouteSequenceTracker() >= 3) {

			logger.error("Package has arrived at destination.");

		} else {

			ArrayList<Location> locations = new ArrayList<Location>();
			Router router = new Router();

			locations.add(router.distributionCenterLookup(this.getToZip()));
			locations.add(new Destination(this.getAddress(), this.getToZip()));

			this.setLocation(locations.get(this.getRouteSequenceTracker() - 1).location());

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

	public String getLocation() {
		return location;
	}

	public void setLocation(String route) {
		if (this.routeIndex == 0) {
			
			this.location = route;
			this.routeIndex = this.routeIndex + 1;
			
		} else {
			
			this.location += route;
			this.routeIndex = this.routeIndex + 1;
		
		}
	}

	public int getRouteSequenceTracker() {
		return routeIndex;
	}

}
