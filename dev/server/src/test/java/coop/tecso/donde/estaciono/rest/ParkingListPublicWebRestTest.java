package coop.tecso.donde.estaciono.rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import coop.tecso.donde.estaciono.communication.DESRequest;
import coop.tecso.donde.estaciono.communication.DESResponse;
import coop.tecso.donde.estaciono.service.impl.EncryptServiceImpl;
import coop.tecso.donde.estaciono.service.impl.SecurityServiceImpl;
import coop.tecso.donde.estaciono.utils.DESUtils;

/**
 * 
 * @author joel.delvalle
 * 
 *         clase test de un cliente que se conecta al restful de autenticacion
 */
public class ParkingListPublicWebRestTest {

	public void getAllParkings() {
		
		try {

			EncryptServiceImpl pp = new EncryptServiceImpl();
			
			SecurityServiceImpl ss = new SecurityServiceImpl();

			Client client = Client.create();

			WebResource webResource = client.resource("http://localhost:8080/DondeEstacionoServer/rest/parking/publicParkingList");

			
			DESRequest request = new DESRequest();
			request.setUserHash("HASH-PUBLIC-WEB");
			request.setPayload(null);
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

}
