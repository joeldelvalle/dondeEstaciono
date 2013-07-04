package coop.tecso.donde.estaciono.dao.queries;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import coop.tecso.donde.estaciono.model.Country;
import coop.tecso.donde.estaciono.utils.DESConstants;

/**
 * 
 * @author joel.delvalle
 *
 */
@Component
public interface CountryQuery {

	@Select("SELECT * " +
			"FROM country " +
			"WHERE state = '" + DESConstants.Database.States.ENABLED + "'")
	@Results(value = {
			@Result(property="stateDate", column="state_date")
		})
	public List<Country> findAllCountryQuery() throws Exception;
	
	
	@Select("SELECT * FROM country WHERE id = #{id}")
	@Results(value = {
			@Result(property="stateDate", column="state_date")
		})
	public Country findCountryById(Long id);
}
