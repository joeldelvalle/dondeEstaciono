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

	List<Locality> findAll() throws DondeEstacionoServerException;

}
