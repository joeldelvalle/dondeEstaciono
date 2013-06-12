package coop.tecso.donde.estaciono.dao.queries;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import coop.tecso.donde.estaciono.model.Login;
import coop.tecso.donde.estaciono.utils.DESConstants;

/**
 * 
 * @author joel.delvalle
 *
 */
@Component
public interface LoginQuery {

	@Select("SELECT * " +
			"FROM login " +
			"WHERE username = #{userName} " +
			"AND password = #{password} " +
			"AND state = '" + DESConstants.Database.States.ENABLED + "'")
	public Login authenticateQuery(Login login) throws Exception;
	
}
