package coop.tecso.donde.estaciono.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coop.tecso.donde.estaciono.dao.VehicleTypeDao;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.model.VehicleType;
import coop.tecso.donde.estaciono.service.VehicleTypeService;

/**
 * 
 * @author joel.delvalle
 * 
 */
@Service
public class VehicleTypeServiceImpl implements VehicleTypeService {

	@Autowired
	private VehicleTypeDao vehicleTypeDao;
	
	@Override
	public void save(VehicleType vehicleType) throws DondeEstacionoServerException {

		this.vehicleTypeDao.save(vehicleType);

	}

}
