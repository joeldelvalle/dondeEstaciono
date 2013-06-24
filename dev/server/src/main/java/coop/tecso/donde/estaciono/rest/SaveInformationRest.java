package coop.tecso.donde.estaciono.rest;

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
 *         restful que se utiliza para grabar informacion proveniente de la web
 *         y mobile
 * 
 */
@Component
@Path("/save")
public class SaveInformationRest extends SecureRest {

	private CustomLogger log = new CustomLogger(getClass().getCanonicalName());

	@Autowired
	private AppContextService appContextService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@POST
	@Path("/{objectToSave}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String save(String json, @PathParam("objectToSave") String objectToSave) {
		String method = "save";
		log.logStartMethod(method);

		DESResponse dondeEstacionoResponse = new DESResponse();

		DESRequest request = null;
		try {

			request = DESUtils.convertJsonToObject(json, DESRequest.class);

			this.securityValidations(request);

			log.logInfo(method, "get bean");
			GenericBean bean = this.appContextService.getCustomBean(this.createBeanName(objectToSave));

			log.logInfo(method, "convert to model objetc");
			GenericModel genericModel = bean.convertToModelObject(request);

			log.logInfo(method, "start validations");
			bean.saveValidation(genericModel);

			log.logInfo(method, "start execution");
			bean.saveExecution(genericModel);

			dondeEstacionoResponse.setStatus(DESConstants.StatusResponse.SUCCESS);
			
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

	private String createBeanName(String objectToSave) {

		StringBuilder beanName = new StringBuilder(objectToSave.replace("request", ""));
		beanName.append("bean");

		return beanName.toString();
	}

}