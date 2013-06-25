package coop.tecso.donde.estaciono.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import coop.tecso.donde.estaciono.communication.DESRequest;
import coop.tecso.donde.estaciono.communication.DESResponse;
import coop.tecso.donde.estaciono.errors.ErrorBuilder;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.logger.CustomLogger;
import coop.tecso.donde.estaciono.model.Country;
import coop.tecso.donde.estaciono.model.Locality;
import coop.tecso.donde.estaciono.model.Province;
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
	@Path("/list/country/all")
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

	@POST
	@Path("/list/province/all")
	@Consumes(MediaType.APPLICATION_JSON)
	public String findProvinces(String json) {
		String method = "findByParking";
		log.logStartMethod(method);

		DESResponse dondeEstacionoResponse = new DESResponse();

		DESRequest request = null;
		try {

			request = DESUtils.convertJsonToObject(json, DESRequest.class);

			this.securityValidations(request);

			List<Province> provinceList = this.applicationService.getProvinceList();

			dondeEstacionoResponse.setStatus(DESConstants.StatusResponse.SUCCESS);
			dondeEstacionoResponse.setPayload(provinceList);

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
	@Path("/list/province/{countryId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String findProvincesByCountry(String json, @PathParam("countryId") String countryId) {
		String method = "findProvincesByCountry";
		log.logStartMethod(method);

		DESResponse dondeEstacionoResponse = new DESResponse();

		DESRequest request = null;
		try {

			request = DESUtils.convertJsonToObject(json, DESRequest.class);

			this.securityValidations(request);
			this.validateCountryId(countryId);

			List<Province> provinceList = this.applicationService.getProvinceListByCountry(Integer.valueOf(countryId));

			dondeEstacionoResponse.setStatus(DESConstants.StatusResponse.SUCCESS);
			dondeEstacionoResponse.setPayload(provinceList);

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
	@Path("/list/locality/all")
	@Consumes(MediaType.APPLICATION_JSON)
	public String findLocalities(String json) {
		String method = "findLocalities";
		log.logStartMethod(method);

		DESResponse dondeEstacionoResponse = new DESResponse();

		DESRequest request = null;
		try {

			request = DESUtils.convertJsonToObject(json, DESRequest.class);

			this.securityValidations(request);

			List<Locality> provinceList = this.applicationService.getLocalityList();

			dondeEstacionoResponse.setStatus(DESConstants.StatusResponse.SUCCESS);
			dondeEstacionoResponse.setPayload(provinceList);

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
	@Path("/list/locality/{provinceId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String findLocalitiesByProvince(String json, @PathParam("provinceId") String provinceId) {
		String method = "findLocalitiesByProvince";
		log.logStartMethod(method);

		DESResponse dondeEstacionoResponse = new DESResponse();

		DESRequest request = null;
		try {

			request = DESUtils.convertJsonToObject(json, DESRequest.class);

			this.securityValidations(request);
			this.validateProvinceId(provinceId);

			List<Locality> provinceList = this.applicationService.getLocalityListByProvince(Integer.valueOf(provinceId));

			dondeEstacionoResponse.setStatus(DESConstants.StatusResponse.SUCCESS);
			dondeEstacionoResponse.setPayload(provinceList);

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
	@Path("/list/locality/{countryId}/{provinceId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String findLocalitiesByCountryByProvince(String json, @PathParam("countryId") String countryId, @PathParam("provinceId") String provinceId) {
		String method = "findLocalitiesByProvince";
		log.logStartMethod(method);

		DESResponse dondeEstacionoResponse = new DESResponse();

		DESRequest request = null;
		try {

			request = DESUtils.convertJsonToObject(json, DESRequest.class);

			this.securityValidations(request);
			this.validateCountryId(countryId);
			this.validateProvinceId(provinceId);

			List<Locality> provinceList = this.applicationService.getLocalityListByProvinceByCountry(Integer.valueOf(provinceId),
					Integer.valueOf(countryId));

			dondeEstacionoResponse.setStatus(DESConstants.StatusResponse.SUCCESS);
			dondeEstacionoResponse.setPayload(provinceList);

		} catch (DondeEstacionoServerException e) {
			log.logError(method, "ERROR FALTAL", e);
			dondeEstacionoResponse.setStatus(DESConstants.StatusResponse.FAIL);
			dondeEstacionoResponse.setPayload(ErrorBuilder.getInstance().buildError(e.getMessage()));
		}

		String jsonResponse = DESUtils.convertObjectToJson(dondeEstacionoResponse);

		log.logEndMethod(method);
		return jsonResponse;
	}

	private void validateCountryId(String countryId) throws DondeEstacionoServerException {
		try {
			Integer.valueOf(countryId);
		} catch (NumberFormatException e) {
			new DondeEstacionoServerException("application.list.countryId.numeric");
		}
	}

	private void validateProvinceId(String provinceId) throws DondeEstacionoServerException {
		try {
			Integer.valueOf(provinceId);
		} catch (NumberFormatException e) {
			new DondeEstacionoServerException("application.list.provinceId.numeric");
		}
	}

	private void securityValidations(DESRequest request) throws DondeEstacionoServerException {
		String method = "securityValidations";
		log.logStartMethod(method);

		this.publicWebHashValidate(request);
		this.macValidation(request);

		log.logEndMethod(method);
	}

}