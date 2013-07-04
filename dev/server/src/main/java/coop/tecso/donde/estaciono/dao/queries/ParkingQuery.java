package coop.tecso.donde.estaciono.dao.queries;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import coop.tecso.donde.estaciono.model.Parking;
import coop.tecso.donde.estaciono.utils.DESConstants;

/**
 * 
 * @author joel.delvalle
 *
 */
@Component
public interface ParkingQuery {

	@Select("SELECT * " +
			"FROM parking " +
			"WHERE state = '" + DESConstants.Database.States.ENABLED + "'")
	@Results(value = {
			@Result(property="identificationCode", column="identification_code"),
			@Result(property="totalPlaces", column="total_places"),
			@Result(property="stateDate", column="state_date")
		})
	public List<Parking> findAllParkingQuery() throws Exception;
	
	
	
	@Select("SELECT * FROM parking WHERE id = #{id}")
	@Results(value = {
			@Result(property="identificationCode", column="identification_code"),
			@Result(property="totalPlaces", column="total_places"),
			@Result(property="stateDate", column="state_date")
		})
	public Parking findParkingById(Long id);
	
}
