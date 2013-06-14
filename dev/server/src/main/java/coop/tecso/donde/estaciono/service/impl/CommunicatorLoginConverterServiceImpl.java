package coop.tecso.donde.estaciono.service.impl;

import org.springframework.stereotype.Service;

import coop.tecso.donde.estaciono.communication.model.LoginRequest;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.logger.CustomLogger;
import coop.tecso.donde.estaciono.model.Login;
import coop.tecso.donde.estaciono.service.CommunicatorConverterService;

/**
 * 
 * @author joel.delvalle
 * 
 */
@Service("communicatorLoginConverterService")
public class CommunicatorLoginConverterServiceImpl implements CommunicatorConverterService<LoginRequest, Login> {

	private CustomLogger log = new CustomLogger(getClass().getCanonicalName());

	@Override
	public Login convertToModelObject(LoginRequest requestObject) throws DondeEstacionoServerException {
		log.logStartMethod("convertToModelObject");

		Login login = new Login();
		login.setUserName(requestObject.getUser());
		login.setPassword(requestObject.getPassword());

		log.logEndMethod("convertToModelObject");
		return login;
	}

}
