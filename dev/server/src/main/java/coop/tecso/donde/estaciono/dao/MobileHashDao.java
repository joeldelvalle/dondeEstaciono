package coop.tecso.donde.estaciono.dao;

import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.model.MobileHash;

/**
 * 
 * @author joel.delvalle
 * 
 */
public interface MobileHashDao {

	public void save(MobileHash hash) throws DondeEstacionoServerException;

	public MobileHash validateHash(String hash) throws DondeEstacionoServerException;

}
