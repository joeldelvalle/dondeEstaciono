package coop.tecso.donde.estaciono.dao.queries;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import coop.tecso.donde.estaciono.model.TimeType;

/**
 * 
 * @author joel.delvalle
 *
 */
public interface TimeTypeQuery {

	@Select("SELECT * FROM time_type")
	public List<TimeType> findAll() throws Exception;
	
}
