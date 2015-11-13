package com.jits.shipping.routing;

import com.jits.shipping.JitsException;
import com.jits.shipping.Parcel;
import com.jits.shipping.ShippingMethod;
import com.jits.shipping.Warehouse;

public abstract class Router {
	
	public abstract Location locateByZip(String zipcode) throws JitsException;

	private static void determineGRDRoute(Parcel parcel) throws JitsException {
		Router router = new GroundRouter();
		try {
			parcel.getRoute().add(router.locateByZip(parcel.getToZip()));
		} catch (JitsException e) {
			e.printStackTrace();
			throw e;
		}
	}

	private static void determineAIRRoute(Parcel parcel) throws JitsException {
		Router router = new AirportRouter();
		parcel.getRoute().add(router.locateByZip(parcel.getFromZip()));
		parcel.getRoute().add(router.locateByZip(parcel.getToZip()));
	}

	public static void determineRoute(Parcel parcel) throws JitsException {
		parcel.getRoute().add(new Warehouse());

		if (parcel.getShippingType().equals(ShippingMethod.GRD)) {
			Router.determineGRDRoute(parcel);
		} else if (parcel.getShippingType().equals(ShippingMethod.AIR)) {
			Router.determineAIRRoute(parcel);
		}

		parcel.getRoute().add(new Destination(parcel.getAddress(), parcel.getToZip()));

		parcel.shipParcel();

	}

}
