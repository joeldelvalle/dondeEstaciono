package coop.tecso.donde.estaciono.service.impl;

import coop.tecso.donde.estaciono.service.MobileRegisterService;

/**
 * 
 * @author joel.delvalle
 *
 */
public class MobileRegisterServiceImpl implements MobileRegisterService {

	public boolean register(String mobileNumber) {
		
		if (mobileNumber != null) {
			return true;
		}
		
		return false;
		
	}
	
}
