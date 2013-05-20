package coop.tecso.donde.estaciono.service.impl;

import org.springframework.stereotype.Service;

import coop.tecso.donde.estaciono.service.MobileRegisterService;

/**
 * 
 * @author joel.delvalle
 * 
 */
@Service
public class MobileRegisterServiceImpl implements MobileRegisterService {

	public boolean register(String mobileNumber) {

		if (mobileNumber != null) {
			return true;
		}

		return false;

	}

}
