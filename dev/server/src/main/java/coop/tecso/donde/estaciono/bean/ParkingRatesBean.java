package coop.tecso.donde.estaciono.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import coop.tecso.donde.estaciono.bean.common.GenericBean;
import coop.tecso.donde.estaciono.communication.DESRequest;
import coop.tecso.donde.estaciono.communication.model.ParkingRatesRequest;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.model.ParkingRates;
import coop.tecso.donde.estaciono.service.CommunicatorConverterService;
import coop.tecso.donde.estaciono.service.ParkingRatesService;

/**
 * 
 * @author german.romero
 * 
 */
@Component
public class ParkingRatesBean implements GenericBean<ParkingRates> {

	@Autowired
	@Qualifier("communicatorParkingRatesConverterService")
	private CommunicatorConverterService<ParkingRatesRequest, ParkingRates> communicatorConverterService;

	@Autowired
	private ParkingRatesService parkingRatesService;

	@Override
	public ParkingRates convertToModelObject(DESRequest request) throws DondeEstacionoServerException {
		return this.communicatorConverterService.convertToModelObject(request.getPayload(ParkingRatesRequest.class));
	}

	@Override
	public void saveValidation(ParkingRates parkingRates) throws DondeEstacionoServerException {
		this.parkingRatesService.saveValidation(parkingRates);
	}

	@Override
	public void saveExecution(ParkingRates parkingRates) throws DondeEstacionoServerException {
		this.parkingRatesService.save(parkingRates);
	}

	@Override
	public void updateValidation(ParkingRates parkingRates) throws DondeEstacionoServerException {
		this.parkingRatesService.updateValidation(parkingRates);
	}

	@Override
	public void updateExecution(ParkingRates parkingRates) throws DondeEstacionoServerException {
		this.parkingRatesService.update(parkingRates);
	}

	@Override
	public void deleteValidation(ParkingRates parkingRates) throws DondeEstacionoServerException {
		this.parkingRatesService.deleteValidation(parkingRates);
	}

	@Override
	public void deleteExecution(ParkingRates parkingRates) throws DondeEstacionoServerException {
		this.parkingRatesService.delete(parkingRates);
	}

	@Override
	public void findByParkingValidation(ParkingRates parkingRates) throws DondeEstacionoServerException {
		this.parkingRatesService.findByParkingValidation(parkingRates);
	}

	@Override
	public List<ParkingRates> findByParkingExecution(ParkingRates parkingRates) throws DondeEstacionoServerException {
		return this.parkingRatesService.findByParking(parkingRates);
	}

	@Override
	public ParkingRates findByParkingByIdExecution(ParkingRates parkingRates) throws DondeEstacionoServerException {
		return this.parkingRatesService.findByParkingById(parkingRates);
	}

}