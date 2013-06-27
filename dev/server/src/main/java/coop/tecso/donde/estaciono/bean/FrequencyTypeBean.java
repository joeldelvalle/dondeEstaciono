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
	public void updateValidation(FrequencyType frequencyType) throws DondeEstacionoServerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateExecution(FrequencyType frequencyType) throws DondeEstacionoServerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteValidation(FrequencyType frequencyType) throws DondeEstacionoServerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteExecution(FrequencyType frequencyType) throws DondeEstacionoServerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void findByParkingValidation(FrequencyType frequencyType) throws DondeEstacionoServerException {
		this.frequencyTypeService.findByParkingValidation(frequencyType);
	}

	@Override
	public List<FrequencyType> findByParkingExecution(FrequencyType frequencyType) throws DondeEstacionoServerException {
		return this.frequencyTypeService.findByParking(frequencyType);
	}

}
