package com.jits.shipping.routing;

import java.util.ArrayList;
import java.util.Collection;

import com.jits.shipping.JitsException;
import com.jits.shipping.Parcel;

class RailRouter extends Router {
	private static Collection<RailYard> railYardList = new ArrayList<RailYard>();

	public RailRouter() {
		loadRailYards();
	}

	Location locateByZip(String zipcode) {
		RailYard result = null;
		Coordinate zipCoord = Router.zipcodeMap.get(zipcode);

		double shortestDistance = 10000000000.0;
		for (RailYard yard : railYardList) {
			Coordinate railYardCoord = Router.zipcodeMap.get(yard.getZip());
			double distance = zipCoord.distanceTo(railYardCoord);
			if (distance < shortestDistance) {
				shortestDistance = distance;
				result = yard;
			}
		}

		return result;
	}

	void determineRoute(Parcel parcel) throws JitsException {
		parcel.getRoute().add(this.locateByZip(parcel.getFromZip()));
	}

	private static void loadRailYards() {
		railYardList.add(new RailYard("NW01", "Portland", "97212", "OR"));
		railYardList.add(new RailYard("SW02", "Phoenix", "85003", "AZ"));
		railYardList.add(new RailYard("NC03", "Rapid City", "57701", "SD"));
		railYardList.add(new RailYard("SC04", "San Antonio", "78202", "TX"));
		railYardList.add(new RailYard("NE05", "Cleveland", "44102", "OH"));
		railYardList.add(new RailYard("SE06", "Jacksonville", "32202", "FL"));
	}

}
