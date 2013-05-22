package coop.tecso.donde.estaciono.rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import coop.tecso.donde.estaciono.communication.DESRequest;
import coop.tecso.donde.estaciono.communication.DESResponse;
import coop.tecso.donde.estaciono.communication.model.web.LoginRequest;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.service.impl.EncryptServiceImpl;
import coop.tecso.donde.estaciono.service.impl.SecurityServiceImpl;
import coop.tecso.donde.estaciono.utils.DESUtils;

public class ConcurrenciaRestTest {

	EncryptServiceImpl pp = new EncryptServiceImpl();
	
	SecurityServiceImpl ss = new SecurityServiceImpl();

	public static void main(String... strings) {
		
		ConcurrenciaRestTest cc = new ConcurrenciaRestTest();
		cc.execute();
		
	}
	
	private void execute() {
		
		String login1 = this.loginToSend("jdelvalle");
		String login2 = this.loginToSend("asdasd");
		String login3 = this.loginToSend("qwer");
		String login4 = this.loginToSend("gromero");
		String login5 = this.loginToSend("nbvcxbcvx");
		
		String login6 = this.loginToSend("jdelvalle");
		String login7 = this.loginToSend("asdasd");
		String login8 = this.loginToSend("qwer");
		String login9 = this.loginToSend("gromero");
		String login10 = this.loginToSend("nbvcxbcvx");
		
		String login11 = this.loginToSend("nalderete");
		String login12 = this.loginToSend("asdasd");
		String login13 = this.loginToSend("jdelvalle");
		String login14 = this.loginToSend("gromero");
		String login15 = this.loginToSend("nbvcxbcvx");
		
		String login16 = this.loginToSend("nalderete");
		String login17 = this.loginToSend("asdasd");
		String login18 = this.loginToSend("jdelvalle");
		String login19 = this.loginToSend("gromero");
		String login20 = this.loginToSend("nbvcxbcvx");
		
		//*********************************
		
		Hilo _1 = this.new Hilo(1, login1, "jdelvalle");
		Hilo _2 = this.new Hilo(2, login2, "asdasd");
		Hilo _3 = this.new Hilo(3, login3, "qwer");
		Hilo _4 = this.new Hilo(4, login4, "gromero");
		Hilo _5 = this.new Hilo(5, login5, "nbvcxbcvx");
		
		Hilo _6 = this.new Hilo(6, login6, "jdelvalle");
		Hilo _7 = this.new Hilo(7, login7, "asdasd");
		Hilo _8 = this.new Hilo(8, login8, "qwer");
		Hilo _9 = this.new Hilo(9, login9, "gromero");
		Hilo _10 = this.new Hilo(10, login10, "nbvcxbcvx");

		Hilo _11 = this.new Hilo(11, login11, "nalderete");
		Hilo _12 = this.new Hilo(12, login12, "asdasd");
		Hilo _13 = this.new Hilo(13, login13, "jdelvalle");
		Hilo _14 = this.new Hilo(14, login14, "gromero");
		Hilo _15 = this.new Hilo(15, login15, "nbvcxbcvx");
		
		Hilo _16 = this.new Hilo(16, login16, "nalderete");
		Hilo _17 = this.new Hilo(17, login17, "asdasd");
		Hilo _18 = this.new Hilo(18, login18, "jdelvalle");
		Hilo _19 = this.new Hilo(19, login19, "gromero");
		Hilo _20 = this.new Hilo(20, login20, "nbvcxbcvx");

		//*********************************
		
		_1.start();
		_2.start();
		_3.start();
		_4.start();
		_5.start();
		
		_6.start();
		_7.start();
		_8.start();
		_9.start();
		_10.start();
		
		_11.start();
		_12.start();
		_13.start();
		_14.start();
		_15.start();
		
		_16.start();
		_17.start();
		_18.start();
		_19.start();
		_20.start();
		
	}
	
	
	
	private class Hilo extends Thread {
		
		private String data;
		
		private String user;
		
		private int nro;
		
		public Hilo(int nro, String data, String user) {
			this.data = data;
			this.user = user;
			this.nro = nro;
		}
		
		public void run() {
			
			Client client = Client.create();
			
			WebResource webResource = client.resource("http://localhost:8080/DondeEstacionoServer/rest/login/authentication");
			
			String response = webResource.type("application/json").post(String.class, data);
			
			String dencryptedMessage = pp.dencrypt(response);

			DESResponse resp = null;
			try {
				resp = DESUtils.convertJsonToObject(dencryptedMessage, DESResponse.class);
			} catch (DondeEstacionoServerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("nro: "  + nro + "  user:" + user + "    resp:" + resp.toString());
		}
	}

	
	
	private String loginToSend(String user) {
		
		LoginRequest login = new LoginRequest(); //"jdelvalle", "jdelvalle");
		login.setUser(user);
		login.setPassword(user);
		
		DESRequest request = new DESRequest();
		request.setUserHash("HASH-PUBLIC-WEB");
		request.setPayload(login);
		request.setMac(ss.buildMac(DESUtils.convertObjectToJson(login)));
		String requestJson = DESUtils.convertObjectToJson(request);
		
		return pp.encrypt(requestJson);
		
	}
	
}

