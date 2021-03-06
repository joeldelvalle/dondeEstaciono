package coop.tecso.donde.estaciono.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import coop.tecso.donde.estaciono.communication.DESRequest;
import coop.tecso.donde.estaciono.communication.DESResponse;
import coop.tecso.donde.estaciono.communication.model.LoginRequest;
import coop.tecso.donde.estaciono.errors.ErrorBuilder;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.logger.CustomLogger;
import coop.tecso.donde.estaciono.model.Login;
import coop.tecso.donde.estaciono.model.User;
import coop.tecso.donde.estaciono.rest.security.SecureRest;
import coop.tecso.donde.estaciono.service.CommunicatorConverterService;
import coop.tecso.donde.estaciono.service.LoginService;
import coop.tecso.donde.estaciono.service.UserService;
import coop.tecso.donde.estaciono.utils.DESConstants;
import coop.tecso.donde.estaciono.utils.DESUtils;

/**
 * 
 * @author joel.delvalle
 * 
 *         restful de autenticacion de usuarios
 * 
 */
@Component
@Path("/login")
public class LoginAuthenticationRest extends SecureRest {

	private CustomLogger log = new CustomLogger(getClass().getCanonicalName());

	@Autowired
	private UserService userService;

	@Autowired
	private LoginService loginService;

	@Autowired
	@Qualifier("communicatorLoginConverterService")
	private CommunicatorConverterService<LoginRequest, Login> communicatorLoginConverterService;

	@POST
	@Path("/authentication")
	@Consumes(MediaType.APPLICATION_JSON)
	public String authentication(String json) {
		String method = "authentication";
		log.logStartMethod(method);

		DESResponse dondeEstacionoResponse = new DESResponse();

		DESRequest request = null;
		try {

			request = DESUtils.convertJsonToObject(json, DESRequest.class);

			this.securityValidations(request);

			Login login = this.communicatorLoginConverterService.convertToModelObject(request.getPayload(LoginRequest.class));

			Login loginAuthenticated = this.loginService.authenticate(login);
			if (!DESUtils.isNull(loginAuthenticated)) {
				log.logInfo(method, "login OK =)");
				User user = this.userService.findMockUser();
				dondeEstacionoResponse.setStatus(DESConstants.StatusResponse.SUCCESS);
				dondeEstacionoResponse.setPayload(user);
			} else {
				log.logInfo(method, "login ERROR =(");
				dondeEstacionoResponse.setStatus(DESConstants.StatusResponse.ERROR);
				dondeEstacionoResponse.setPayload(ErrorBuilder.getInstance().buildError("loginInvalido"));
			}

		} catch (DondeEstacionoServerException e) {
			log.logError(method, "ERROR FALTAL", e);
			dondeEstacionoResponse.setStatus(DESConstants.StatusResponse.ERROR);
			dondeEstacionoResponse.setPayload(ErrorBuilder.getInstance().buildError(e.getMessage()));
		}

		String jsonResponse = DESUtils.convertObjectToJson(dondeEstacionoResponse);

		log.logEndMethod(method);
		return jsonResponse;
	}

	private void securityValidations(DESRequest request) throws DondeEstacionoServerException {
		String method = "securityValidations";
		log.logStartMethod(method);

		this.publicWebHashValidate(request);
		this.macValidation(request);

		log.logEndMethod(method);
	}

}