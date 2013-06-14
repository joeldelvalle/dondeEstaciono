package coop.tecso.donde.estaciono.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coop.tecso.donde.estaciono.communication.model.VehicleTypeRequest;
import coop.tecso.donde.estaciono.dao.ParkingDao;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.logger.CustomLogger;
import coop.tecso.donde.estaciono.model.Parking;
import coop.tecso.donde.estaciono.model.VehicleType;
import coop.tecso.donde.estaciono.service.CommunicatorConverterService;

/**
 * 
 * @author joel.delvalle
 * 
 */
@Service("communicatorVehicleTypeConverterService")
public class CommunicatorVehicleTypeConverterServiceImpl implements CommunicatorConverterService<VehicleTypeRequest, VehicleType> {

	private CustomLogger log = new CustomLogger(getClass().getCanonicalName());

	@Autowired
	private ParkingDao parkingDao;

	@Override
	public VehicleType convertToModelObject(VehicleTypeRequest requestObject) throws DondeEstacionoServerException {
		log.logStartMethod("convertToModelObject");

		Parking parking = this.parkingDao.findByIdentificationCode(requestObject.getParkingIdentificationCode());

		VehicleType vehicleType = new VehicleType(parking, requestObject.getDescription());

		log.logEndMethod("convertToModelObject");
		return vehicleType;
	}

}
