package coop.tecso.donde.estaciono.dao.impl;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import coop.tecso.donde.estaciono.dao.MobileHashDao;
import coop.tecso.donde.estaciono.dao.generic.DaoGeneric;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.logger.LoggerFactory;
import coop.tecso.donde.estaciono.model.MobileHash;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class MobileHashDaoImpl extends DaoGeneric implements MobileHashDao {

	LoggerFactory log = LoggerFactory.getInstance(getClass());

	@Override
	public void save(MobileHash hash) throws DondeEstacionoServerException {
		String method = "save";
		log.logStartMethod(method);

		try {
			this.getMongoTemplate().save(hash);

		} catch (Exception e) {
			throw new DondeEstacionoServerException(e);
		}

		log.logEndMethod(method);
	}

	@Override
	public MobileHash validateHash(String hash) throws DondeEstacionoServerException {
		String method = "validateHash";
		log.logStartMethod(method);

		MobileHash mobileHash = null;
		try {
			mobileHash = this.getMongoTemplate().findOne(new Query(Criteria.where("hash").is(hash)), MobileHash.class, "mobileHash");

		} catch (Exception e) {
			throw new DondeEstacionoServerException(e);
		}

		log.logEndMethod(method);
		return mobileHash;
	}
}
