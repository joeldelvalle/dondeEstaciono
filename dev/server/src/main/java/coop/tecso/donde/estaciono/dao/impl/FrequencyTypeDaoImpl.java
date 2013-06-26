package coop.tecso.donde.estaciono.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import coop.tecso.donde.estaciono.dao.FrequencyTypeDao;
import coop.tecso.donde.estaciono.dao.queries.FrequencyTypeQuery;
import coop.tecso.donde.estaciono.dao.utils.DatabaseConnection;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.logger.CustomLogger;
import coop.tecso.donde.estaciono.model.FrequencyType;
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
	public Boolean existsFrequencyWithSamePriority(FrequencyType frequencyType) throws DondeEstacionoServerException {
		String method = "existsFrequencyWithSamePriority";
		log.logStartMethod(method);

		SqlSession session = null;
		FrequencyType frequencyTypeResult = null;

		try {

			session = DatabaseConnection.getInstance().getSession();

			FrequencyTypeQuery query = session.getMapper(FrequencyTypeQuery.class);

			frequencyTypeResult = query.existsWithSamePriorityQuery(frequencyType);

		} catch (Exception e) {
			log.logError(method, "error to validate frequencyType", e);
			throw new DondeEstacionoServerException("frequency.type.database.error", e);

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

}
