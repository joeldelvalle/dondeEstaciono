package coop.tecso.donde.estaciono.dao;

import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.model.MobileRegister;

/**
 * 
 * @author joel.delvalle
 * 
 */
public interface RegisterDao {

	public void save(MobileRegister mobileRegister) throws DondeEstacionoServerException;

}
