package coop.tecso.donde.estaciono.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import coop.tecso.donde.estaciono.bean.common.GenericBean;
import coop.tecso.donde.estaciono.communication.DESRequest;
import coop.tecso.donde.estaciono.communication.DESResponse;
import coop.tecso.donde.estaciono.errors.ErrorBuilder;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.logger.CustomLogger;
import coop.tecso.donde.estaciono.model.common.GenericModel;
import coop.tecso.donde.estaciono.rest.security.SecureRest;
import coop.tecso.donde.estaciono.spring.AppContextService;
import coop.tecso.donde.estaciono.utils.DESConstants;
import coop.tecso.donde.estaciono.utils.DESUtils;

/**
 * 
 * @author joel.delvalle
 * 
 *         restful que se utiliza para buscar informacion proveniente de la web
 *         y mobile
 * 
 */
@Component
@Path("/find")
public class FindInformationRest extends SecureRest {

	private CustomLogger log = new CustomLogger(getClass().getCanonicalName());

	@Autowired
	private AppContextService appContextService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@POST
	@Path("/byParking/{objectToFind}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String findByParking(String json, @PathParam("objectToFind") String objectToFind) {
		String method = "findByParking";
		log.logStartMethod(method);

		DESResponse dondeEstacionoResponse = new DESResponse();

		DESRequest request = null;
		try {

			request = DESUtils.convertJsonToObject(json, DESRequest.class);

			this.securityValidations(request);

			log.logInfo(method, "get bean");
			GenericBean bean = this.appContextService.getCustomBean(this.createBeanName(objectToFind));

			log.logInfo(method, "convert to model objetc");
			GenericModel genericModel = bean.convertToModelObject(request);

			log.logInfo(method, "start validations");
			bean.findByParkingValidation(genericModel);

			log.logInfo(method, "start execution");
			List resultList = bean.findByParkingExecution(genericModel);

			dondeEstacionoResponse.setStatus(DESConstants.StatusResponse.SUCCESS);
			dondeEstacionoResponse.setPayload(resultList);

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

	private String createBeanName(String objectToSave) {

		StringBuilder beanName = new StringBuilder(objectToSave.replace("request", ""));
		beanName.append("bean");

		return beanName.toString();
	}

}