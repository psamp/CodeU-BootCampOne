package com.jits.shipping;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jits.library.Validator;

public class Parcel implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1188081902829370890L;
	private long id;
	private ShippingMethod shippingType;
	private String fromZip;
	private String toZip;
	private double weight;
	private int height;
	private int width;
	private int depth;
	private static final Logger logger = LogManager.getLogger(Parcel.class.getSimpleName());
	


	@Override
	public String toString() {
		return "Parcel [id=" + this.id + ", shippingType=" + this.shippingType + ", fromZip=" + this.fromZip + ", "
				+ "toZip=" + this.toZip
				+ ", weight=" + this.weight + ", height=" + this.height + ", width=" + this.width + ", depth=" + this.depth + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(String id) throws JitsException {
		try {
			this.id = Long.parseLong(id);
		} catch(NumberFormatException e) {
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
			} catch(IllegalArgumentException e) {
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
			
			if(Validator.checkNumericalString(fromZip, 5)) {
				this.fromZip = fromZip;
			}
			
		} catch (JitsException e) {
			Parcel.logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	String getToZip() {
		return toZip;
	}

	void setToZip(String toZip) throws JitsException {
		try {
			
			if(Validator.checkNumericalString(toZip, 5)) {
				this.toZip = toZip;
			}
			
		} catch (JitsException e) {
			Parcel.logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
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

}
