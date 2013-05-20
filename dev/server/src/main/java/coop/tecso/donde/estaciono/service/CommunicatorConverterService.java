package coop.tecso.donde.estaciono.service;


/**
 * 
 * @author joel.delvalle
 * 
 */
public interface CommunicatorConverterService<RequestObject, ModelObject> {

	public ModelObject convertToModelObject(RequestObject requestObject);
	
}
