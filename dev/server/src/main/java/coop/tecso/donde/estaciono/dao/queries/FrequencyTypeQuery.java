package coop.tecso.donde.estaciono.dao.queries;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import coop.tecso.donde.estaciono.model.FrequencyType;
import coop.tecso.donde.estaciono.model.TimeType;
import coop.tecso.donde.estaciono.utils.DESConstants;

/**
 * 
 * @author joel.delvalle
 *
 */
public interface FrequencyTypeQuery extends TimeTypeQuery {

	
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
	public FrequencyType existsWithSamePriorityToSaveQuery(FrequencyType frequencyType) throws Exception;

	
	
	@Select("SELECT * " + 
			"FROM frequency_type ft, parking k " + 
			"WHERE k.identification_code = #{parking.identificationCode} " + 
			"AND k.state = '" + DESConstants.Database.States.ENABLED + "' " + 
			"AND ft.id != #{id} " + 
			"AND ft.id_parking = k.id " +
			"AND ft.priority = #{priority} " + 
			"AND ft.state ='" + DESConstants.Database.States.ENABLED + "'")
	public FrequencyType existsWithSamePriorityToUpdateOrDeleteQuery(FrequencyType frequencyType) throws Exception;



	@Insert("INSERT INTO frequency_type " +
			"VALUES (#{id}, #{parking.id}, #{description}, #{type}, #{time}, #{timeType.id}, #{priority}, #{combinablePreviousFrequency}, #{state}, #{stateDate})")
	@SelectKey(statement = "call identity()", keyProperty = "id", before = false, resultType = long.class)
	public void saveQuery(FrequencyType frequencyType) throws Exception;




	@Select("SELECT * " + 
			"FROM frequency_type ft, parking k " + 
			"WHERE k.identification_code = #{identificationCode} " + 
			"AND k.state = '" + DESConstants.Database.States.ENABLED + "' " + 
			"AND ft.id_parking = k.id " + 
			"AND ft.state ='" + DESConstants.Database.States.ENABLED + "'")
	@Results(value = {
			@Result(property="combinablePreviousFrequency", column="combinable_previous_freq"),
			@Result(property = "timeType", column = "id_time_type", javaType = TimeType.class, one = @One(select = "findTimeTypeById")),
			@Result(property="stateDate", column="state_date")
			}
	)
	public List<FrequencyType> findByParkingQuery(String identificationCode) throws Exception;

	

	@Select("SELECT * " + 
			"FROM frequency_type ft, parking k " + 
			"WHERE k.identification_code = #{parking.identificationCode} " + 
			"AND k.state = '" + DESConstants.Database.States.ENABLED + "' " + 
			"AND ft.id_parking = k.id " + 
			"AND ft.id = #{id} " + 
			"AND ft.state ='" + DESConstants.Database.States.ENABLED + "'")
	public FrequencyType existsInDatabaseToUpdateQuery(FrequencyType frequencyType);



	@Update("UPDATE frequency_type " + 
			"SET description = #{description}, " +
			"type = #{type}, " +
			"time = #{time}, " +
			"id_time_type = #{timeType.id}, " + 
			"priority = #{priority}, " +
			"combinable_previous_freq = #{combinablePreviousFrequency}, " +
			"state_date = #{stateDate} " +
			"WHERE id = #{id}")
	public void updateQuery(FrequencyType frequencyType);



	@Update("UPDATE frequency_type " + 
			"SET state = #{state}, " +
			"state_date = #{stateDate} " +
			"WHERE id = #{id}")
	public void deleteQuery(FrequencyType frequencyType);



	@Select("SELECT * " + 
			"FROM frequency_type ft, parking k " + 
			"WHERE k.identification_code = #{identificationCode} " + 
			"AND k.state = '" + DESConstants.Database.States.ENABLED + "' " + 
			"AND ft.id_parking = k.id " +
			"AND ft.id = #{id} " + 
			"AND ft.state ='" + DESConstants.Database.States.ENABLED + "'")
	@Results(value = {
			@Result(property="combinablePreviousFrequency", column="combinable_previous_freq"),
			@Result(property = "timeType", column = "id_time_type", javaType = TimeType.class, one = @One(select = "findTimeTypeById")),
			@Result(property="stateDate", column="state_date")
			}
	)
	public FrequencyType findByParkingByIdQuery(@Param("identificationCode") String identificationCode, @Param("id") Long id);
	
	
}
