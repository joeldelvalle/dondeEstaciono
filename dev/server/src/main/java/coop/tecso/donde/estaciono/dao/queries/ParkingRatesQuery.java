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
import org.springframework.stereotype.Component;

import coop.tecso.donde.estaciono.model.FrequencyType;
import coop.tecso.donde.estaciono.model.ParkingRates;
import coop.tecso.donde.estaciono.model.VehicleType;
import coop.tecso.donde.estaciono.utils.DESConstants;

/**
 * 
 * @author german.romero
 *
 */
@Component
public interface ParkingRatesQuery {

	
	@Select("SELECT * " +
			"FROM parking_rates " +
			"WHERE state = '" + DESConstants.Database.States.ENABLED + "'")
	@Results(value = {
			@Result(property="stateDate", column="state_date")
		})
	public List<ParkingRates> findAllParkingRatesQuery() throws Exception;
	
	
	
	
	@Select("SELECT * " + 
			"FROM parking_rates vt, parking k " + 
			"WHERE k.identification_code = #{identificationCode} " + 
			"AND k.state = '" + DESConstants.Database.States.ENABLED + "' " + 
			"AND vt.id_parking = k.id " + 
			"AND vt.state ='" + DESConstants.Database.States.ENABLED + "'")
	@Results(value = {
			@Result(property = "vehicleType", column = "id_vehicle_type", javaType = VehicleType.class, one = @One(select = "findVehicleTypeQuery")),
			@Result(property = "frequencyType", column = "id_frequency_type", javaType = FrequencyType.class, one = @One(select = "findFrequencyTypeQuery")),
			@Result(property="stateDate", column="state_date")
			}
	)
	public List<ParkingRates> findByParkingQuery(String identificationCode) throws Exception;
	

	
	
	@Select("SELECT * " + 
			"FROM vehicle_type vt " + 
			"WHERE vt.id = #{id} " +
			"AND vt.state ='" + DESConstants.Database.States.ENABLED + "'")
	@Results(value = {
			@Result(property="stateDate", column="state_date")
			}
	)
	VehicleType findVehicleTypeQuery(@Param("id") Long id);
	
	
	
	@Select("SELECT * " + 
			"FROM frequency_type vt " + 
			"WHERE vt.id = #{id} " +
			"AND vt.state ='" + DESConstants.Database.States.ENABLED + "'")
	@Results(value = {
			@Result(property="stateDate", column="state_date")
			}
	)
	FrequencyType findFrequencyTypeQuery(@Param("id") Long id);
	
	
	
	@Insert("INSERT INTO parking_rates " +
			"VALUES (#{id}, #{parking.id},  #{vehicleType.id}, #{frequencyType.id}, #{amount}, #{state}, #{stateDate})")
	@SelectKey(statement = "call identity()", keyProperty = "id", before = false, resultType = long.class)
	public void saveQuery(ParkingRates parkingRates) throws Exception;
	
	
	
	
	
	@Select("SELECT * " + 
			"FROM parking_rates vt, parking k " + 
			"WHERE k.identification_code = #{parking.identificationCode} " + 
			"AND k.state = '" + DESConstants.Database.States.ENABLED + "' " + 
			"AND vt.id_parking = k.id " + 
			"AND vt.id_vehicle_type = #{vehicleType.id} " +
			"AND vt.id_frequency_type = #{frequencyType.id} " +
			"AND vt.state ='" + DESConstants.Database.States.ENABLED + "'")
	public ParkingRates existsInDatabaseToSaveQuery(ParkingRates parkingRates) throws Exception;

	
	
	
	
	@Select("SELECT * " + 
			"FROM parking_rates vt, parking k " + 
			"WHERE k.identification_code = #{parking.identificationCode} " + 
			"AND k.state = '" + DESConstants.Database.States.ENABLED + "' " + 
			"AND vt.id_parking = k.id " + 
			"AND vt.id = #{id} " + 
			"AND vt.state ='" + DESConstants.Database.States.ENABLED + "'")
	public ParkingRates existsInDatabaseToUpdateQuery(ParkingRates parkingRates) throws Exception;
	
	
	
	
	
	@Update("UPDATE parking_rates " + 
			"SET id_vehicle_type = #{vehicleType.id}, " +
			"id_frequency_type = #{frequencyType.id}, " +
			"amount = #{amount}, " +
			"state_date = #{stateDate} " +
			"WHERE id = #{id}")
	public void updateQuery(ParkingRates parkingRates);


	
	
	
	@Update("UPDATE parking_rates " + 
			"SET state = #{state}, " +
			"state_date = #{stateDate} " +
			"WHERE id = #{id}")
	public void deleteQuery(ParkingRates parkingRates);
	
	
	
	
	
	@Select("SELECT * " + 
			"FROM parking_rates vt, parking k " + 
			"WHERE k.identification_code = #{identificationCode} " + 
			"AND k.state = '" + DESConstants.Database.States.ENABLED + "' " + 
			"AND vt.id_parking = k.id " +
			"AND vt.id = #{id} " +
			"AND vt.state ='" + DESConstants.Database.States.ENABLED + "'")
	@Results(value = {
			@Result(property = "vehicleType", column = "id_vehicle_type", javaType = VehicleType.class, one = @One(select = "findVehicleTypeQuery")),
			@Result(property = "frequencyType", column = "id_frequency_type", javaType = FrequencyType.class, one = @One(select = "findFrequencyTypeQuery")),
			@Result(property="stateDate", column="state_date")
			}
	)
	public ParkingRates findByParkingByIdQuery(@Param("identificationCode") String identificationCode, @Param("id") Long id);


	
	

	@Select("SELECT * " + 
			"FROM parking_rates pr, parking k " + 
			"WHERE k.identification_code = #{parking.identificationCode} " + 
			"AND k.state = '" + DESConstants.Database.States.ENABLED + "' " + 
			"AND pr.id != #{id} " + 
			"AND pr.id_parking = k.id " + 
			"AND pr.id_vehicle_type = #{vehicleType.id} " +
			"AND pr.id_frequency_type = #{frequencyType.id} " +
			"AND pr.state ='" + DESConstants.Database.States.ENABLED + "'")
	public ParkingRates existsOtherEqualsInDatabaseBeforeUpdate(ParkingRates parkingRates);
}
