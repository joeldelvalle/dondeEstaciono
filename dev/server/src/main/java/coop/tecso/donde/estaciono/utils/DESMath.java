package coop.tecso.donde.estaciono.utils;

import coop.tecso.donde.estaciono.model.Coordinate;

/**
 * 
 * @author joel.delvalle
 *
 */
public class DESMath {

	public static Double calculateDistanceBetweenToCoordinates(Coordinate coordinatesFrom, Coordinate coordinatesTo) {
		// double earthRadius = 3958.75;//miles
		double earthRadius = 6371;// kilometers
		double dLat = Math.toRadians(coordinatesTo.getLatitude() - coordinatesFrom.getLatitude());
		double dLng = Math.toRadians(coordinatesTo.getLongitude() - coordinatesFrom.getLongitude());
		double sindLat = Math.sin(dLat / 2);
		double sindLng = Math.sin(dLng / 2);
		double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2) * Math.cos(Math.toRadians(coordinatesFrom.getLatitude()))
				* Math.cos(Math.toRadians(coordinatesTo.getLatitude()));
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double dist = earthRadius * c;

		return dist;
	}

}
