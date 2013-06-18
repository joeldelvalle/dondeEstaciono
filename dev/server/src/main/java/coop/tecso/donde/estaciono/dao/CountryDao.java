package coop.tecso.donde.estaciono.dao;

import java.util.List;

import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.model.Country;

/**
 * 
 * @author joel.delvalle
 * 
 */
public interface CountryDao {

	public List<Country> findAll() throws DondeEstacionoServerException;

}
