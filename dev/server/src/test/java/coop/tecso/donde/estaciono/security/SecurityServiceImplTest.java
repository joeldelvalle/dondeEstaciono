package coop.tecso.donde.estaciono.security;

import org.junit.Assert;
import org.junit.Test;

import coop.tecso.donde.estaciono.model.Mac;
import coop.tecso.donde.estaciono.service.SecurityService;
import coop.tecso.donde.estaciono.service.impl.SecurityServiceImpl;

/**
 * 
 * @author joel.delvalle
 *
 */
public class SecurityServiceImplTest {

	private SecurityService securityService = new SecurityServiceImpl();

	@Test
	public void builMac() {

		// DADO
		String json = "{\"user\":\"jdelvalle\",\"password\":\"jdelvalle\"}";

		// CUANDO
		Mac resultMac = this.securityService.buildMac(json);

		// ENTONCES
		Assert.assertNotNull("La MAC no puede ser null", resultMac);
		Assert.assertEquals("La MAC debe ser: 3438346289403984", "3438346289403984", resultMac.getMac());

	}

	@Test
	public void validaMac() {

		// DADO
		String json = "{\"user\":\"jdelvalle\",\"password\":\"jdelvalle\"}";
		Mac mac = new Mac("3438346289403984");

		// CUANDO
		Boolean result = this.securityService.validateMac(mac, json);

		// ENTONCES
		Assert.assertTrue("La MAC debe ser valida", result);
	}

	@Test
	public void validatePublicWebHash() {

		// DADO
		String publicWebHash = "HASH-PUBLIC-WEB";

		// CUANDO
		Boolean result = this.securityService.validatePublicWebHash(publicWebHash);

		// ENTONCES
		Assert.assertTrue("El Hash Publico de la Web es invalido", result);

	}
	
	
	public void validateParkingUserHash() {
		//TODO: hacer el test cuando definamos la modalidad del hash para estacionamientos
	}
	
	
	public void validateMobileClientHash() {
		//TODO: hacer el test cuando definamos la modalidad del hash para mobile
	}
			
}		


