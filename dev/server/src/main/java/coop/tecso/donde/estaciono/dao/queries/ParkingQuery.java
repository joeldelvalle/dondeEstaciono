package coop.tecso.donde.estaciono.dao.queries;

import java.util.List;

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
	public List<Parking> findAllQuery() throws Exception;
}
