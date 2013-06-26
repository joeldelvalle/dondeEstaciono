package coop.tecso.donde.estaciono.dao;

import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.model.FrequencyType;

/**
 * 
 * @author joel.delvalle
 * 
 */
public interface FrequencyTypeDao {

	public Boolean existsInDatabaseToSave(FrequencyType frequencyType) throws DondeEstacionoServerException;

	public Boolean existsFrequencyWithSamePriority(FrequencyType frequencyType) throws DondeEstacionoServerException;

}
