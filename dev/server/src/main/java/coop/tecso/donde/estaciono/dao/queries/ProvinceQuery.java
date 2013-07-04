package coop.tecso.donde.estaciono.dao.queries;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import coop.tecso.donde.estaciono.model.Country;
import coop.tecso.donde.estaciono.model.Province;
import coop.tecso.donde.estaciono.utils.DESConstants;

/**
 * 
 * @author joel.delvalle
 *
 */
@Component
public interface ProvinceQuery extends CountryQuery {

	@Select("SELECT * " +
			"FROM province " +
			"WHERE state = '" + DESConstants.Database.States.ENABLED + "'")
	@Results(value = {
			@Result(property = "country", column = "id_country", javaType = Country.class, one = @One(select = "findCountryById")),
			@Result(property="stateDate", column="state_date")
		})
	public List<Province> findAllProvinceQuery() throws Exception;


	
	@Select("SELECT * " +
			"FROM province " +
			"WHERE id_country = #{countryId} " +
			"AND state = '" + DESConstants.Database.States.ENABLED + "'")
	@Results(value = {
			@Result(property = "country", column = "id_country", javaType = Country.class, one = @One(select = "findCountryById")),
			@Result(property="stateDate", column="state_date")
		})
	public List<Province> findByCountryIdQuery(Integer countryId) throws Exception;

	
	
	@Select("SELECT * FROM province WHERE id = #{id}")
	@Results(value = {
			@Result(property = "country", column = "id_country", javaType = Province.class, one = @One(select = "findCountryById")),
			@Result(property="stateDate", column="state_date")
		})
	public Province findProvinceById(Long id);
	
}
