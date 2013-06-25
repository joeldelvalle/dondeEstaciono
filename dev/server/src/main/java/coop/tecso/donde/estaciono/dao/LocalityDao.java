package coop.tecso.donde.estaciono.dao;

import java.util.List;

import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.model.Locality;

/**
 * 
 * @author joel.delvalle
 * 
 */
public interface LocalityDao {

	public List<Locality> findAll() throws DondeEstacionoServerException;

	public List<Locality> findByProvince(Integer provinceId) throws DondeEstacionoServerException;

	public List<Locality> findByProvinceByCountry(Integer provinceId, Integer countryId) throws DondeEstacionoServerException;

}
