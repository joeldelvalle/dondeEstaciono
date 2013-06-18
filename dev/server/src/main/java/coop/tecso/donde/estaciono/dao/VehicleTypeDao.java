package coop.tecso.donde.estaciono.dao;

import coop.tecso.donde.estaciono.dao.common.GenericDao;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.model.VehicleType;

/**
 * 
 * @author joel.delvalle
 * 
 */
public interface VehicleTypeDao extends GenericDao<VehicleType> {

	public Boolean existsInDatabaseToSave(VehicleType vehicleType) throws DondeEstacionoServerException;

	public Boolean existsInDatabaseToUpdateOrDelete(VehicleType vehicleType) throws DondeEstacionoServerException;

}
