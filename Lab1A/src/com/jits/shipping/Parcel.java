package com.jits.shipping;

import java.io.Serializable;

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
	private String other;
	private String hazards;
	
	@Override
	public String toString() {
		return this.getId() + "\t" + this.getShippingType();
	}

	public long getId() {
		return id;
	}

	public void setId(String id) throws JitsException {
		try {
			this.id = Long.parseLong(id);
		} catch(NumberFormatException e) {
			new JitsException("Digits only.", new NumberFormatException());
		}
	}

	public String getShippingType() {
		return shippingType.toString();
	}

	public void setShippingType(String shippingType) throws JitsException {
			try {
				this.shippingType = ShippingMethod.valueOf(shippingType);
			} catch(IllegalArgumentException e) {	
				throw new JitsException("We only ship by ground (GRD), air (AIR) or railway (RAL.)", 
										 new IllegalArgumentException());	
			}
	}

	String getFromZip() {
		return fromZip;
	}

	void setFromZip(String fromZip) {
		try {
			
			if(Validator.checkNumericalString(fromZip, 5)) {
				this.fromZip = fromZip;
			}
			
		} catch (JitsException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	String getToZip() {
		return toZip;
	}

	void setToZip(String toZip) {
		try {
			
			if(Validator.checkNumericalString(toZip, 5)) {
				this.toZip = toZip;
			}
			
		} catch (JitsException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
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
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	String getHazards() {
		return hazards;
	}

	void setHazards(String hazards) {
		this.hazards = hazards;
	}

	String getOther() {
		return other;
	}

	void setOther(String other) {
		this.other = other;
	}

}