package coop.tecso.donde.estaciono.dao;

import coop.tecso.donde.estaciono.dao.common.GenericDao;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.model.FrequencyType;

/**
 * 
 * @author joel.delvalle
 * 
 */
public interface FrequencyTypeDao extends GenericDao<FrequencyType> {

	public Boolean existsInDatabaseToSave(FrequencyType frequencyType) throws DondeEstacionoServerException;

	public Boolean existsFrequencyWithSamePriority(FrequencyType frequencyType) throws DondeEstacionoServerException;

	public Boolean existsInDatabaseToUpdateOrDelete(FrequencyType frequencyType) throws DondeEstacionoServerException;

}
