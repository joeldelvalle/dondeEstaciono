package coop.tecso.donde.estaciono.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import coop.tecso.donde.estaciono.dao.ParkingRatesDao;
import coop.tecso.donde.estaciono.dao.queries.ParkingRatesQuery;
import coop.tecso.donde.estaciono.dao.utils.DatabaseConnection;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.logger.CustomLogger;
import coop.tecso.donde.estaciono.model.Parking;
import coop.tecso.donde.estaciono.model.ParkingRates;
import coop.tecso.donde.estaciono.utils.DESUtils;

/**
 * 
 * @author german.romero
 * 
 */
@Service
public class ParkingRatesDaoImpl implements ParkingRatesDao {

	private CustomLogger log = new CustomLogger(getClass().getCanonicalName());

	@Override
	public void save(ParkingRates value) throws DondeEstacionoServerException {
		String method = "save";
		log.logStartMethod(method);

		SqlSession session = null;
		try {

			session = DatabaseConnection.getInstance().getSession();

			ParkingRatesQuery query = session.getMapper(ParkingRatesQuery.class);

			query.saveQuery(value);

			session.commit();

		} catch (Exception e) {
			log.logError(method, "error to save parkingRates", e);
			throw new DondeEstacionoServerException("parking.rates.database.error.save", e);

		} finally {

			if (!DESUtils.isNull(session)) {
				session.close();
			}

		}

		log.logEndMethod(method);
	}

	@Override
	public void update(ParkingRates value) throws DondeEstacionoServerException {
		String method = "update";
		log.logStartMethod(method);

		SqlSession session = null;
		try {

			session = DatabaseConnection.getInstance().getSession();

			ParkingRatesQuery query = session.getMapper(ParkingRatesQuery.class);

			query.updateQuery(value);

			session.commit();

		} catch (Exception e) {
			log.logError(method, "error to update parkingRates", e);
			throw new DondeEstacionoServerException("parking.rates.database.error.update", e);

		} finally {

			if (!DESUtils.isNull(session)) {
				session.close();
			}

		}

		log.logEndMethod(method);
	}

	@Override
	public void delete(ParkingRates value) throws DondeEstacionoServerException {
		String method = "delete";
		log.logStartMethod(method);

		SqlSession session = null;
		try {

			session = DatabaseConnection.getInstance().getSession();

			ParkingRatesQuery query = session.getMapper(ParkingRatesQuery.class);

			query.deleteQuery(value);

			session.commit();

		} catch (Exception e) {
			log.logError(method, "error to update parkingRates", e);
			throw new DondeEstacionoServerException("parking.rates.database.error.delete", e);

		} finally {

			if (!DESUtils.isNull(session)) {
				session.close();
			}

		}

		log.logEndMethod(method);
	}

	@Override
	public List<ParkingRates> findAll() throws DondeEstacionoServerException {
		String method = "findAll";
		log.logStartMethod(method);

		SqlSession session = null;
		List<ParkingRates> parkingRatesList = new ArrayList<ParkingRates>();

		try {

			session = DatabaseConnection.getInstance().getSession();

			ParkingRatesQuery query = session.getMapper(ParkingRatesQuery.class);

			parkingRatesList.addAll(query.findAllParkingRatesQuery());

		} catch (Exception e) {
			log.logError(method, "error to find parking rates", e);
			throw new DondeEstacionoServerException("parkingRates.database.error", e);

		} finally {

			if (!DESUtils.isNull(session)) {
				session.close();
			}

		}

		log.logInfo(method, "parkingRatesList size: " + parkingRatesList.size());
		log.logEndMethod(method);
		return parkingRatesList;
	}

	@Override
	public List<ParkingRates> findByParking(String identificationCode) throws DondeEstacionoServerException {
		String method = "findByParking";
		log.logStartMethod(method);

		SqlSession session = null;
		List<ParkingRates> parkingRatesList = new ArrayList<ParkingRates>();

		try {

			session = DatabaseConnection.getInstance().getSession();

			ParkingRatesQuery query = session.getMapper(ParkingRatesQuery.class);

			parkingRatesList = query.findByParkingQuery(identificationCode);

		} catch (Exception e) {
			log.logError(method, "error to find all parkingRates", e);
			throw new DondeEstacionoServerException("ParkingRates.type.database.error.find", e);

		} finally {

			if (!DESUtils.isNull(session)) {
				session.close();
			}

		}

		log.logInfo(method, "vehicleType size found: " + parkingRatesList.size());
		log.logEndMethod(method);

		return parkingRatesList;
	}

	@Override
	public Parking findByIdentificationCode(String identificationCode) throws DondeEstacionoServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean existsInDatabaseToSave(ParkingRates parkingRates) throws DondeEstacionoServerException {
		String method = "existsInDatabaseToSave";
		log.logStartMethod(method);

		SqlSession session = null;
		ParkingRates parkingRatesResult = null;

		try {

			session = DatabaseConnection.getInstance().getSession();

			ParkingRatesQuery query = session.getMapper(ParkingRatesQuery.class);

			parkingRatesResult = query.existsInDatabaseToSaveQuery(parkingRates);

		} catch (Exception e) {
			log.logError(method, "error to validate parkingRates", e);
			throw new DondeEstacionoServerException("parking.rates.database.error.find", e);

		} finally {

			if (!DESUtils.isNull(session)) {
				session.close();
			}

		}

		if (!DESUtils.isNull(parkingRatesResult)) {
			log.logInfo(method, "parkingRates exists in database");
			log.logEndMethod(method);
			return Boolean.TRUE;
		}

		log.logInfo(method, "parkingRates don't exists in database");
		log.logEndMethod(method);
		return Boolean.FALSE;

	}

	@Override
	public Boolean existsInDatabaseToUpdateOrDelete(ParkingRates parkingRates) throws DondeEstacionoServerException {
		String method = "existsInDatabaseToUpdateOrDelete";
		log.logStartMethod(method);

		SqlSession session = null;
		ParkingRates parkingRatesResult = null;

		try {

			session = DatabaseConnection.getInstance().getSession();

			ParkingRatesQuery query = session.getMapper(ParkingRatesQuery.class);

			parkingRatesResult = query.existsInDatabaseToUpdateQuery(parkingRates);

		} catch (Exception e) {
			log.logError(method, "error to validate parkingRates", e);
			throw new DondeEstacionoServerException("parking.rates.database.error.find", e);

		} finally {

			if (!DESUtils.isNull(session)) {
				session.close();
			}

		}

		if (!DESUtils.isNull(parkingRatesResult)) {
			log.logInfo(method, "parkingRates exists in database");
			log.logEndMethod(method);
			return Boolean.TRUE;
		}

		log.logInfo(method, "parkingRates don't exists in database");
		log.logEndMethod(method);
		return Boolean.FALSE;

	}

	@Override
	public ParkingRates findByParkingById(String identificationCode, Long id) throws DondeEstacionoServerException {
		String method = "findByParkingById";
		log.logStartMethod(method);

		SqlSession session = null;
		ParkingRates parkingRates = null;

		try {

			session = DatabaseConnection.getInstance().getSession();

			ParkingRatesQuery query = session.getMapper(ParkingRatesQuery.class);

			parkingRates = query.findByParkingByIdQuery(identificationCode, id);

		} catch (Exception e) {
			log.logError(method, "error to find all parkingRates", e);
			throw new DondeEstacionoServerException("parking.rates.database.error.find", e);

		} finally {

			if (!DESUtils.isNull(session)) {
				session.close();
			}

		}

		if (DESUtils.isNull(parkingRates)) {
			log.logError(method, "parkingRates with ID: " + id + " not found");
			log.logEndMethod(method);
			throw new DondeEstacionoServerException("parking.rates.database.error.find");
		}

		log.logEndMethod(method);
		return parkingRates;
	}

	@Override
	public Boolean existsOtherEqualsInDatabaseBeforeUpdate(ParkingRates parkingRates) throws DondeEstacionoServerException {
		String method = "existsOtherEqualsInDatabaseBeforeUpdate";
		log.logStartMethod(method);

		SqlSession session = null;
		ParkingRates parkingRatesResult = null;

		try {

			session = DatabaseConnection.getInstance().getSession();

			ParkingRatesQuery query = session.getMapper(ParkingRatesQuery.class);

			parkingRatesResult = query.existsOtherEqualsInDatabaseBeforeUpdate(parkingRates);

		} catch (Exception e) {
			log.logError(method, "error to validate parkingRates", e);
			throw new DondeEstacionoServerException("parking.rates.database.error.find", e);

		} finally {

			if (!DESUtils.isNull(session)) {
				session.close();
			}

		}

		if (!DESUtils.isNull(parkingRatesResult)) {
			log.logInfo(method, "parkingRates exists in database");
			log.logEndMethod(method);
			return Boolean.TRUE;
		}

		log.logInfo(method, "parkingRates don't exists in database");
		log.logEndMethod(method);
		return Boolean.FALSE;

	}

}
