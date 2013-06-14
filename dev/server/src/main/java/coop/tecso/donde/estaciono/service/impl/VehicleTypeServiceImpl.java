package coop.tecso.donde.estaciono.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coop.tecso.donde.estaciono.dao.VehicleTypeDao;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.logger.CustomLogger;
import coop.tecso.donde.estaciono.model.VehicleType;
import coop.tecso.donde.estaciono.service.VehicleTypeService;

/**
 * 
 * @author joel.delvalle
 * 
 */
@Service
public class VehicleTypeServiceImpl implements VehicleTypeService {

	private CustomLogger log = new CustomLogger(getClass().getCanonicalName());

	@Autowired
	private VehicleTypeDao vehicleTypeDao;

	@Override
	public void save(VehicleType vehicleType) throws DondeEstacionoServerException {
		String method = "save";
		log.logStartMethod(method);

		this.vehicleTypeDao.save(vehicleType);

		log.logEndMethod(method);
	}

	@Override
	public void validate(VehicleType vehicleType) throws DondeEstacionoServerException {
		String method = "validate";
		log.logStartMethod(method);

		this.vehicleTypeDao.existsInDatabase(vehicleType);

		log.logEndMethod(method);

	}

}
