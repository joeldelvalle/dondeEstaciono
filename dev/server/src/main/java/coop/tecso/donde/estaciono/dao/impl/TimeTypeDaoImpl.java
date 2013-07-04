package coop.tecso.donde.estaciono.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import coop.tecso.donde.estaciono.dao.TimeTypeDao;
import coop.tecso.donde.estaciono.dao.common.HaveCache;
import coop.tecso.donde.estaciono.dao.queries.TimeTypeQuery;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.logger.CustomLogger;
import coop.tecso.donde.estaciono.model.TimeType;

/**
 * 
 * @author joel.delvalle
 * 
 */
@Service
public class TimeTypeDaoImpl extends HaveCache<TimeType> implements TimeTypeDao {

	private CustomLogger log = new CustomLogger(getClass().getCanonicalName());

	private List<TimeType> timeTypeListCache = new ArrayList<TimeType>();

	@Override
	public List<TimeType> findAll() throws DondeEstacionoServerException {
		log.logInfo("findAll", "find all timeType");

		if (this.isUpdateCaheList()) {
			this.updateCache();
		}

		return this.getCacheList();
	}

	@Override
	public TimeType findById(Integer id) throws DondeEstacionoServerException {
		log.logInfo("findById", "find all timeType");

		if (this.isUpdateCaheList()) {
			this.updateCache();
		}

		for (TimeType timeType : this.getCacheList()) {
			if (timeType.getId().equals(id)) {
				return timeType;
			}
		}

		return null;

	}
	
	@Override
	protected List<TimeType> getCacheList() {
		return this.timeTypeListCache;
	}

	@Override
	protected List<TimeType> excuteQuery(SqlSession session) throws Exception {
		TimeTypeQuery query = session.getMapper(TimeTypeQuery.class);
		return query.findAllTimeTypeQuery();
	}

	@Override
	protected String getErrorKey() {
		return "timeType.find.database.error";
	}

}
