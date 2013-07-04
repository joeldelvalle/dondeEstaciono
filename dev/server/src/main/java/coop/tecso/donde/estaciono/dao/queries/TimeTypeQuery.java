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
	public List<TimeType> findAllTimeTypeQuery() throws Exception;
	
	
	@Select("SELECT * FROM time_type WHERE id = #{id}")
	public TimeType findTimeTypeById(Long id);
	
}
