package coop.tecso.donde.estaciono.rest;

import java.util.List;

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
import coop.tecso.donde.estaciono.model.Country;
import coop.tecso.donde.estaciono.rest.security.SecureRest;
import coop.tecso.donde.estaciono.service.ApplicationService;
import coop.tecso.donde.estaciono.utils.DESConstants;
import coop.tecso.donde.estaciono.utils.DESUtils;

/**
 * 
 * @author joel.delvalle
 * 
 *         restful que se utiliza para solicitar informacion de la app, por
 *         ejemplo listado de paises, provincias y localidades
 * 
 */
@Component
@Path("/application")
public class ApplicationInformationRest extends SecureRest {

	private CustomLogger log = new CustomLogger(getClass().getCanonicalName());

	@Autowired
	private ApplicationService applicationService;

	@POST
	@Path("/country")
	@Consumes(MediaType.APPLICATION_JSON)
	public String findCountries(String json) {
		String method = "findByParking";
		log.logStartMethod(method);

		DESResponse dondeEstacionoResponse = new DESResponse();

		DESRequest request = null;
		try {

			request = DESUtils.convertJsonToObject(json, DESRequest.class);

			this.securityValidations(request);

			List<Country> countryList = this.applicationService.getCountryList();

			dondeEstacionoResponse.setStatus(DESConstants.StatusResponse.SUCCESS);
			dondeEstacionoResponse.setPayload(countryList);

		} catch (DondeEstacionoServerException e) {
			log.logError(method, "ERROR FALTAL", e);
			dondeEstacionoResponse.setStatus(DESConstants.StatusResponse.FAIL);
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