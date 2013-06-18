package coop.tecso.donde.estaciono.service;

import java.util.List;

import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.model.Country;

/**
 * 
 * @author joel.delvalle
 * 
 */
public interface ApplicationService {

	public List<Country> getCountryList() throws DondeEstacionoServerException;

}
