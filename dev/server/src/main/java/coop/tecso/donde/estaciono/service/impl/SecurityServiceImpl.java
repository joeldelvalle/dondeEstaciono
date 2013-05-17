package coop.tecso.donde.estaciono.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import coop.tecso.donde.estaciono.dao.MobileHashDao;
import coop.tecso.donde.estaciono.dao.ParkingHashDao;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.logger.LoggerFactory;
import coop.tecso.donde.estaciono.model.Mac;
import coop.tecso.donde.estaciono.service.SecurityService;
import coop.tecso.donde.estaciono.utils.DESConstants;
import coop.tecso.donde.estaciono.utils.DESUtils;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class SecurityServiceImpl implements SecurityService {

	LoggerFactory log = LoggerFactory.getInstance(getClass());

	@Autowired
	private ParkingHashDao parkingHashDao;

	@Autowired
	private MobileHashDao mobileHashDao;

	private int keys[] = { 2, 7, 3, 5, 13, 21, 47, 31, 17, 23 };

	@Override
	public Mac buildMac(String json) {
		String method = "buildMac";
		log.logStartMethod(method);

		String jsonHex = this.convertStringToHex(json);

		String macFirstPart = this.getCalculateMac(jsonHex);

		String macSecondPart = this.sign(macFirstPart);

		String macComplete = macFirstPart.toUpperCase() + macSecondPart.toUpperCase();

		Mac mac = new Mac(macComplete);

		log.logEndMethod(method);
		return mac;
	}

	@Override
	public Boolean validateMac(Mac mac, String json) {
		log.logInfo("validateMac", "validate mac");

		if (!DESUtils.isNull(mac) && !DESUtils.isNull(json)) {
			return mac.equals(this.buildMac(json));
		}

		return Boolean.FALSE;
	}

	@Override
	public Boolean validatePublicWebHash(String hash) {
		log.logInfo("validatePublicWebHash", "validate public web hash");
		return DESConstants.Hash.PUBLIC_WEB.equals(hash);
	}

	@Override
	public Boolean validateParkingUserHash(String hash) throws DondeEstacionoServerException {
		log.logInfo("validateParkingUserHash", "validate parking user hash");
		return !DESUtils.isNull(this.parkingHashDao.validateHash(hash));
	}

	@Override
	public Boolean validateMobileClientHash(String hash) throws DondeEstacionoServerException {
		log.logInfo("validateMobileClienteHash", "validate mobile cliente hash");
		return !DESUtils.isNull(this.mobileHashDao.validateHash(hash));
	}

	private String convertStringToHex(String str) {

		char[] chars = str.toCharArray();

		StringBuffer strBuffer = new StringBuffer();

		for (int i = 0; i < chars.length; i++) {
			strBuffer.append(Integer.toHexString((int) chars[i]));
		}

		return strBuffer.toString();
	}

	private String getCalculateMac(String hexa) {

		String mac = hexa;
		while (mac.length() > 10) {

			Integer suma = 0;
			for (int i = 0; i < mac.length(); i++) {
				suma = suma + Integer.parseInt(mac.substring(i, i + 1), 16);
			}

			mac = convertStringToHex(suma.toString());

		}

		return mac;

	}

	private String sign(String str) {

		StringBuffer strBuffer = new StringBuffer();

		for (int i = 0; i < str.length(); i++) {
			strBuffer.append(Integer.parseInt(str.substring(i, i + 1), 16) * keys[i]);
		}

		return strBuffer.toString();

	}

}
