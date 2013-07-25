package coop.tecso.donde.estaciono.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coop.tecso.donde.estaciono.dao.ParkingDao;
import coop.tecso.donde.estaciono.dao.VehicleTypeDao;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.logger.CustomLogger;
import coop.tecso.donde.estaciono.model.VehicleType;
import coop.tecso.donde.estaciono.service.VehicleTypeService;
import coop.tecso.donde.estaciono.utils.DESConstants;
import coop.tecso.donde.estaciono.utils.DESTime;

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

	@Autowired
	private ParkingDao parkingDao;

	/*
	 * EXECUTION METHODS
	 */

	@Override
	public void save(VehicleType vehicleType) throws DondeEstacionoServerException {
		String method = "save";
		log.logStartMethod(method);

		// se setea el ID en null para evitar posible duplicacion
		vehicleType.setId(null);

		this.vehicleTypeDao.save(vehicleType);

		log.logEndMethod(method);
	}

	@Override
	public void update(VehicleType vehicleType) throws DondeEstacionoServerException {
		String method = "update";
		log.logStartMethod(method);

		// se setea la fecha de estado para refrescar la fecha de cuando se hizo
		// la ultima actualizacion
		vehicleType.setStateDate(DESTime.getToday().getTime());

		this.vehicleTypeDao.update(vehicleType);

		log.logEndMethod(method);
	}

	@Override
	public void delete(VehicleType vehicleType) throws DondeEstacionoServerException {
		String method = "delete";
		log.logStartMethod(method);

		// se setea el estado y la fecha de estado
		vehicleType.setState(DESConstants.Database.States.DISABLED);
		vehicleType.setStateDate(DESTime.getToday().getTime());

		this.vehicleTypeDao.delete(vehicleType);

		log.logEndMethod(method);
	}

	@Override
	public List<VehicleType> findByParking(VehicleType vehicleType) throws DondeEstacionoServerException {
		String method = "findByParking";
		log.logStartMethod(method);

		List<VehicleType> vehicleTypeList = this.vehicleTypeDao.findByParking(vehicleType.getParking().getIdentificationCode());

		log.logInfo(method, "vehicleType size by parking " + vehicleType.getParking().getName() + ": " + vehicleTypeList.size());
		log.logEndMethod(method);
		return vehicleTypeList;
	}

	@Override
	public VehicleType findByParkingById(VehicleType vehicleType) throws DondeEstacionoServerException {
		String method = "findByParkingById";
		log.logStartMethod(method);

		VehicleType vehicleTypeResult = this.vehicleTypeDao.findByParkingById(vehicleType.getParking().getIdentificationCode(), vehicleType.getId());

		log.logEndMethod(method);
		return vehicleTypeResult;
	}

	/*
	 * VALIDATION METHODS
	 */

	@Override
	public void saveValidation(VehicleType vehicleType) throws DondeEstacionoServerException {
		String method = "saveValidation";
		log.logStartMethod(method);

		if (this.haveVehicleEqualsInDatabase(vehicleType)) {
			log.logError(method, "vehicleType exists in database - vehicleType: " + vehicleType.toString());
			throw new DondeEstacionoServerException("vehicle.type.exists");
		}

		log.logEndMethod(method);

	}

	@Override
	public void updateValidation(VehicleType vehicleType) throws DondeEstacionoServerException {
		String method = "updateValidation";
		log.logStartMethod(method);

		if (!this.vehicleTypeDao.existsInDatabaseToUpdateOrDelete(vehicleType)) {
			log.logError(method, "vehicleType does not exists in database - vehicleType: " + vehicleType.toString());
			throw new DondeEstacionoServerException("vehicle.type.not.exists");
		}

		if (this.haveVehicleEqualsInDatabase(vehicleType)) {
			log.logError(method, "vehicleType exists in database - vehicleType: " + vehicleType.toString());
			throw new DondeEstacionoServerException("vehicle.type.exists");
		}

		log.logEndMethod(method);

	}

	@Override
	public void findByParkingValidation(VehicleType vehicleType) throws DondeEstacionoServerException {
		String method = "findByParkingValidation";
		log.logStartMethod(method);

		this.parkingDao.findByIdentificationCode(vehicleType.getParking().getIdentificationCode());

		log.logEndMethod(method);

	}

	@Override
	public void deleteValidation(VehicleType vehicleType) throws DondeEstacionoServerException {
		String method = "deleteValidation";
		log.logStartMethod(method);

		Boolean exists = this.vehicleTypeDao.existsInDatabaseToUpdateOrDelete(vehicleType);

		if (!exists) {
			log.logError(method, "vehicleType does not exists in database - vehicleType: " + vehicleType.toString());
			throw new DondeEstacionoServerException("vehicle.type.not.exists");
		}

		log.logEndMethod(method);

	}

	private Boolean haveVehicleEqualsInDatabase(VehicleType vehicleType) throws DondeEstacionoServerException {
		String method = "haveVehicleEqualsInDatabase";
		log.logStartMethod(method);

		Boolean result = this.vehicleTypeDao.existsInDatabaseToSave(vehicleType);

		log.logInfo(method, "result: " + result);
		log.logEndMethod(method);
		return result;
	}

}
