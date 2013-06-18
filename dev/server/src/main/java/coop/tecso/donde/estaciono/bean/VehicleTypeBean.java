package coop.tecso.donde.estaciono.bean;

import java.util.List;

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
		this.vehicleTypeService.saveValidation(vehicleType);
	}

	@Override
	public void saveExecution(VehicleType vehicleType) throws DondeEstacionoServerException {
		this.vehicleTypeService.save(vehicleType);
	}

	@Override
	public void updateValidation(VehicleType vehicleType) throws DondeEstacionoServerException {
		this.vehicleTypeService.updateValidation(vehicleType);

	}

	@Override
	public void updateExecution(VehicleType vehicleType) throws DondeEstacionoServerException {
		this.vehicleTypeService.update(vehicleType);
	}

	@Override
	public void deleteValidation(VehicleType vehicleType) throws DondeEstacionoServerException {
		this.vehicleTypeService.deleteValidation(vehicleType);
	}

	@Override
	public void deleteExecution(VehicleType vehicleType) throws DondeEstacionoServerException {
		this.vehicleTypeService.delete(vehicleType);
	}

	@Override
	public void findByParkingValidation(VehicleType vehicleType) throws DondeEstacionoServerException {
		this.vehicleTypeService.findByParkingValidation(vehicleType);
	}

	@Override
	public List<VehicleType> findByParkingExecution(VehicleType vehicleType) throws DondeEstacionoServerException {
		return this.vehicleTypeService.findByParking(vehicleType);
	}

}