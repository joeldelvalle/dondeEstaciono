package coop.tecso.donde.estaciono.service;

import java.util.List;

import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.model.FrequencyType;

/**
 * 
 * @author joel.delvalle
 * 
 */
public interface FrequencyTypeService {

	public void save(FrequencyType frequencyType) throws DondeEstacionoServerException;

	public void saveValidation(FrequencyType frequencyType) throws DondeEstacionoServerException;

	public void findByParkingValidation(FrequencyType frequencyType) throws DondeEstacionoServerException;

	public List<FrequencyType> findByParking(FrequencyType frequencyType) throws DondeEstacionoServerException;

}
