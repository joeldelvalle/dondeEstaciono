package coop.tecso.donde.estaciono.service.impl;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import coop.tecso.donde.estaciono.service.EncryptService;

/**
 * 
 * @author joel.delvalle
 * 
 */
@Service
public class EncryptServiceImpl implements EncryptService {

	public static final String SEED_KEY = "tiriTiriTiriGadgetGadget";

	public int seed = 0;	
	
	public String encrypt(String message) {
		StringBuilder encrypt = new StringBuilder();

		for (int i = 0; i < message.length(); i++) {
			char currentChar = message.charAt(i);
			encrypt.append(currentChar + getSeed());
		}

		return Base64.encodeBase64String(encrypt.toString().getBytes());
	}
	
	public String dencrypt(String message) {
		
		String decodeMessage = new String(Base64.decodeBase64(message));
		
		StringBuilder dencrypt = new StringBuilder();

		for (int i = 0; i < decodeMessage.length(); i = i + 4) {
			
			String encoded = decodeMessage.substring(i, i + 4);
			
			int charDecoded = Integer.valueOf(encoded) - getSeed();
			
			 dencrypt.append((char)charDecoded);
		}

		return dencrypt.toString();
	}

	private int getSeed() {

		if (seed == 0) {
			for (int i = 0; i < SEED_KEY.length(); i++) {
				char currentChar = SEED_KEY.charAt(i);
				seed = seed + currentChar;
			}
		}

		return seed;
	}

}
