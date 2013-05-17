package coop.tecso.donde.estaciono.security;

import org.junit.Test;

import coop.tecso.donde.estaciono.model.Mac;
import coop.tecso.donde.estaciono.service.impl.SecurityServiceImpl;



public class SecurityServiceImplTest {

//	private static int keys[] = { 2, 7, 3, 5, 13, 21, 47, 31, 17, 23};
	
	@Test
	public void calculateMac() {

		SecurityServiceImpl ss = new SecurityServiceImpl();
		
		Mac mac = ss.buildMac("JOEL");
		
		System.out.println(mac.getMac());
		
	}

//	static String stringToHex(String str) {
//		char[] chars = str.toCharArray();
//		StringBuffer strBuffer = new StringBuffer();
//		for (int i = 0; i < chars.length; i++) {
//			strBuffer.append(Integer.toHexString((int) chars[i]));
//		}
//		return strBuffer.toString();
//	}
//	
//	public static void main(String[] args) {
//		
//		SecurityServiceImplTest obj = new SecurityServiceImplTest();
//		
//		Login login = new Login("jdelvalle", "jdelvalle");
////		String requestJson = DondeEstacionoServerUtils.convertObjectToJson(login);
//		
//		UserServiceImpl pp = new UserServiceImpl();
//		User user = pp.findMockUser();
//		String requestJson = DondeEstacionoServerUtils.convertObjectToJson(user);
//
//		System.out.println("Text" + requestJson);
//		
//		String hexString = obj.stringToHex(requestJson);
//		System.out.println("hexa : " + hexString.toUpperCase());
//		
//		String mac = calculateMac(hexString);
//		System.out.println(mac);
//		
//		mac = mac + sign(mac);
//		
//		System.out.println("mac final: " + mac);
//	}
//	
//	private static String calculateMac(String str) {
//		
//		String mac = str;
//		while (mac.length() > 10) {
//		
//			Integer suma = 0;
//			for (int i = 0; i < mac.length(); i++) {
//				 String digit = mac.substring(i, i+1);
//				 suma = suma + Integer.parseInt(digit, 16);
//			}
//			
//			mac = stringToHex(suma.toString());
//			
//		}
//		
////		System.out.println(mac);
//		
//		return mac;
//	}
//	
//	private static String sign(String mac) {
//		
//		char[] chars = mac.toCharArray();
//		StringBuffer strBuffer = new StringBuffer();
//		for (int i = 0; i < mac.length(); i++) {
//			 String digit = mac.substring(i, i+1);
//			 strBuffer.append(Integer.parseInt(digit, 16) * keys[i]);
//		}
//		
//		return strBuffer.toString();
//		
//	}

}
