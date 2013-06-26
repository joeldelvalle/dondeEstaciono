package coop.tecso.donde.estaciono.dao.queries;

import org.apache.ibatis.annotations.Select;

import coop.tecso.donde.estaciono.model.FrequencyType;
import coop.tecso.donde.estaciono.utils.DESConstants;

/**
 * 
 * @author joel.delvalle
 *
 */
public interface FrequencyTypeQuery {

	
	@Select("SELECT * " + 
			"FROM frequency_type ft, parking k " + 
			"WHERE k.identification_code = #{parking.identificationCode} " + 
			"AND k.state = '" + DESConstants.Database.States.ENABLED + "' " + 
			"AND ft.id_parking = k.id " + 
			"AND ft.description = #{description} " +
			"AND ft.type = #{type} " +
			"AND ft.time = #{time} " +
			"AND ft.id_time_type = #{timeType.id} " +
			"AND ft.priority = #{priority} " + 
			"AND ft.state ='" + DESConstants.Database.States.ENABLED + "'")
	public FrequencyType existsInDatabaseToSaveQuery(FrequencyType frequencyType) throws Exception;

	
	
	
	@Select("SELECT * " + 
			"FROM frequency_type ft, parking k " + 
			"WHERE k.identification_code = #{parking.identificationCode} " + 
			"AND k.state = '" + DESConstants.Database.States.ENABLED + "' " + 
			"AND ft.id_parking = k.id " + 
			"AND ft.priority = #{priority} " + 
			"AND ft.state ='" + DESConstants.Database.States.ENABLED + "'")
	public FrequencyType existsWithSamePriorityQuery(FrequencyType frequencyType);
	
}
