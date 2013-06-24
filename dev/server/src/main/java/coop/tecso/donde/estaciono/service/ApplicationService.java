package coop.tecso.donde.estaciono.service;

import java.util.List;

import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.model.Country;
import coop.tecso.donde.estaciono.model.Province;

/**
 * 
 * @author joel.delvalle
 * 
 */
public interface ApplicationService {

	public List<Country> getCountryList() throws DondeEstacionoServerException;

	public List<Province> getProvinceList() throws DondeEstacionoServerException;

	public List<Province> getProvinceListByCountry(Integer countryId) throws DondeEstacionoServerException;

}
