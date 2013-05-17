package coop.tecso.donde.estaciono.dao.impl;

import coop.tecso.donde.estaciono.dao.RegisterDao;
import coop.tecso.donde.estaciono.dao.generic.DaoGeneric;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.model.MobileRegister;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class RegisterDaoImpl extends DaoGeneric implements RegisterDao {

	@Override
	public void save(MobileRegister mobileRegister) throws DondeEstacionoServerException {

		try {
			
			this.getMongoTemplate().save(mobileRegister);
			
		} catch (Exception e) {
			throw new DondeEstacionoServerException(e);
		}

	}

}
