package coop.tecso.donde.estaciono.dao.impl;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import coop.tecso.donde.estaciono.dao.LoginDao;
import coop.tecso.donde.estaciono.dao.generic.DaoGeneric;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.logger.LoggerFactory;
import coop.tecso.donde.estaciono.model.Login;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class LoginDaoImpl extends DaoGeneric implements LoginDao {

	private LoggerFactory log = LoggerFactory.getInstance(this.getClass());

	public Login authenticate(Login login) throws DondeEstacionoServerException {
		String method = "authenticate";
		log.logStartMethod(method);

		Login updatedLogin = null;
		try {
			updatedLogin = this.getMongoTemplate().findOne(
					new Query(Criteria.where("user").is(login.getUser()).andOperator(Criteria.where("password").is(login.getPassword()))),
					Login.class, "login");

		} catch (Exception e) {
			throw new DondeEstacionoServerException(e);
		}

		log.logEndMethod(method);
		return updatedLogin;
	}

}
