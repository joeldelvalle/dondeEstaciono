package coop.tecso.donde.estaciono.dao.queries;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import coop.tecso.donde.estaciono.dao.queries.common.GenericQuery;
import coop.tecso.donde.estaciono.model.Parking;
import coop.tecso.donde.estaciono.model.VehicleType;
import coop.tecso.donde.estaciono.utils.DESConstants;

/**
 * 
 * @author joel.delvalle
 * 
 */
@Component
public interface VehicleTypeQuery extends GenericQuery {

	@Insert("INSERT INTO vehicle_type " +
			"VALUES (#{id}, #{parking.id}, #{description}, #{state}, #{stateDate})")
	@SelectKey(statement = "call identity()", keyProperty = "id", before = false, resultType = long.class)
	public void saveQuery(VehicleType vehicleType) throws Exception;

	
	
	@Select("SELECT * " + 
			"FROM vehicle_type " + 
			"WHERE state ='" + DESConstants.Database.States.ENABLED + "'")
	@Results(value = {
			@Result(property="stateDate", column="state_date"),
			@Result(property = "parking", column = "id_parking", javaType = Parking.class, one = @One(select = "findParkingById")) 
			}
	)
	public List<VehicleType> findAllQuery() throws Exception;

	
	
	@Select("SELECT * " + 
			"FROM vehicle_type vt, parking k " + 
			"WHERE k.identification_code = #{identificationCode} " + 
			"AND k.state = '" + DESConstants.Database.States.ENABLED + "' " + 
			"AND vt.id_parking = k.id " + 
			"AND vt.state ='" + DESConstants.Database.States.ENABLED + "'")
	@Results(value = {
			@Result(property="stateDate", column="state_date"),
			@Result(property = "parking", column = "id_parking", javaType = Parking.class, one = @One(select = "findParkingById")) 
			}
	)
	public List<VehicleType> findByParkingQuery(String identificationCode) throws Exception;
	
	
	@Select("SELECT * " + 
			"FROM vehicle_type vt, parking k " + 
			"WHERE k.identification_code = #{parking.identificationCode} " + 
			"AND k.state = '" + DESConstants.Database.States.ENABLED + "' " + 
			"AND vt.id_parking = k.id " + 
			"AND vt.description = #{description} " + 
			"AND vt.state ='" + DESConstants.Database.States.ENABLED + "'")
	public VehicleType existsInDatabaseToSaveQuery(VehicleType vehicleType) throws Exception;

	
	
	@Select("SELECT * " + 
			"FROM vehicle_type vt, parking k " + 
			"WHERE k.identification_code = #{parking.identificationCode} " + 
			"AND k.state = '" + DESConstants.Database.States.ENABLED + "' " + 
			"AND vt.id_parking = k.id " + 
			"AND vt.id = #{id} " + 
			"AND vt.state ='" + DESConstants.Database.States.ENABLED + "'")
	public VehicleType existsInDatabaseToUpdateQuery(VehicleType vehicleType) throws Exception;

	
	
	@Update("UPDATE vehicle_type " + 
			"SET description = #{description}, " + 
			"state_date = #{stateDate} " +
			"WHERE id = #{id}")
	public void updateQuery(VehicleType vehicleType);


	@Update("UPDATE vehicle_type " + 
			"SET state = #{state}, " +
			"state_date = #{stateDate} " +
			"WHERE id = #{id}")
	public void deleteQuery(VehicleType vehicleType);

}
