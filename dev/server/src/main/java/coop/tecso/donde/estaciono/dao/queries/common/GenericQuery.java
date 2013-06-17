package coop.tecso.donde.estaciono.dao.queries.common;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import coop.tecso.donde.estaciono.model.Parking;

public interface GenericQuery {

	@Select("SELECT * FROM parking WHERE id = #{id}")
	@Results(value = {
			@Result(property="identificationCode", column="identification_code"),
			@Result(property="totalPlaces", column="total_places")
		})
	Parking findParkingById(Long id);
	
}
