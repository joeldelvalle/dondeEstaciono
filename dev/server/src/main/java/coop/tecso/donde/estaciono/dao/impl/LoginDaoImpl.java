package coop.tecso.donde.estaciono.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import coop.tecso.donde.estaciono.dao.LoginDao;
import coop.tecso.donde.estaciono.dao.queries.LoginQuery;
import coop.tecso.donde.estaciono.dao.utils.DatabaseConnection;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.logger.CustomLogger;
import coop.tecso.donde.estaciono.model.Login;

/**
 * 
 * @author joel.delvalle
 * 
 */
@Service
public class LoginDaoImpl implements LoginDao {

	private CustomLogger log = new CustomLogger(getClass().getCanonicalName());

	public Login authenticate(Login login) throws DondeEstacionoServerException {
		String method = "authenticate";
		log.logStartMethod(method);

		Login authenticateLogin = null;
		try {

			SqlSession session = DatabaseConnection.getInstance().getSession();

			LoginQuery query = session.getMapper(LoginQuery.class);

			authenticateLogin = query.authenticateQuery(login);

			session.close();

		} catch (Exception e) {
			throw new DondeEstacionoServerException(e);
		}

		log.logEndMethod(method);
		return authenticateLogin;
	}

}
