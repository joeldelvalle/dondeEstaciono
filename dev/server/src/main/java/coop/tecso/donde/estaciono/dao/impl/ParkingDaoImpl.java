package coop.tecso.donde.estaciono.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import coop.tecso.donde.estaciono.dao.ParkingDao;
import coop.tecso.donde.estaciono.dao.common.HaveCache;
import coop.tecso.donde.estaciono.dao.queries.ParkingQuery;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.logger.CustomLogger;
import coop.tecso.donde.estaciono.model.Parking;

/**
 * 
 * @author joel.delvalle
 * 
 */
@Service
public class ParkingDaoImpl extends HaveCache<Parking> implements ParkingDao {

	private CustomLogger log = new CustomLogger(getClass().getCanonicalName());

	private List<Parking> parkingListCache = new ArrayList<Parking>();

	@Override
	public List<Parking> findAllParking() throws DondeEstacionoServerException {
		String method = "findAllParking";
		log.logStartMethod(method);
		log.logEndMethod(method);

		if (this.isUpdateCaheList()) {
			this.updateCache();
		}

		return this.getCacheList();
	}

	@Override
	public Parking findByIdentificationCode(String identificationCode) throws DondeEstacionoServerException {

		if (this.isUpdateCaheList()) {
			this.updateCache();
		}

		for (Parking parking : this.getCacheList()) {

			if (identificationCode.equals(parking.getIdentificationCode())) {
				return parking;
			}

		}

		throw new DondeEstacionoServerException("parking.idetification.code.not.found");
	}

	@Override
	protected List<Parking> getCacheList() {
		return this.parkingListCache;
	}

	@Override
	protected List<Parking> excuteQuery(SqlSession session) throws Exception {
		ParkingQuery query = session.getMapper(ParkingQuery.class);
		return query.findAllParkingQuery();
	}

	@Override
	protected String getErrorKey() {
		return "parking.find.database.error";
	}
}