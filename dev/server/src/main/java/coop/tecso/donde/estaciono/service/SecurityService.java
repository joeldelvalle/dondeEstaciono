package coop.tecso.donde.estaciono.service;

import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.model.Mac;

/**
 * 
 * @author joel.delvalle
 * 
 */
public interface SecurityService {

	public Mac buildMac(String json);

	public Boolean validateMac(Mac mac, String json);

	public Boolean validatePublicWebHash(String hash);

	public Boolean validateParkingUserHash(String hash) throws DondeEstacionoServerException;

	public Boolean validateMobileClientHash(String hash) throws DondeEstacionoServerException;

}
