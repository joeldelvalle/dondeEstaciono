package coop.tecso.donde.estaciono.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import coop.tecso.donde.estaciono.dao.CountryDao;
import coop.tecso.donde.estaciono.dao.queries.CountryQuery;
import coop.tecso.donde.estaciono.dao.utils.DatabaseConnection;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.logger.CustomLogger;
import coop.tecso.donde.estaciono.model.Country;
import coop.tecso.donde.estaciono.utils.DESUtils;

/**
 * 
 * @author joel.delvalle
 * 
 */
@Service
public class CountryDaoImpl implements CountryDao {

	private CustomLogger log = new CustomLogger(getClass().getCanonicalName());

	@Override
	public List<Country> findAll() throws DondeEstacionoServerException {
		String method = "findAll";
		log.logStartMethod(method);

		SqlSession session = null;
		List<Country> countryList = new ArrayList<Country>();

		try {

			session = DatabaseConnection.getInstance().getSession();

			CountryQuery query = session.getMapper(CountryQuery.class);

			countryList.addAll(query.findAllQuery());

		} catch (Exception e) {
			log.logError(method, "error to find countries", e);
			throw new DondeEstacionoServerException("country.database.error", e);

		} finally {

			if (!DESUtils.isNull(session)) {
				session.close();
			}

		}

		log.logInfo(method, "countryList size: " + countryList.size());
		log.logEndMethod(method);
		return countryList;
	}

}
