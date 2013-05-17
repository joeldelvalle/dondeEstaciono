package coop.tecso.donde.estaciono.service;

import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.model.Login;

/**
 * 
 * @author joel.delvalle
 * 
 */
public interface LoginService {

	public Login authenticate(Login login) throws DondeEstacionoServerException;

}
