package coop.tecso.donde.estaciono.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import coop.tecso.donde.estaciono.dao.FrequencyTypeDao;
import coop.tecso.donde.estaciono.dao.queries.FrequencyTypeQuery;
import coop.tecso.donde.estaciono.dao.utils.DatabaseConnection;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.logger.CustomLogger;
import coop.tecso.donde.estaciono.model.FrequencyType;
import coop.tecso.donde.estaciono.utils.DESConstants;
import coop.tecso.donde.estaciono.utils.DESUtils;

/**
 * 
 * @author joel.delvalle
 * 
 */
@Service
public class FrequencyTypeDaoImpl implements FrequencyTypeDao {

	private CustomLogger log = new CustomLogger(getClass().getCanonicalName());

	@Override
	public void save(FrequencyType frequencyType) throws DondeEstacionoServerException {
		String method = "save";
		log.logStartMethod(method);

		SqlSession session = null;
		try {

			session = DatabaseConnection.getInstance().getSession();

			FrequencyTypeQuery query = session.getMapper(FrequencyTypeQuery.class);

			query.saveQuery(frequencyType);

			session.commit();

		} catch (Exception e) {
			log.logError(method, "error to save frequencyType", e);
			throw new DondeEstacionoServerException("frequency.type.database.error.save", e);

		} finally {

			if (!DESUtils.isNull(session)) {
				session.close();
			}

		}

		log.logEndMethod(method);
	}

	@Override
	public void update(FrequencyType frequencyType) throws DondeEstacionoServerException {
		String method = "update";
		log.logStartMethod(method);

		SqlSession session = null;
		try {

			session = DatabaseConnection.getInstance().getSession();

			FrequencyTypeQuery query = session.getMapper(FrequencyTypeQuery.class);

			query.updateQuery(frequencyType);

			session.commit();

		} catch (Exception e) {
			log.logError(method, "error to update frequencyType", e);
			throw new DondeEstacionoServerException("frequency.type.database.error.update", e);

		} finally {

			if (!DESUtils.isNull(session)) {
				session.close();
			}

		}

		log.logEndMethod(method);
	}

	@Override
	public void delete(FrequencyType frequencyType) throws DondeEstacionoServerException {
		String method = "delete";
		log.logStartMethod(method);

		SqlSession session = null;
		try {

			session = DatabaseConnection.getInstance().getSession();

			FrequencyTypeQuery query = session.getMapper(FrequencyTypeQuery.class);

			query.deleteQuery(frequencyType);

			session.commit();

		} catch (Exception e) {
			log.logError(method, "error to update frequencyType", e);
			throw new DondeEstacionoServerException("frequency.type.database.error.delete", e);

		} finally {

			if (!DESUtils.isNull(session)) {
				session.close();
			}

		}

		log.logEndMethod(method);

	}

	@Override
	public List<FrequencyType> findAll() throws DondeEstacionoServerException {
		return null;
	}

	@Override
	public List<FrequencyType> findByParking(String identificationCode) throws DondeEstacionoServerException {
		String method = "findByParking";
		log.logStartMethod(method);

		SqlSession session = null;
		List<FrequencyType> frequencyTypeList = new ArrayList<FrequencyType>();

		try {

			session = DatabaseConnection.getInstance().getSession();

			FrequencyTypeQuery query = session.getMapper(FrequencyTypeQuery.class);

			frequencyTypeList = query.findByParkingQuery(identificationCode);

		} catch (Exception e) {
			log.logError(method, "error to find all frequencyType", e);
			throw new DondeEstacionoServerException("frequency.type.database.error.find", e);

		} finally {

			if (!DESUtils.isNull(session)) {
				session.close();
			}

		}

		log.logInfo(method, "frequencyType size found: " + frequencyTypeList.size());
		log.logEndMethod(method);

		return frequencyTypeList;
	}

	@Override
	public Boolean existsInDatabaseToSave(FrequencyType frequencyType) throws DondeEstacionoServerException {
		String method = "existsInDatabaseToSave";
		log.logStartMethod(method);

		SqlSession session = null;
		FrequencyType frequencyTypeResult = null;

		try {

			session = DatabaseConnection.getInstance().getSession();

			FrequencyTypeQuery query = session.getMapper(FrequencyTypeQuery.class);

			frequencyTypeResult = query.existsInDatabaseToSaveQuery(frequencyType);

		} catch (Exception e) {
			log.logError(method, "error to validate frequencyType", e);
			throw new DondeEstacionoServerException("frequency.type.database.error", e);

		} finally {

			if (!DESUtils.isNull(session)) {
				session.close();
			}

		}

		if (!DESUtils.isNull(frequencyTypeResult)) {
			log.logInfo(method, "frequencyType exists in database");
			log.logEndMethod(method);
			return Boolean.TRUE;
		}

		log.logInfo(method, "frequencyType don't exists in database");
		log.logEndMethod(method);
		return Boolean.FALSE;

	}

	@Override
	public Boolean existsFrequencyWithSamePriority(FrequencyType frequencyType, DESConstants.Action action) throws DondeEstacionoServerException {
		String method = "existsFrequencyWithSamePriority";
		log.logStartMethod(method);

		SqlSession session = null;
		FrequencyType frequencyTypeResult = null;

		try {

			session = DatabaseConnection.getInstance().getSession();

			FrequencyTypeQuery query = session.getMapper(FrequencyTypeQuery.class);

			if (DESConstants.Action.SAVE.equals(action)) {
				frequencyTypeResult = query.existsWithSamePriorityToSaveQuery(frequencyType);
			} else {
				frequencyTypeResult = query.existsWithSamePriorityToUpdateOrDeleteQuery(frequencyType);
			}

		} catch (Exception e) {
			log.logError(method, "error to validate frequencyType", e);
			throw new DondeEstacionoServerException("frequency.type.database.error.find", e);

		} finally {

			if (!DESUtils.isNull(session)) {
				session.close();
			}

		}

		if (!DESUtils.isNull(frequencyTypeResult)) {
			log.logInfo(method, "frequencyType exists with the same priority in database");
			log.logEndMethod(method);
			return Boolean.TRUE;
		}

		log.logInfo(method, "frequencyType don't exists with the same priority in database");
		log.logEndMethod(method);
		return Boolean.FALSE;

	}

	@Override
	public Boolean existsInDatabaseToUpdateOrDelete(FrequencyType frequencyType) throws DondeEstacionoServerException {
		String method = "existsInDatabaseToUpdateOrDelete";
		log.logStartMethod(method);

		SqlSession session = null;
		FrequencyType frequencyTypeResult = null;

		try {

			session = DatabaseConnection.getInstance().getSession();

			FrequencyTypeQuery query = session.getMapper(FrequencyTypeQuery.class);

			frequencyTypeResult = query.existsInDatabaseToUpdateQuery(frequencyType);

		} catch (Exception e) {
			log.logError(method, "error to validate frequencyType", e);
			throw new DondeEstacionoServerException("frequency.type.database.error.find", e);

		} finally {

			if (!DESUtils.isNull(session)) {
				session.close();
			}

		}

		if (!DESUtils.isNull(frequencyTypeResult)) {
			log.logInfo(method, "frequencyType exists in database");
			log.logEndMethod(method);
			return Boolean.TRUE;
		}

		log.logInfo(method, "frequencyType don't exists in database");
		log.logEndMethod(method);
		return Boolean.FALSE;

	}

}
