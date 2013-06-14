package coop.tecso.donde.estaciono.bean.common;

import coop.tecso.donde.estaciono.communication.DESRequest;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.model.common.GenericModel;

/**
 * 
 * @author joel.delvalle
 * 
 */
public interface GenericBean<T extends GenericModel> {

	public T convertToModelObject(DESRequest request) throws DondeEstacionoServerException;

	public void saveValidation(T value) throws DondeEstacionoServerException;

	public void saveExecute(T value) throws DondeEstacionoServerException;

	public void updateValidation(T value) throws DondeEstacionoServerException;

	public void updateExecute(T value) throws DondeEstacionoServerException;

	public void deleteValidation(T value) throws DondeEstacionoServerException;

	public void deleteExecute(T value) throws DondeEstacionoServerException;

}
