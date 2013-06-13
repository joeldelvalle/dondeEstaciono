package coop.tecso.donde.estaciono.dao.queries;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
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

	@Insert("INSERT INTO vehicle_type VALUES (#{id}, #{parking.id}, #{description}, #{state}, #{stateDate})")
	@SelectKey(statement="call identity()", keyProperty="id", before=false, resultType=long.class)
	public void saveQuery(VehicleType vehicleType) throws Exception;

	
	@Select("SELECT * " +
			"FROM vehicle_type " +
			"WHERE state ='" + DESConstants.Database.States.ENABLED + "'")
	@Results(value = {
			@Result(property="parking", column="id_parking", javaType=Parking.class, one=@One(select = "findParkingById"))
		})
	public List<VehicleType> findAllQuery() throws Exception;
	
}
