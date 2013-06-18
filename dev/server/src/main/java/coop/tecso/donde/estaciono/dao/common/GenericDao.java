package coop.tecso.donde.estaciono.dao.common;

import java.util.List;

import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;

/**
 * 
 * @author joel.delvalle
 * 
 */
public interface GenericDao<T> {

	public void save(T value) throws DondeEstacionoServerException;

	public void update(T value) throws DondeEstacionoServerException;

	public void delete(T value) throws DondeEstacionoServerException;

	public List<T> findAll() throws DondeEstacionoServerException;

	public List<T> findByParking(String identificationCode) throws DondeEstacionoServerException;

}
