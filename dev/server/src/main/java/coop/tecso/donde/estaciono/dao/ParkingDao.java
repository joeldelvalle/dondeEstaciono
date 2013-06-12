package coop.tecso.donde.estaciono.dao;

import java.util.List;

import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.model.Parking;

/**
 * 
 * @author joel.delvalle
 *
 */
public interface ParkingDao {

	public List<Parking> findAllParking() throws DondeEstacionoServerException;

}
