package coop.tecso.donde.estaciono.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import coop.tecso.donde.estaciono.dao.VehicleTypeDao;
import coop.tecso.donde.estaciono.dao.queries.VehicleTypeQuery;
import coop.tecso.donde.estaciono.dao.utils.DatabaseConnection;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.logger.CustomLogger;
import coop.tecso.donde.estaciono.model.VehicleType;
import coop.tecso.donde.estaciono.utils.DESUtils;

/**
 * 
 * @author joel.delvalle
 * 
 */
@Service
public class VehicleTypeDaoImpl implements VehicleTypeDao {

	private CustomLogger log = new CustomLogger(getClass().getCanonicalName());

	@Override
	public void save(VehicleType vehicleType)
			throws DondeEstacionoServerException {
		String method = "save";
		log.logStartMethod(method);

		SqlSession session = null;
		try {

			session = DatabaseConnection.getInstance().getSession();

			VehicleTypeQuery query = session.getMapper(VehicleTypeQuery.class);

			query.saveQuery(vehicleType);

			session.commit();

		} catch (Exception e) {
			log.logError(method, "error al buscar vehicleType", e);
			throw new DondeEstacionoServerException(e);

		} finally {

			if (!DESUtils.isNull(session)) {
				session.close();
			}

		}

		log.logEndMethod(method);
	}

	@Override
	public List<VehicleType> findAll() throws DondeEstacionoServerException {
		String method = "findAll";
		log.logStartMethod(method);

		SqlSession session = null;
		List<VehicleType> vehicleTypeList = new ArrayList<VehicleType>();

		try {

			session = DatabaseConnection.getInstance().getSession();

			VehicleTypeQuery query = session.getMapper(VehicleTypeQuery.class);

			vehicleTypeList = query.findAllQuery();

		} catch (Exception e) {
			log.logError(method, "error al buscar vehicleType", e);
			throw new DondeEstacionoServerException(e);

		} finally {

			if (!DESUtils.isNull(session)) {
				session.close();
			}

		}

		log.logInfo(method, "cantidad de vehicleType encontrados: "
				+ vehicleTypeList.size());
		log.logEndMethod(method);

		return vehicleTypeList;
	}

	@Override
	public List<VehicleType> findByParking(String identificationCode) {
		// TODO Auto-generated method stub
		return null;
	}

}
