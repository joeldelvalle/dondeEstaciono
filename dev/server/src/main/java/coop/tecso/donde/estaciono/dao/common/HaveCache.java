package coop.tecso.donde.estaciono.dao.common;

import java.util.Calendar;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import coop.tecso.donde.estaciono.dao.utils.DatabaseConnection;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.logger.CustomLogger;
import coop.tecso.donde.estaciono.utils.DESTime;
import coop.tecso.donde.estaciono.utils.DESUtils;

/**
 * 
 * @author joel.delvalle
 * 
 */
public abstract class HaveCache<ModelObject> {

	private CustomLogger log = new CustomLogger(getClass().getCanonicalName());

	private Calendar lastUpdateCache;

	protected abstract List<ModelObject> getCacheList();

	protected abstract List<ModelObject> excuteQuery(SqlSession session) throws Exception;

	protected abstract String getErrorKey();

	protected synchronized Boolean isUpdateCaheList() {

		if (DESUtils.isNull(this.lastUpdateCache) || DESTime.calculateDifferenceBetweenTwoCalendars(this.lastUpdateCache, DESTime.getToday()) > 10) {
			return Boolean.TRUE;
		}

		return Boolean.FALSE;

	}

	protected synchronized void updateCache() throws DondeEstacionoServerException {

		this.getCacheList().clear();

		this.lastUpdateCache = Calendar.getInstance();

		SqlSession session = null;

		try {

			session = DatabaseConnection.getInstance().getSession();

			List<ModelObject> result = this.excuteQuery(session);
			log.logInfo("updateCache", "cantidad de registros encontrados: " + result.size());

			this.getCacheList().addAll(result);

			session.close();

		} catch (Exception e) {
			String errorKey = this.getErrorKey();
			throw new DondeEstacionoServerException(errorKey, e);

		} finally {

			if (!DESUtils.isNull(session)) {
				session.close();
			}

		}

	}

}