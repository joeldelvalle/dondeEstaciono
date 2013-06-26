package coop.tecso.donde.estaciono.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import coop.tecso.donde.estaciono.bean.common.GenericBean;
import coop.tecso.donde.estaciono.communication.DESRequest;
import coop.tecso.donde.estaciono.communication.model.FrequencyTypeRequest;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.model.FrequencyType;
import coop.tecso.donde.estaciono.service.CommunicatorConverterService;
import coop.tecso.donde.estaciono.service.FrequencyTypeService;

/**
 * 
 * @author joel.delvalle
 * 
 */
@Component
public class FrequencyTypeBean implements GenericBean<FrequencyType> {

	@Autowired
	@Qualifier("communicatorFrequencyTypeConverterService")
	private CommunicatorConverterService<FrequencyTypeRequest, FrequencyType> communicatorConverterService;

	@Autowired
	private FrequencyTypeService frequencyTypeService;
	
	@Override
	public FrequencyType convertToModelObject(DESRequest request) throws DondeEstacionoServerException {
		return this.communicatorConverterService.convertToModelObject(request.getPayload(FrequencyTypeRequest.class));
	}

	@Override
	public void saveValidation(FrequencyType frequencyType) throws DondeEstacionoServerException {
		this.frequencyTypeService.saveValidation(frequencyType);
	}

	@Override
	public void saveExecution(FrequencyType frequencyType) throws DondeEstacionoServerException {
		this.frequencyTypeService.save(frequencyType);
	}

	@Override
	public void updateValidation(FrequencyType FrequencyType) throws DondeEstacionoServerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateExecution(FrequencyType FrequencyType) throws DondeEstacionoServerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteValidation(FrequencyType FrequencyType) throws DondeEstacionoServerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteExecution(FrequencyType FrequencyType) throws DondeEstacionoServerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void findByParkingValidation(FrequencyType FrequencyType) throws DondeEstacionoServerException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<FrequencyType> findByParkingExecution(FrequencyType FrequencyType) throws DondeEstacionoServerException {
		// TODO Auto-generated method stub
		return null;
	}

}
