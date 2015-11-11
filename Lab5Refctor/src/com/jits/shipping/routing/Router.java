package com.jits.shipping.routing;

import com.jits.shipping.JitsException;
import com.jits.shipping.Parcel;
import com.jits.shipping.ShippingMethod;
import com.jits.shipping.Warehouse;

public abstract class Router {

	private static void determineGRDRoute(Parcel parcel) {
		try {
			parcel.getRoute().add(GroundRouter.groundLocationLookup(parcel.getToZip()));
		} catch (JitsException e) {
			e.printStackTrace();
		}
	}

	private static void determineAIRRoute(Parcel parcel) {
		parcel.getRoute().add(AirportRouter.airportLocationLookup(parcel.getFromZip()));
		parcel.getRoute().add(AirportRouter.airportLocationLookup(parcel.getToZip()));
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