package coop.tecso.donde.estaciono.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coop.tecso.donde.estaciono.dao.FrequencyTypeDao;
import coop.tecso.donde.estaciono.dao.ParkingDao;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.logger.CustomLogger;
import coop.tecso.donde.estaciono.model.FrequencyType;
import coop.tecso.donde.estaciono.service.FrequencyTypeService;

/**
 * 
 * @author joel.delvalle
 * 
 */
@Service
public class FrequencyTypeServiceImpl implements FrequencyTypeService {

	private CustomLogger log = new CustomLogger(getClass().getCanonicalName());

	@Autowired
	private FrequencyTypeDao frequencyTypeDao;

	@Autowired
	private ParkingDao parkingDao;

	/*
	 * EXECUTION METHODS
	 */

	@Override
	public void save(FrequencyType frequencyType) throws DondeEstacionoServerException {
		String method = "save";
		log.logStartMethod(method);

		// se setea el ID en null para evitar posible duplicacion
		frequencyType.setId(null);

		this.frequencyTypeDao.save(frequencyType);

		log.logEndMethod(method);
	}

	@Override
	public List<FrequencyType> findByParking(FrequencyType frequencyType) throws DondeEstacionoServerException {
		String method = "findByParking";
		log.logStartMethod(method);

		List<FrequencyType> frequencyTypeList = this.frequencyTypeDao.findByParking(frequencyType.getParking().getIdentificationCode());

		log.logInfo(method, "frequencyType size by parking " + frequencyType.getParking().getName() + ": " + frequencyTypeList.size());
		log.logEndMethod(method);
		return frequencyTypeList;
	}

	/*
	 * VALIDATION METHODS
	 */

	/**
	 * para poder grabar un tipo de frecuencia para un estacionamiento se van a
	 * realizar varias validaciones:
	 * 
	 * + no puede haber dos frecuencias con el mismo: description, tipe, time,
	 * timeType y priority
	 * 
	 * + no puede haber dos frecuancias con el mismo: priority
	 * 
	 */
	@Override
	public void saveValidation(FrequencyType frequencyType) throws DondeEstacionoServerException {
		String method = "saveValidation";
		log.logStartMethod(method);

		if (this.haveFrequencyEqualsInDatabase(frequencyType)) {
			log.logError(method, "frequencyType exists in database - frequencyType: " + frequencyType.toString());
			throw new DondeEstacionoServerException("frequency.type.exists");
		}

		if (this.haveFrequencyWithSamePriority(frequencyType)) {
			log.logError(method, "frequencyType with this priority exists in database - frequencyType: " + frequencyType.toString());
			throw new DondeEstacionoServerException("frequency.type.priority.exists");
		}

		log.logEndMethod(method);
	}

	@Override
	public void findByParkingValidation(FrequencyType frequencyType) throws DondeEstacionoServerException {
		String method = "findByParkingValidation";
		log.logStartMethod(method);

		this.parkingDao.findByIdentificationCode(frequencyType.getParking().getIdentificationCode());

		log.logEndMethod(method);

	}
	
	@Override
	public void updateValidation(FrequencyType frequencyType) throws DondeEstacionoServerException {
		String method = "updateValidation";
		log.logStartMethod(method);

		Boolean exists = this.frequencyTypeDao.existsInDatabaseToUpdateOrDelete(frequencyType);

		if (!exists) {
			log.logError(method, "frequencyType does not exists in database - frequencyType: " + frequencyType.toString());
			throw new DondeEstacionoServerException("frequency.type.not.exists");
		}

		log.logEndMethod(method);

	}

	private Boolean haveFrequencyEqualsInDatabase(FrequencyType frequencyType) throws DondeEstacionoServerException {
		String method = "haveFrequencyEqualsInDatabase";
		log.logStartMethod(method);

		Boolean result = this.frequencyTypeDao.existsInDatabaseToSave(frequencyType);

		log.logInfo(method, "result: " + result);
		log.logEndMethod(method);
		return result;
	}

	private Boolean haveFrequencyWithSamePriority(FrequencyType frequencyType) throws DondeEstacionoServerException {
		String method = "haveFrequencyWithSamePriority";
		log.logStartMethod(method);

		Boolean result = this.frequencyTypeDao.existsFrequencyWithSamePriority(frequencyType);

		log.logInfo(method, "result: " + result);
		log.logEndMethod(method);
		return result;
	}

}
