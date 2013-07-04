package coop.tecso.donde.estaciono.dao.queries;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import coop.tecso.donde.estaciono.dao.utils.QueryParameters;
import coop.tecso.donde.estaciono.model.Locality;
import coop.tecso.donde.estaciono.model.Province;
import coop.tecso.donde.estaciono.utils.DESConstants;

/**
 * 
 * @author joel.delvalle
 *
 */
@Component
public interface LocalityQuery extends ProvinceQuery {

	@Select("SELECT * " +
			"FROM locality " +
			"WHERE state = '" + DESConstants.Database.States.ENABLED + "'")
	@Results(value = {
			@Result(property = "province", column = "id_province", javaType = Province.class, one = @One(select = "findProvinceById")),
			@Result(property="stateDate", column="state_date")
		})
	public List<Locality> findAllLocalityQuery() throws Exception;

	
	
	@Select("SELECT * " +
			"FROM locality lo, province pr " +
			"WHERE lo.id_province = #{provinceId} " +
			"AND lo.id_province = pr.id " +
			"AND pr.state = '" + DESConstants.Database.States.ENABLED + "' " +
			"AND lo.state = '" + DESConstants.Database.States.ENABLED + "'")
	@Results(value = {
			@Result(property = "province", column = "id_province", javaType = Province.class, one = @One(select = "findProvinceById")),
			@Result(property="stateDate", column="state_date")
		})
	public List<Locality> findByProvinceQuery(Integer provinceId);

	
	
	

	@Select("SELECT * " +
			"FROM locality lo, province pr " +
			"WHERE lo.id_province = #{0} " +
			"AND lo.id_province = pr.id " +
			"AND pr.id_country = #{1} " +
			"AND pr.state = '" + DESConstants.Database.States.ENABLED + "' " +
			"AND lo.state = '" + DESConstants.Database.States.ENABLED + "'")
	@Results(value = {
			@Result(property = "province", column = "id_province", javaType = Province.class, one = @One(select = "findProvinceById")),
			@Result(property="stateDate", column="state_date")
		})
	public List<Locality> findByProvinceByCountryQuery(QueryParameters parameters);
		

}