package coop.tecso.donde.estaciono.rest.parkingrates;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import coop.tecso.donde.estaciono.communication.DESRequest;
import coop.tecso.donde.estaciono.communication.model.ParkingRatesRequest;
import coop.tecso.donde.estaciono.service.impl.EncryptServiceImpl;
import coop.tecso.donde.estaciono.service.impl.SecurityServiceImpl;
import coop.tecso.donde.estaciono.utils.DESUtils;

/**
 * 
 * @author german.romero
 * 
 *         clase test de un cliente que se conecta al restful de save
 */
public class SaveInformationParkingRatesRestTest {

	public static void main(String[] args) {

		try {

			EncryptServiceImpl pp = new EncryptServiceImpl();
			
			SecurityServiceImpl ss = new SecurityServiceImpl();

			Client client = Client.create();

			WebResource webResource = client.resource("http://localhost:8080/DondeEstacionoServer/rest/save/parkingratesrequest");

			ParkingRatesRequest parkingRatesRequest = new ParkingRatesRequest();
			parkingRatesRequest.setVehicleType(Long.valueOf("0"));
			parkingRatesRequest.setFrequencyType(Long.valueOf("0"));
			parkingRatesRequest.setAmount(Double.valueOf("100.00"));
			parkingRatesRequest.setParkingIdentificationCode("OTT");
			
			DESRequest request = new DESRequest();
			request.setUserHash("HASH-PUBLIC-WEB");
			request.setPayload(parkingRatesRequest);
			request.setMac(ss.buildMac(DESUtils.convertObjectToJson(parkingRatesRequest)));

			String requestJson = DESUtils.convertObjectToJson(request);
			System.out.println("request json  " + requestJson);
			
			String ppp = pp.encrypt(requestJson);
			System.out.println("request json encrypted  " + ppp);
			
			String response = webResource.type("application/json").post(String.class, ppp);

			System.out.println("RESULT encripted:  " + response);

			String dencryptedMessage = pp.dencrypt(response);
			System.out.println("RESULT dencripted:  " + dencryptedMessage);

			

//			DESResponse resp = DESUtils.convertJsonToObject(dencryptedMessage, DESResponse.class);
//			
//			System.out.println(resp.toString());

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
