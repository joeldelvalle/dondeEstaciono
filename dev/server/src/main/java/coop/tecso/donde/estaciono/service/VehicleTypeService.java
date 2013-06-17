package coop.tecso.donde.estaciono.service;

import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.model.VehicleType;

/**
 * 
 * @author joel.delvalle
 * 
 */
public interface VehicleTypeService {

	public void save(VehicleType vehicleType) throws DondeEstacionoServerException;

	public void saveValidation(VehicleType vehicleType) throws DondeEstacionoServerException;

	public void update(VehicleType vehicleType) throws DondeEstacionoServerException;

	public void updateValidation(VehicleType vehicleType) throws DondeEstacionoServerException;

}
