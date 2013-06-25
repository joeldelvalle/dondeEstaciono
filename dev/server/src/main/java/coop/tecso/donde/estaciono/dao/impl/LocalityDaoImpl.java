package coop.tecso.donde.estaciono.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import coop.tecso.donde.estaciono.dao.LocalityDao;
import coop.tecso.donde.estaciono.dao.queries.LocalityQuery;
import coop.tecso.donde.estaciono.dao.utils.DatabaseConnection;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.logger.CustomLogger;
import coop.tecso.donde.estaciono.model.Locality;
import coop.tecso.donde.estaciono.utils.DESUtils;

/**
 * 
 * @author joel.delvalle
 * 
 */
@Service
public class LocalityDaoImpl implements LocalityDao {

	private CustomLogger log = new CustomLogger(getClass().getCanonicalName());

	@Override
	public List<Locality> findAll() throws DondeEstacionoServerException {
		String method = "findAll";
		log.logStartMethod(method);

		SqlSession session = null;
		List<Locality> localityList = new ArrayList<Locality>();

		try {

			session = DatabaseConnection.getInstance().getSession();

			LocalityQuery query = session.getMapper(LocalityQuery.class);

			localityList.addAll(query.findAllQuery());

		} catch (Exception e) {
			log.logError(method, "error to find localities", e);
			throw new DondeEstacionoServerException("locality.database.error", e);

		} finally {

			if (!DESUtils.isNull(session)) {
				session.close();
			}

		}

		log.logInfo(method, "localityList size: " + localityList.size());
		log.logEndMethod(method);
		return localityList;
	}

	@Override
	public List<Locality> findByProvince(Integer provinceId) throws DondeEstacionoServerException {
		String method = "findByProvince";
		log.logStartMethod(method);

		SqlSession session = null;
		List<Locality> localityList = new ArrayList<Locality>();

		try {

			session = DatabaseConnection.getInstance().getSession();

			LocalityQuery query = session.getMapper(LocalityQuery.class);

			localityList.addAll(query.findByProvinceQuery(provinceId));

		} catch (Exception e) {
			log.logError(method, "error to find localities", e);
			throw new DondeEstacionoServerException("locality.database.error", e);

		} finally {

			if (!DESUtils.isNull(session)) {
				session.close();
			}

		}

		log.logInfo(method, "localityList size: " + localityList.size());
		log.logEndMethod(method);
		return localityList;
	}

}
