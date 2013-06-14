package coop.tecso.donde.estaciono.service;

import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;

/**
 * 
 * @author joel.delvalle
 * 
 */
public interface CommunicatorConverterService<RequestObject, ModelObject> {

	public ModelObject convertToModelObject(RequestObject requestObject) throws DondeEstacionoServerException;

}
