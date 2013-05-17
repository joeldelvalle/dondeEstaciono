package coop.tecso.donde.estaciono.rest.security;

import org.springframework.beans.factory.annotation.Autowired;

import coop.tecso.donde.estaciono.communication.DESRequest;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.service.SecurityService;
import coop.tecso.donde.estaciono.utils.DESUtils;

/**
 * 
 * @author joel.delvalle
 *
 */
public abstract class SecureRest {

	@Autowired
	private SecurityService securityService;

	/**
	 * valida la mac del payload enviado
	 * 
	 * @param request
	 * @throws DondeEstacionoServerException
	 */
	public void macValidation(DESRequest request) throws DondeEstacionoServerException {
		if (!this.securityService.validateMac(request.getMac(), DESUtils.convertObjectToJson(request.getPayload()))) {
			throw new DondeEstacionoServerException("Error MAC Validation");
		}
	}

	/**
	 * valida el hash enviado desde la web publica
	 * 
	 * @param request
	 * @throws DondeEstacionoServerException
	 */
	public void publicWebHashValidate(DESRequest request) throws DondeEstacionoServerException {
		if(!this.securityService.validatePublicWebHash(request.getUserHash())) {
			throw new DondeEstacionoServerException("Error HASH Validation");
		}
	}
	
	/**
	 * valida el hash enviado desde la el mobile
	 * 
	 * @param request
	 * @throws DondeEstacionoServerException
	 */
	public void mobileClientHashValidate(DESRequest request) throws DondeEstacionoServerException {
		if(!this.securityService.validateMobileClientHash(request.getUserHash())) {
			throw new DondeEstacionoServerException("Error HASH Validation");
		}
	}

}
