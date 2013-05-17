package coop.tecso.donde.estaciono.dao;

import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.model.ParkingHash;

/**
 * 
 * @author joel.delvalle
 * 
 */
public interface ParkingHashDao {

	public void save(String hash);

	public ParkingHash validateHash(String hash) throws DondeEstacionoServerException;

}
