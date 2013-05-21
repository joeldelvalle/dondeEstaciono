package coop.tecso.donde.estaciono.rest;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import coop.tecso.donde.estaciono.communication.DESRequest;
import coop.tecso.donde.estaciono.communication.DESResponse;
import coop.tecso.donde.estaciono.model.MobileRegister;
import coop.tecso.donde.estaciono.mongodb.config.MongoDBConfiguration;
import coop.tecso.donde.estaciono.service.impl.EncryptServiceImpl;
import coop.tecso.donde.estaciono.service.impl.SecurityServiceImpl;
import coop.tecso.donde.estaciono.utils.DESUtils;

/**
 * 
 * @author joel.delvalle
 * 
 *         clase test de un cliente que se conecta al restful de autenticacion
 */
public class MobileRegisterRestTest {

	public void getAllParkings() {
		
		try {

			EncryptServiceImpl pp = new EncryptServiceImpl();
			
			SecurityServiceImpl ss = new SecurityServiceImpl();

			Client client = Client.create();

			WebResource webResource = client.resource("http://localhost:8080/DondeEstacionoServer/rest/register/mobileRegister");

			MobileRegister mm = new MobileRegister();
			mm.setNumber("1564661795");
			
			DESRequest request = new DESRequest();
			request.setUserHash("HASH-PUBLIC-WEB");
			request.setPayload(mm);
			request.setMac(ss.buildMac(DESUtils.convertObjectToJson(request.getPayload())));

			String requestJson = DESUtils.convertObjectToJson(request);
			System.out.println("request json  " + requestJson);
			
			String ppp = pp.encrypt(requestJson);
			System.out.println("request json encrypted  " + ppp);
			
			String response = webResource.type("application/json").post(String.class, ppp);

			System.out.println("RESULT encripted:  " + response);

			String dencryptedMessage = pp.dencrypt(response);
			System.out.println("RESULT dencripted:  " + dencryptedMessage);


			DESResponse resp = DESUtils.convertJsonToObject(dencryptedMessage, DESResponse.class);
			
			System.out.println(resp.toString());

		} catch (Exception e) {

			e.printStackTrace();

		}

	}
	
	
	public void cleanAllMobileRegistered() {
		
		
		ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoDBConfiguration.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

		List<MobileRegister> resultFindAll = mongoOperation.findAll(MobileRegister.class);
		System.out.println("cantidad de mobiles registrados: " + resultFindAll.size());
		
		mongoOperation.dropCollection(MobileRegister.class);
		System.out.println("delete aplicado");
		
		List<MobileRegister> resultFindAllPostDelete = mongoOperation.findAll(MobileRegister.class);
		System.out.println("cantidad de mobiles registrados: " + resultFindAllPostDelete.size());
		
		
	}

}
