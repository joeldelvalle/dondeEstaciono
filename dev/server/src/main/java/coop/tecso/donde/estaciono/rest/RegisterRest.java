package coop.tecso.donde.estaciono.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import coop.tecso.donde.estaciono.communication.DESRequest;
import coop.tecso.donde.estaciono.communication.DESResponse;
import coop.tecso.donde.estaciono.errors.ErrorBuilder;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.logger.CustomLogger;
import coop.tecso.donde.estaciono.model.MobileHash;
import coop.tecso.donde.estaciono.model.MobileRegister;
import coop.tecso.donde.estaciono.rest.security.SecureRest;
import coop.tecso.donde.estaciono.service.RegisterService;
import coop.tecso.donde.estaciono.utils.DESConstants;
import coop.tecso.donde.estaciono.utils.DESUtils;

/**
 * 
 * @author joel.delvalle
 * 
 *         restful para registrar a los usuarios mobile y a los operadores de
 *         los estacionamientos
 */
@Component
@Path("/register")
public class RegisterRest extends SecureRest {

	private CustomLogger log = new CustomLogger(getClass().getCanonicalName());

	@Autowired
	private RegisterService registerService;

	@POST
	@Path("/mobileRegister")
	@Consumes(MediaType.APPLICATION_JSON)
	public String mobileRegister(String json) {
		String method = "publicParkingList";
		log.logStartMethod(method);

		DESResponse dondeEstacionoResponse = new DESResponse();

		DESRequest request = null;
		try {

			request = DESUtils.convertJsonToObject(json, DESRequest.class);

			this.securityValidations(request);

			MobileHash hashResult = this.registerService.mobileRegister(request.getPayload(MobileRegister.class));

			dondeEstacionoResponse.setStatus(DESConstants.StatusResponse.SUCCESS);
			dondeEstacionoResponse.setPayload(hashResult);

		} catch (DondeEstacionoServerException e) {
			log.logError(method, "ERROR FALTAL", e);
			dondeEstacionoResponse.setStatus(DESConstants.StatusResponse.FAIL);
			dondeEstacionoResponse.setPayload(ErrorBuilder.getInstance().buildError(e.getMessage()));
		}

		String jsonResponse = DESUtils.convertObjectToJson(dondeEstacionoResponse);
		log.logEndMethod(method);

		return jsonResponse;
	}

	@POST
	@Path("/parkingUserRegister")
	@Consumes(MediaType.APPLICATION_JSON)
	public String parkingUserRegister(String json) {
		
		
		return null;
		
	}
	
	private void securityValidations(DESRequest request) throws DondeEstacionoServerException {
		String method = "securityValidations";
		log.logStartMethod(method);

		this.publicWebHashValidate(request);
		this.macValidation(request);

		log.logEndMethod(method);
	}

}
