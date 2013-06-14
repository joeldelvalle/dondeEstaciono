package coop.tecso.donde.estaciono.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import coop.tecso.donde.estaciono.bean.common.GenericBean;
import coop.tecso.donde.estaciono.communication.DESRequest;
import coop.tecso.donde.estaciono.communication.model.VehicleTypeRequest;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.model.VehicleType;
import coop.tecso.donde.estaciono.service.CommunicatorConverterService;
import coop.tecso.donde.estaciono.service.VehicleTypeService;

/**
 * 
 * @author joel.delvalle
 * 
 */
@Component
public class VehicleTypeBean implements GenericBean<VehicleType> {

	@Autowired
	@Qualifier("communicatorVehicleTypeConverterService")
	private CommunicatorConverterService<VehicleTypeRequest, VehicleType> communicatorConverterService;

	@Autowired
	private VehicleTypeService vehicleTypeService;

	@Override
	public VehicleType convertToModelObject(DESRequest request) throws DondeEstacionoServerException {
		return this.communicatorConverterService.convertToModelObject(request.getPayload(VehicleTypeRequest.class));
	}

	@Override
	public void saveValidation(VehicleType vehicleType) throws DondeEstacionoServerException {
		this.vehicleTypeService.validate(vehicleType);
	}

	@Override
	public void saveExecute(VehicleType vehicleType) throws DondeEstacionoServerException {
		this.vehicleTypeService.save(vehicleType);
	}

	@Override
	public void updateValidation(VehicleType vehicleType) throws DondeEstacionoServerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateExecute(VehicleType vehicleType) throws DondeEstacionoServerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteValidation(VehicleType vehicleType) throws DondeEstacionoServerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteExecute(VehicleType vehicleType) throws DondeEstacionoServerException {
		// TODO Auto-generated method stub

	}

}