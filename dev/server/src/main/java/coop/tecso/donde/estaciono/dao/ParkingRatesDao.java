package coop.tecso.donde.estaciono.dao;

import coop.tecso.donde.estaciono.dao.common.GenericDao;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.model.Parking;
import coop.tecso.donde.estaciono.model.ParkingRates;

/**
 * 
 * @author german.romero
 * 
 */
public interface ParkingRatesDao extends GenericDao<ParkingRates> {
	
	public Parking findByIdentificationCode(String identificationCode) throws DondeEstacionoServerException;
	
	public Boolean existsInDatabaseToSave(ParkingRates parkingRates) throws DondeEstacionoServerException;
	
	public Boolean existsInDatabaseToUpdateOrDelete(ParkingRates parkingRates) throws DondeEstacionoServerException;
	
	public ParkingRates findByParkingById(String identificationCode, Long id) throws DondeEstacionoServerException;

	public Boolean existsOtherEqualsInDatabaseBeforeUpdate(ParkingRates parkingRates) throws DondeEstacionoServerException;

}
