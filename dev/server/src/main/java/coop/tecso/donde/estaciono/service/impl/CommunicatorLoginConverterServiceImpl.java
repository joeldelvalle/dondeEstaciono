package coop.tecso.donde.estaciono.service.impl;

import org.springframework.stereotype.Service;

import coop.tecso.donde.estaciono.communication.model.web.LoginRequest;
import coop.tecso.donde.estaciono.logger.LoggerFactory;
import coop.tecso.donde.estaciono.model.Login;
import coop.tecso.donde.estaciono.service.CommunicatorConverterService;

/**
 * 
 * @author joel.delvalle
 * 
 */
@Service
public class CommunicatorLoginConverterServiceImpl implements CommunicatorConverterService<LoginRequest, Login> {

	private LoggerFactory log = LoggerFactory.getInstance(getClass());

	@Override
	public Login convertToModelObject(LoginRequest requestObject) {
		log.logStartMethod("convertToModelObject");

		Login login = new Login();
		login.setUser(requestObject.getUser());
		login.setPassword(requestObject.getPassword());

		log.logEndMethod("convertToModelObject");
		return login;
	}

}