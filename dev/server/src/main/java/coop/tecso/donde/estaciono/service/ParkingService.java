package coop.tecso.donde.estaciono.service;

import java.util.List;

import coop.tecso.donde.estaciono.model.Coordinates;
import coop.tecso.donde.estaciono.model.Parking;

/**
 * 
 * @author joel.delvalle
 * 
 */
public interface ParkingService {

	public List<Parking> findAllParking();

	public List<Parking> findParkingByCoordinates(Coordinates coordinates);

}
