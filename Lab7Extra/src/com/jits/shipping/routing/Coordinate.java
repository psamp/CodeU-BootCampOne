package com.jits.shipping.routing;

/**
 * A point on the Earth, comprised of latitude and longitude, both 
 * in units of decimal degrees.
 */
class Coordinate {
  private double lat;
  private double lng;
  
  Coordinate(double lat, double lng) {
    this.lat = lat;
    this.lng = lng;
  }
  
  /**
   * Calculates the distance between this Coordinate and the provided one.
   * Distance returned is in miles.
   */
  double distanceTo(Coordinate other) {
    return DistanceCalculator.calcDistance(this.lat, this.lng, other.lat, other.lng);
  }
}