package coop.tecso.donde.estaciono.service;

import java.util.List;

import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.model.Coordinate;
import coop.tecso.donde.estaciono.model.Parking;

/**
 * 
 * @author joel.delvalle
 * 
 */
public interface ParkingService {

	public List<Parking> findAllParking() throws DondeEstacionoServerException;

	public List<Parking> findParkingByCoordinates(Coordinate coordinates) throws DondeEstacionoServerException;

}
