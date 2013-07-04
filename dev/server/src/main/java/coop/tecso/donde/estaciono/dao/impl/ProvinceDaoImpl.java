package coop.tecso.donde.estaciono.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import coop.tecso.donde.estaciono.dao.ProvinceDao;
import coop.tecso.donde.estaciono.dao.queries.ProvinceQuery;
import coop.tecso.donde.estaciono.dao.utils.DatabaseConnection;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.logger.CustomLogger;
import coop.tecso.donde.estaciono.model.Province;
import coop.tecso.donde.estaciono.utils.DESUtils;

/**
 * 
 * @author joel.delvalle
 * 
 */
@Service
public class ProvinceDaoImpl implements ProvinceDao {

	private CustomLogger log = new CustomLogger(getClass().getCanonicalName());

	@Override
	public List<Province> findAll() throws DondeEstacionoServerException {
		String method = "findAll";
		log.logStartMethod(method);

		SqlSession session = null;
		List<Province> provinceList = new ArrayList<Province>();

		try {

			session = DatabaseConnection.getInstance().getSession();

			ProvinceQuery query = session.getMapper(ProvinceQuery.class);

			provinceList.addAll(query.findAllProvinceQuery());

		} catch (Exception e) {
			log.logError(method, "error to find provinces", e);
			throw new DondeEstacionoServerException("province.database.error", e);

		} finally {

			if (!DESUtils.isNull(session)) {
				session.close();
			}

		}

		log.logInfo(method, "provinceList size: " + provinceList.size());
		log.logEndMethod(method);
		return provinceList;
	}

	@Override
	public List<Province> findByCountryId(Integer countryId) throws DondeEstacionoServerException {
		String method = "findByCountryId";
		log.logStartMethod(method);

		SqlSession session = null;
		List<Province> provinceList = new ArrayList<Province>();

		try {

			session = DatabaseConnection.getInstance().getSession();

			ProvinceQuery query = session.getMapper(ProvinceQuery.class);

			provinceList.addAll(query.findByCountryIdQuery(countryId));

		} catch (Exception e) {
			log.logError(method, "error to find provinces", e);
			throw new DondeEstacionoServerException("province.database.error", e);

		} finally {

			if (!DESUtils.isNull(session)) {
				session.close();
			}

		}

		log.logInfo(method, "provinceList size: " + provinceList.size());
		log.logEndMethod(method);
		return provinceList;
	
	}

}
