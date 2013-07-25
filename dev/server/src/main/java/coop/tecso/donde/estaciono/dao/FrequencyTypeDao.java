package coop.tecso.donde.estaciono.dao;

import coop.tecso.donde.estaciono.dao.common.GenericDao;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.model.FrequencyType;
import coop.tecso.donde.estaciono.utils.DESConstants;

/**
 * 
 * @author joel.delvalle
 * 
 */
public interface FrequencyTypeDao extends GenericDao<FrequencyType> {

	public Boolean existsInDatabaseToSave(FrequencyType frequencyType) throws DondeEstacionoServerException;

	public Boolean existsFrequencyWithSamePriority(FrequencyType frequencyType, DESConstants.Action action) throws DondeEstacionoServerException;

	public Boolean existsInDatabaseToUpdateOrDelete(FrequencyType frequencyType) throws DondeEstacionoServerException;

	public FrequencyType findByParkingById(String identificationCode, Long id) throws DondeEstacionoServerException;

}
