package coop.tecso.donde.estaciono.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coop.tecso.donde.estaciono.communication.model.FrequencyTypeRequest;
import coop.tecso.donde.estaciono.dao.ParkingDao;
import coop.tecso.donde.estaciono.dao.TimeTypeDao;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.logger.CustomLogger;
import coop.tecso.donde.estaciono.model.FrequencyType;
import coop.tecso.donde.estaciono.model.Parking;
import coop.tecso.donde.estaciono.model.TimeType;
import coop.tecso.donde.estaciono.service.CommunicatorConverterService;

/**
 * 
 * @author joel.delvalle
 * 
 */
@Service("communicatorFrequencyTypeConverterService")
public class CommunicatorFrequencyTypeConverterServiceImpl implements CommunicatorConverterService<FrequencyTypeRequest, FrequencyType> {

	private CustomLogger log = new CustomLogger(getClass().getCanonicalName());

	@Autowired
	private ParkingDao parkingDao;

	@Autowired
	private TimeTypeDao timeTypeDao;

	@Override
	public FrequencyType convertToModelObject(FrequencyTypeRequest requestObject) throws DondeEstacionoServerException {
		log.logStartMethod("convertToModelObject");

		Parking parking = this.parkingDao.findByIdentificationCode(requestObject.getParkingIdentificationCode());

		TimeType timeType = this.timeTypeDao.findById(requestObject.getIdTimeType());

		FrequencyType frequencyType = new FrequencyType(requestObject.getId(), parking, requestObject.getDescription(), requestObject.getType(),
				requestObject.getTime(), timeType, requestObject.getPriority(), requestObject.getCombinablePreviousFrequency());

		log.logEndMethod("convertToModelObject");
		return frequencyType;
	}

}
