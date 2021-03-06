package com.jits.shipping.routing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.jits.shipping.JitsException;
import com.jits.shipping.Parcel;

public class AirportRouter extends Router {
	static Map<String, Coordinate> zipcodeMap = new HashMap<String, Coordinate>(29500);
	private static Collection<Airport> airportList = new ArrayList<Airport>();

	AirportRouter() {
		loadAirports();
	}

	void determineRoute(Parcel parcel) throws JitsException {
		parcel.getRoute().add(this.locateByZip(parcel.getFromZip()));
		parcel.getRoute().add(this.locateByZip(parcel.getToZip()));
	}

	Location locateByZip(String zipcode) {
		Airport result = null;
		Coordinate zipCoord = Router.zipcodeMap.get(zipcode);
		
		double shortestDistance = 10000000000.0;
		for (Airport airport : airportList) {
			Coordinate airportCoord = new Coordinate(airport.getLat(), airport.getLng());
			double distance = zipCoord.distanceTo(airportCoord);
			if (distance < shortestDistance) {
				shortestDistance = distance;
				result = airport;
			}
		}

		return result;
	}

	private static void loadAirports() {
		airportList.add(new Airport("MIA", "Miami International", "Miami", 25, 47, 0, 80, 16, 0));
		airportList.add(new Airport("ORD", "O'Hare International", "Chicago", 41, 58, 43, 87, 54, 17));
		airportList.add(new Airport("JFK", "John F. Kennedy Intl", "New York", 40, 38, 0, 73, 47, 0));
		airportList.add(new Airport("DEN", "Denver International", "Denver", 39, 51, 42, 104, 40, 23));
		airportList.add(new Airport("DFW", "Dallas/Fort Worth Intl", "Dallas", 32, 53, 49, 97, 2, 17));
		airportList.add(new Airport("SEA", "Seattle-Tacoma Intl", "Seattle", 47, 27, 56, 122, 18, 42));
		airportList.add(new Airport("LAX", "Los Angeles International", "Los Angeles", 33, 56, 33, 118, 24, 26));
		airportList.add(new Airport("MSP", "Minneapolis-St. Paul Intl", "Minneapolis", 44, 53, 0, 93, 13, 1));
		airportList.add(new Airport("ATL", "Hartsfield-Jackson Intl", "Atlanta", 33, 38, 12, 84, 25, 41));
		airportList.add(new Airport("PHX", "Sky Harbor International", "Phoenix", 33, 26, 0, 112, 2, 0));
	}
}