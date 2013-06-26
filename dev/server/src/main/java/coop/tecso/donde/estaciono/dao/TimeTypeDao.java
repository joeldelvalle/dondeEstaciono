package coop.tecso.donde.estaciono.dao;

import java.util.List;

import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.model.TimeType;

/**
 * 
 * @author joel.delvalle
 * 
 */
public interface TimeTypeDao {

	public List<TimeType> findAll() throws DondeEstacionoServerException;

	public TimeType findById(Integer id) throws DondeEstacionoServerException;

}
