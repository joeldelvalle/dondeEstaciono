package coop.tecso.donde.estaciono.aspect;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import coop.tecso.donde.estaciono.service.EncryptService;

/**
 * 
 * @author joel.delvalle
 *
 *
 */

@Aspect
public class EncryptionAspect {

	@Autowired
	private EncryptService encryptService;

	
	@Around("execution(* coop.tecso.donde.estaciono.rest..*(..))")
	@Produces(MediaType.APPLICATION_JSON)
	public String dencryptAndEncryptMessage(ProceedingJoinPoint joinPoint) throws Throwable {

		Object[] arguments = joinPoint.getArgs();

		String message = (String) arguments[0];

		String messageDencrypt = this.getEncryptService().dencrypt(message);

		arguments[0] = messageDencrypt;

		String response = (String) joinPoint.proceed(arguments);

		return this.getEncryptService().encrypt(response);

	}

	private EncryptService getEncryptService() {
		return encryptService;
	}

}
