package coop.tecso.donde.estaciono.dao.queries.common;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import coop.tecso.donde.estaciono.model.Country;
import coop.tecso.donde.estaciono.model.Parking;
import coop.tecso.donde.estaciono.model.Province;

public interface GenericQuery {

	@Select("SELECT * FROM parking WHERE id = #{id}")
	@Results(value = {
			@Result(property="identificationCode", column="identification_code"),
			@Result(property="totalPlaces", column="total_places"),
			@Result(property="stateDate", column="state_date")
		})
	Parking findParkingById(Long id);
	
	
	
	@Select("SELECT * FROM country WHERE id = #{id}")
	@Results(value = {
			@Result(property="stateDate", column="state_date")
		})
	Country findCountryById(Long id);
	
	
	
	@Select("SELECT * FROM province WHERE id = #{id}")
	@Results(value = {
			@Result(property = "country", column = "id_country", javaType = Province.class, one = @One(select = "findCountryById")),
			@Result(property="stateDate", column="state_date")
		})
	Province findProvinceById(Long id);
	
}
