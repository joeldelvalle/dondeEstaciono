package coop.tecso.donde.estaciono.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import coop.tecso.donde.estaciono.dao.VehicleTypeDao;
import coop.tecso.donde.estaciono.dao.queries.VehicleTypeQuery;
import coop.tecso.donde.estaciono.dao.utils.DatabaseConnection;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.logger.CustomLogger;
import coop.tecso.donde.estaciono.model.VehicleType;
import coop.tecso.donde.estaciono.utils.DESUtils;

/**
 * 
 * @author joel.delvalle
 * 
 */
@Service
public class VehicleTypeDaoImpl implements VehicleTypeDao {

	private CustomLogger log = new CustomLogger(getClass().getCanonicalName());

	@Override
	public void save(VehicleType vehicleType) throws DondeEstacionoServerException {
		String method = "save";
		log.logStartMethod(method);

		SqlSession session = null;
		try {

			session = DatabaseConnection.getInstance().getSession();

			VehicleTypeQuery query = session.getMapper(VehicleTypeQuery.class);

			query.saveQuery(vehicleType);

			session.commit();

		} catch (Exception e) {
			log.logError(method, "error to save vehicleType", e);
			throw new DondeEstacionoServerException("vehicle.type.database.error.save", e);

		} finally {

			if (!DESUtils.isNull(session)) {
				session.close();
			}

		}

		log.logEndMethod(method);
	}

	@Override
	public void update(VehicleType vehicleType) throws DondeEstacionoServerException {
		String method = "update";
		log.logStartMethod(method);

		SqlSession session = null;
		try {

			session = DatabaseConnection.getInstance().getSession();

			VehicleTypeQuery query = session.getMapper(VehicleTypeQuery.class);

			query.updateQuery(vehicleType);

			session.commit();

		} catch (Exception e) {
			log.logError(method, "error to update vehicleType", e);
			throw new DondeEstacionoServerException("vehicle.type.database.error.update", e);

		} finally {

			if (!DESUtils.isNull(session)) {
				session.close();
			}

		}

		log.logEndMethod(method);
	}

	@Override
	public void delete(VehicleType vehicleType) throws DondeEstacionoServerException {
		String method = "delete";
		log.logStartMethod(method);

		SqlSession session = null;
		try {

			session = DatabaseConnection.getInstance().getSession();

			VehicleTypeQuery query = session.getMapper(VehicleTypeQuery.class);

			query.deleteQuery(vehicleType);

			session.commit();

		} catch (Exception e) {
			log.logError(method, "error to update vehicleType", e);
			throw new DondeEstacionoServerException("vehicle.type.database.error.delete", e);

		} finally {

			if (!DESUtils.isNull(session)) {
				session.close();
			}

		}

		log.logEndMethod(method);

	}

	@Override
	public List<VehicleType> findAll() throws DondeEstacionoServerException {
		String method = "findAll";
		log.logStartMethod(method);

		SqlSession session = null;
		List<VehicleType> vehicleTypeList = new ArrayList<VehicleType>();

		try {

			session = DatabaseConnection.getInstance().getSession();

			VehicleTypeQuery query = session.getMapper(VehicleTypeQuery.class);

			vehicleTypeList = query.findAllVehicleTypeQuery();

		} catch (Exception e) {
			log.logError(method, "error to find all vehicleType", e);
			throw new DondeEstacionoServerException("vehicle.type.database.error.find", e);

		} finally {

			if (!DESUtils.isNull(session)) {
				session.close();
			}

		}

		log.logInfo(method, "vehicleType size found: " + vehicleTypeList.size());
		log.logEndMethod(method);

		return vehicleTypeList;
	}

	@Override
	public List<VehicleType> findByParking(String identificationCode) throws DondeEstacionoServerException {
		String method = "findByParking";
		log.logStartMethod(method);

		SqlSession session = null;
		List<VehicleType> vehicleTypeList = new ArrayList<VehicleType>();

		try {

			session = DatabaseConnection.getInstance().getSession();

			VehicleTypeQuery query = session.getMapper(VehicleTypeQuery.class);

			vehicleTypeList = query.findByParkingQuery(identificationCode);

		} catch (Exception e) {
			log.logError(method, "error to find all vehicleType", e);
			throw new DondeEstacionoServerException("vehicle.type.database.error.find", e);

		} finally {

			if (!DESUtils.isNull(session)) {
				session.close();
			}

		}

		log.logInfo(method, "vehicleType size found: " + vehicleTypeList.size());
		log.logEndMethod(method);

		return vehicleTypeList;
	}

	@Override
	public VehicleType findByParkingById(String identificationCode, Long id) throws DondeEstacionoServerException {
		String method = "findByParkingById";
		log.logStartMethod(method);

		SqlSession session = null;
		VehicleType vehicleType = null;

		try {

			session = DatabaseConnection.getInstance().getSession();

			VehicleTypeQuery query = session.getMapper(VehicleTypeQuery.class);

			vehicleType = query.findByParkingByIdQuery(identificationCode, id);

		} catch (Exception e) {
			log.logError(method, "error to find all vehicleType", e);
			throw new DondeEstacionoServerException("vehicle.type.database.error.find", e);

		} finally {

			if (!DESUtils.isNull(session)) {
				session.close();
			}

		}

		if (DESUtils.isNull(vehicleType)) {
			log.logError(method, "vehicleType with ID: " + id + " not found");
			log.logEndMethod(method);
			throw new DondeEstacionoServerException("vehicle.type.database.error.find");
		}

		log.logEndMethod(method);
		return vehicleType;
	}

	@Override
	public Boolean existsInDatabaseToSave(VehicleType vehicleType) throws DondeEstacionoServerException {
		String method = "existsInDatabaseToSave";
		log.logStartMethod(method);

		SqlSession session = null;
		VehicleType vehicleTypeResult = null;

		try {

			session = DatabaseConnection.getInstance().getSession();

			VehicleTypeQuery query = session.getMapper(VehicleTypeQuery.class);

			vehicleTypeResult = query.existsInDatabaseToSaveQuery(vehicleType);

		} catch (Exception e) {
			log.logError(method, "error to validate vehicleType", e);
			throw new DondeEstacionoServerException("vehicle.type.database.error.find", e);

		} finally {

			if (!DESUtils.isNull(session)) {
				session.close();
			}

		}

		if (!DESUtils.isNull(vehicleTypeResult)) {
			log.logInfo(method, "vehicleType exists in database");
			log.logEndMethod(method);
			return Boolean.TRUE;
		}

		log.logInfo(method, "vehicleType don't exists in database");
		log.logEndMethod(method);
		return Boolean.FALSE;

	}

	@Override
	public Boolean existsInDatabaseToUpdateOrDelete(VehicleType vehicleType) throws DondeEstacionoServerException {
		String method = "existsInDatabaseToUpdateOrDelete";
		log.logStartMethod(method);

		SqlSession session = null;
		VehicleType vehicleTypeResult = null;

		try {

			session = DatabaseConnection.getInstance().getSession();

			VehicleTypeQuery query = session.getMapper(VehicleTypeQuery.class);

			vehicleTypeResult = query.existsInDatabaseToUpdateQuery(vehicleType);

		} catch (Exception e) {
			log.logError(method, "error to validate vehicleType", e);
			throw new DondeEstacionoServerException("vehicle.type.database.error.find", e);

		} finally {

			if (!DESUtils.isNull(session)) {
				session.close();
			}

		}

		if (!DESUtils.isNull(vehicleTypeResult)) {
			log.logInfo(method, "vehicleType exists in database");
			log.logEndMethod(method);
			return Boolean.TRUE;
		}

		log.logInfo(method, "vehicleType don't exists in database");
		log.logEndMethod(method);
		return Boolean.FALSE;

	}

}
