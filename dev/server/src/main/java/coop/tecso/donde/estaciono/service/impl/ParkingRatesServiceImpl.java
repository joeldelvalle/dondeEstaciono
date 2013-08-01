package coop.tecso.donde.estaciono.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coop.tecso.donde.estaciono.dao.ParkingDao;
import coop.tecso.donde.estaciono.dao.ParkingRatesDao;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.logger.CustomLogger;
import coop.tecso.donde.estaciono.model.ParkingRates;
import coop.tecso.donde.estaciono.service.ParkingRatesService;
import coop.tecso.donde.estaciono.utils.DESConstants;
import coop.tecso.donde.estaciono.utils.DESTime;

/**
 * 
 * @author german.romero
 * 
 */
@Service
public class ParkingRatesServiceImpl implements ParkingRatesService {

	private CustomLogger log = new CustomLogger(getClass().getCanonicalName());

	@Autowired
	private ParkingRatesDao parkingRatesDao;

	@Autowired
	private ParkingDao parkingDao;

	/*
	 * EXECUTION METHODS
	 */
	@Override
	public List<ParkingRates> findByParking(ParkingRates parkingRates) throws DondeEstacionoServerException {
		String method = "findByParking";
		log.logStartMethod(method);

		List<ParkingRates> parkingRatesList = this.parkingRatesDao.findByParking(parkingRates.getParking().getIdentificationCode());

		log.logInfo(method, "parkingRates size by parking " + parkingRates.getParking().getName() + ": " + parkingRatesList.size());
		log.logEndMethod(method);
		return parkingRatesList;
	}

	@Override
	public void findByParkingValidation(ParkingRates parkingRates) throws DondeEstacionoServerException {
		String method = "findByParkingValidation";
		log.logStartMethod(method);

		this.parkingDao.findByIdentificationCode(parkingRates.getParking().getIdentificationCode());

		log.logEndMethod(method);
	}

	@Override
	public void save(ParkingRates parkingRates) throws DondeEstacionoServerException {
		String method = "save";
		log.logStartMethod(method);

		// se setea el ID en null para evitar posible duplicacion
		parkingRates.setId(null);

		this.parkingRatesDao.save(parkingRates);

		log.logEndMethod(method);
	}

	@Override
	public void saveValidation(ParkingRates parkingRates) throws DondeEstacionoServerException {
		String method = "saveValidation";
		log.logStartMethod(method);

		if (this.haveParkingRatesEqualsInDatabase(parkingRates, DESConstants.Action.SAVE)) {
			log.logError(method, "parkingRates exists in database - parkingRates: " + parkingRates.toString());
			throw new DondeEstacionoServerException("parking.rates.exists");
		}

		log.logEndMethod(method);
	}

	@Override
	public void update(ParkingRates parkingRates) throws DondeEstacionoServerException {
		String method = "update";
		log.logStartMethod(method);

		// se setea la fecha de estado para refrescar la fecha de cuando se hizo
		// la ultima actualizacion
		parkingRates.setStateDate(DESTime.getToday().getTime());

		this.parkingRatesDao.update(parkingRates);

		log.logEndMethod(method);
	}

	@Override
	public void updateValidation(ParkingRates parkingRates) throws DondeEstacionoServerException {
		String method = "updateValidation";
		log.logStartMethod(method);

		if (!this.parkingRatesDao.existsInDatabaseToUpdateOrDelete(parkingRates)) {
			log.logError(method, "parkingRates does not exists in database - parkingRates: " + parkingRates.toString());
			throw new DondeEstacionoServerException("parking.rates.not.exists");
		}

		if (this.haveParkingRatesEqualsInDatabase(parkingRates, DESConstants.Action.UPDATE)) {
			log.logError(method, "parkingRates exists in database - parkingRates: " + parkingRates.toString());
			throw new DondeEstacionoServerException("parking.rates.exists");
		}

		log.logEndMethod(method);
	}

	@Override
	public void delete(ParkingRates parkingRates) throws DondeEstacionoServerException {
		String method = "delete";
		log.logStartMethod(method);

		// se setea el estado y la fecha de estado
		parkingRates.setState(DESConstants.Database.States.DISABLED);
		parkingRates.setStateDate(DESTime.getToday().getTime());

		this.parkingRatesDao.delete(parkingRates);

		log.logEndMethod(method);
	}

	@Override
	public void deleteValidation(ParkingRates parkingRates) throws DondeEstacionoServerException {
		String method = "deleteValidation";
		log.logStartMethod(method);

		Boolean exists = this.parkingRatesDao.existsInDatabaseToUpdateOrDelete(parkingRates);

		if (!exists) {
			log.logError(method, "parkingRates does not exists in database - vehicleType: " + parkingRates.toString());
			throw new DondeEstacionoServerException("parking.rates.not.exists");
		}

		log.logEndMethod(method);
	}

	private Boolean haveParkingRatesEqualsInDatabase(ParkingRates parkingRates, DESConstants.Action action) throws DondeEstacionoServerException {
		String method = "haveParkingRatesEqualsInDatabase";
		log.logStartMethod(method);

		Boolean result = null;
		if (DESConstants.Action.SAVE.equals(action)) {
			result = this.parkingRatesDao.existsInDatabaseToSave(parkingRates);

		} else {
			result = this.parkingRatesDao.existsOtherEqualsInDatabaseBeforeUpdate(parkingRates);
		}

		log.logInfo(method, "result: " + result);
		log.logEndMethod(method);
		return result;
	}

	@Override
	public ParkingRates findByParkingById(ParkingRates parkingRates) throws DondeEstacionoServerException {
		String method = "findByParkingById";
		log.logStartMethod(method);

		ParkingRates parkingRatesResult = this.parkingRatesDao.findByParkingById(parkingRates.getParking().getIdentificationCode(),
				parkingRates.getId());

		log.logEndMethod(method);
		return parkingRatesResult;
	}
}
