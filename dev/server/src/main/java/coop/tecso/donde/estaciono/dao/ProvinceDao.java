package coop.tecso.donde.estaciono.dao;

import java.util.List;

import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.model.Province;

/**
 * 
 * @author joel.delvalle
 * 
 */
public interface ProvinceDao {

	List<Province> findAll() throws DondeEstacionoServerException;

	List<Province> findByCountryId(Integer countryId) throws DondeEstacionoServerException;

}
