package coop.tecso.donde.estaciono.dao.impl;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import coop.tecso.donde.estaciono.dao.ParkingHashDao;
import coop.tecso.donde.estaciono.dao.generic.DaoGeneric;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.logger.CustomLogger;
import coop.tecso.donde.estaciono.model.ParkingHash;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class ParkingHashDaoImpl extends DaoGeneric implements ParkingHashDao {

	private CustomLogger log = new CustomLogger(getClass().getCanonicalName());

	@Override
	public void save(String hash) {
		// TODO Auto-generated method stub

	}

	@Override
	public ParkingHash validateHash(String hash) throws DondeEstacionoServerException {
		String method = "validateHash";
		log.logStartMethod(method);

		ParkingHash parkingHash = null;
		try {
			parkingHash = this.getMongoTemplate().findOne(new Query(Criteria.where("hash").is(hash)), ParkingHash.class, "parkingHash");

		} catch (Exception e) {
			throw new DondeEstacionoServerException(e);
		}

		log.logEndMethod(method);
		return parkingHash;
	}
}
