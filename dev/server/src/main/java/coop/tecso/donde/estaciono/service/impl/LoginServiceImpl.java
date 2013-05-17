package coop.tecso.donde.estaciono.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import coop.tecso.donde.estaciono.dao.LoginDao;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.logger.LoggerFactory;
import coop.tecso.donde.estaciono.model.Login;
import coop.tecso.donde.estaciono.service.LoginService;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class LoginServiceImpl implements LoginService {

	private LoggerFactory log = LoggerFactory.getInstance(this.getClass());

	@Autowired
	private LoginDao loginDao;

	public Login authenticate(Login login) throws DondeEstacionoServerException {
		String mehotd = "authenticate";
		log.logStartMethod(mehotd);

		Login loginAuthenticated = this.loginDao.authenticate(login);

		log.logEndMethod(mehotd);
		return loginAuthenticated;
	}

}
