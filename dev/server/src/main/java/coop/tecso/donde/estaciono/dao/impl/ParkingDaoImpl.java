package coop.tecso.donde.estaciono.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import coop.tecso.donde.estaciono.dao.ParkingDao;
import coop.tecso.donde.estaciono.dao.queries.ParkingQuery;
import coop.tecso.donde.estaciono.dao.utils.DatabaseConnection;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.logger.CustomLogger;
import coop.tecso.donde.estaciono.model.Parking;
import coop.tecso.donde.estaciono.utils.DESTime;
import coop.tecso.donde.estaciono.utils.DESUtils;

/**
 * 
 * @author joel.delvalle
 * 
 */
@Service
public class ParkingDaoImpl implements ParkingDao {

	private CustomLogger log = new CustomLogger(getClass().getCanonicalName());

	private List<Parking> parkingListCache = new ArrayList<Parking>();

	private Calendar lastUpdateCache;

	@Override
	public List<Parking> findAllParking() throws DondeEstacionoServerException {
		String method = "findAllParking";
		log.logStartMethod(method);

		if (this.isUpdateCaheParkingList()) {
			this.updateCache();
		}

		log.logEndMethod(method);

		return this.parkingListCache;

	}

	private Boolean isUpdateCaheParkingList() {

		if (DESTime.calculateDifferenceBetweenTwoCalendars(this.lastUpdateCache, DESTime.getToday()) > 10) {
			return Boolean.TRUE;
		}

		return Boolean.FALSE;

	}

	private synchronized void updateCache() throws DondeEstacionoServerException {

		this.parkingListCache.clear();

		this.lastUpdateCache = Calendar.getInstance();

		SqlSession session = null;

		try {

			session = DatabaseConnection.getInstance().getSession();

			ParkingQuery query = session.getMapper(ParkingQuery.class);

			this.parkingListCache.addAll(query.findAllQuery());

			log.logInfo("updateCache", "cantidad de parkings encontrados: " + parkingListCache.size());

			session.close();

		} catch (Exception e) {

			throw new DondeEstacionoServerException(e);

		} finally {

			if (!DESUtils.isNull(session)) {
				session.close();
			}

		}

	}

}