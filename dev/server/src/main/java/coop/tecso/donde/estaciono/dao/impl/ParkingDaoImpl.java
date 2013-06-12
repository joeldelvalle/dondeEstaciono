package coop.tecso.donde.estaciono.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import coop.tecso.donde.estaciono.dao.ParkingDao;
import coop.tecso.donde.estaciono.dao.queries.ParkingQuery;
import coop.tecso.donde.estaciono.dao.utils.DatabaseConnection;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.model.Parking;

/**
 * 
 * @author joel.delvalle
 * 
 */
@Service
public class ParkingDaoImpl implements ParkingDao {

	private List<Parking> parkingListCache = new ArrayList<Parking>();

	// TODO: hacer un metodo que actualize la cache cada vez que se de de alta
	// un estacionamiento

	@Override
	public List<Parking> findAllParking() throws DondeEstacionoServerException {

		if (this.getParkingListCache().size() > 0) {
			return this.getParkingListCache();
		}

		List<Parking> parkingList = null;

		try {

			SqlSession session = DatabaseConnection.getInstance().getSession();

			ParkingQuery query = session.getMapper(ParkingQuery.class);

			parkingList = query.findAllQuery();

			session.close();

		} catch (Exception e) {
			throw new DondeEstacionoServerException(e);
		}

		this.saveInCache(parkingList);

		return parkingList;
	}

	private synchronized void saveInCache(List<Parking> parkingList) {
		this.getParkingListCache().addAll(parkingList);
	}

	private synchronized void updateInCache(List<Parking> parkingList) {
		this.getParkingListCache().clear();
		this.saveInCache(parkingList);
	}

	private List<Parking> getParkingListCache() {
		return parkingListCache;
	}

}
