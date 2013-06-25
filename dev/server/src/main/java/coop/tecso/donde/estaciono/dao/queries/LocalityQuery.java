package coop.tecso.donde.estaciono.dao.queries;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import coop.tecso.donde.estaciono.dao.queries.common.GenericQuery;
import coop.tecso.donde.estaciono.model.Locality;
import coop.tecso.donde.estaciono.model.Province;
import coop.tecso.donde.estaciono.utils.DESConstants;

/**
 * 
 * @author joel.delvalle
 *
 */
@Component
public interface LocalityQuery extends GenericQuery {

	@Select("SELECT * " +
			"FROM locality " +
			"WHERE state = '" + DESConstants.Database.States.ENABLED + "'")
	@Results(value = {
			@Result(property = "province", column = "id_province", javaType = Province.class, one = @One(select = "findProvinceById")),
			@Result(property="stateDate", column="state_date")
		})
	public List<Locality> findAllQuery() throws Exception;

	
	
	@Select("SELECT * " +
			"FROM locality " +
			"WHERE id_province = #{provinceId} " +
			"AND state = '" + DESConstants.Database.States.ENABLED + "'")
	@Results(value = {
			@Result(property = "province", column = "id_province", javaType = Province.class, one = @One(select = "findProvinceById")),
			@Result(property="stateDate", column="state_date")
		})
	public List<Locality> findByProvinceQuery(Integer provinceId);

}
