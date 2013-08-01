package coop.tecso.donde.estaciono.service;

import java.util.List;

import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.model.ParkingRates;

/**
 * 
 * @author german.romero
 * 
 */
public interface ParkingRatesService {

	public List<ParkingRates> findByParking(ParkingRates parkingRates) throws DondeEstacionoServerException;
	
	public ParkingRates findByParkingById(ParkingRates parkingRates) throws DondeEstacionoServerException;
	
	public void findByParkingValidation(ParkingRates parkingRates) throws DondeEstacionoServerException;
	
	public void save(ParkingRates parkingRates) throws DondeEstacionoServerException;

	public void saveValidation(ParkingRates parkingRates) throws DondeEstacionoServerException;

	public void update(ParkingRates parkingRates) throws DondeEstacionoServerException;

	public void updateValidation(ParkingRates parkingRates) throws DondeEstacionoServerException;
	
	public void delete(ParkingRates parkingRates) throws DondeEstacionoServerException;

	public void deleteValidation(ParkingRates parkingRates) throws DondeEstacionoServerException;
	
}
