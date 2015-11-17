package com.jits.shipping.routing;

import static java.lang.Math.*;

class DistanceCalculator {
	private static double R = 6371;  // earth's radius in kilometers
	
  // prevent instantiation
  private DistanceCalculator() {}
  
  /**
   * Calculates the distance between two points.
   * @param fromLat latitude in decimal degrees
   * @param fromLng longitude in decimal degrees
   * @param toLat   latitude in decimal degrees
   * @param toLng   longitude in decimal degrees
   * @return distance in miles
   */
	static double calcDistance(double fromLat, double fromLng, double toLat, double toLng) {
		return calcDistanceSphericalLawOfCosine(fromLat, fromLng, toLat, toLng);
  }
	
	static double convertKmToMiles(double km) {
		return km * .62;
	}
	
	static double convertMilesToKm(double miles) {
		return miles / .62;
	}
  
  private static double calcDistanceSphericalLawOfCosine(double fromLat, double fromLng, double toLat, double toLng) {
    double rToLat = toRadians(toLat);
    double rToLng = toRadians(toLng);
    double rFromLat = toRadians(fromLat);
    double rFromLng = toRadians(fromLng);
    return acos(sin(rFromLat) * sin(rToLat) + cos(rFromLat) * cos(rToLat) * cos(rFromLng - rToLng)) * R;
  }
}