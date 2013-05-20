package coop.tecso.donde.estaciono.service.impl;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import coop.tecso.donde.estaciono.model.MobileHash;
import coop.tecso.donde.estaciono.model.MobileRegister;
import coop.tecso.donde.estaciono.model.ParkingHash;
import coop.tecso.donde.estaciono.service.HashGeneratorService;

/**
 * 
 * @author joel.delvalle
 * 
 */
@Service
public class HashGeneratorServiceImpl implements HashGeneratorService {

	@Override
	public MobileHash generateMobileHash(MobileRegister mobileRegister) {

		String input = mobileRegister.getNumber();
		String hash = this.createHash(input);

		MobileHash mobileHash = new MobileHash();
		mobileHash.setHash(hash);

		return mobileHash;
	}

	@Override
	public ParkingHash generateParkingHas(ParkingHash parkingHash) {
		// TODO Auto-generated method stub
		return null;
	}

	private String createHash(String input) {

		try {

			IvParameterSpec iv = new IvParameterSpec(new byte[8]);

			SecretKey key = getKey();

			Cipher encrypter = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			encrypter.init(Cipher.ENCRYPT_MODE, key, iv);

			byte[] inputResult = input.getBytes("UTF-8");

			byte[] encrypted = encrypter.doFinal(inputResult);

			return String.valueOf(encrypted);

		} catch (Exception e) {
			return String.valueOf(input.hashCode());
		}

	}

	private SecretKey getKey() throws NoSuchAlgorithmException {
		String keyString = "sonDePlataYDeAceroSilverHawks";

		byte[] keyB = new byte[24];

		for (int i = 0; i < keyString.length() && i < keyB.length; i++) {
			keyB[i] = (byte) keyString.charAt(i);
		}

		SecretKey key = new SecretKeySpec(keyB, "DESede");

		return key;
	}

}
