package coop.tecso.donde.estaciono.service;

import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.model.MobileHash;
import coop.tecso.donde.estaciono.model.MobileRegister;

/**
 * 
 * @author joel.delvalle
 * 
 */
public interface RegisterService {

	public MobileHash mobileRegister(MobileRegister mobileRegister) throws DondeEstacionoServerException;

}
