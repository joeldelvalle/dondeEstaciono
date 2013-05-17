package coop.tecso.donde.estaciono.dao;

import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.model.Login;

/**
 * 
 * @author joel.delvalle
 * 
 */
public interface LoginDao {

	public Login authenticate(Login login) throws DondeEstacionoServerException;

}
