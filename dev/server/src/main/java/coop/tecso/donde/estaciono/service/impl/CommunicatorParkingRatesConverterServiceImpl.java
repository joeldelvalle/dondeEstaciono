package coop.tecso.donde.estaciono.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coop.tecso.donde.estaciono.communication.model.ParkingRatesRequest;
import coop.tecso.donde.estaciono.dao.FrequencyTypeDao;
import coop.tecso.donde.estaciono.dao.ParkingDao;
import coop.tecso.donde.estaciono.dao.VehicleTypeDao;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.logger.CustomLogger;
import coop.tecso.donde.estaciono.model.FrequencyType;
import coop.tecso.donde.estaciono.model.Parking;
import coop.tecso.donde.estaciono.model.ParkingRates;
import coop.tecso.donde.estaciono.model.VehicleType;
import coop.tecso.donde.estaciono.service.CommunicatorConverterService;

/**
 * 
 * @author german.romero
 * 
 */
@Service("communicatorParkingRatesConverterService")
public class CommunicatorParkingRatesConverterServiceImpl implements CommunicatorConverterService<ParkingRatesRequest, ParkingRates> {

	private CustomLogger log = new CustomLogger(getClass().getCanonicalName());
	
	@Autowired
	private ParkingDao parkingDao;
	
	@Autowired
	private VehicleTypeDao vehicleTypeDao;
	
	@Autowired
	private FrequencyTypeDao frequencyTypeDao;

	@Override
	public ParkingRates convertToModelObject(ParkingRatesRequest requestObject) throws DondeEstacionoServerException {
		log.logStartMethod("convertToModelObject");

		Parking parking = this.parkingDao.findByIdentificationCode(requestObject.getParkingIdentificationCode());
		
		VehicleType vehicleType = null;
		if (requestObject.getVehicleType() != null) {			
			vehicleType = this.vehicleTypeDao.findByParkingById(requestObject.getParkingIdentificationCode(), requestObject.getVehicleType());
		}
		
		FrequencyType frequencyType = null;
		if (requestObject.getFrequencyType() != null) {			
			frequencyType = this.frequencyTypeDao.findByParkingById(requestObject.getParkingIdentificationCode(), requestObject.getFrequencyType());
		}

		ParkingRates parkRates = new ParkingRates(requestObject.getId(), parking, vehicleType, frequencyType, requestObject.getAmount());

		log.logEndMethod("convertToModelObject");
		return parkRates;
	}

}
