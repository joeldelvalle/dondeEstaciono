package coop.tecso.donde.estaciono.dao.queries.common;

import org.apache.ibatis.annotations.Select;

import coop.tecso.donde.estaciono.model.Parking;

public interface GenericQuery {

	@Select("SELECT * FROM parking WHERE id = #{id}")
	Parking findParkingById(Long id);
	
}
