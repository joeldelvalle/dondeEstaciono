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

	public void saveExecution(T value) throws DondeEstacionoServerException;

	public void updateValidation(T value) throws DondeEstacionoServerException;

	public void updateExecution(T value) throws DondeEstacionoServerException;

	public void deleteValidation(T value) throws DondeEstacionoServerException;

	public void deleteExecution(T value) throws DondeEstacionoServerException;

}
