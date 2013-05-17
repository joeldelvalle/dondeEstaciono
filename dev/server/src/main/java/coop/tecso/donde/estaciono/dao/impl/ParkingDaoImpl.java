package coop.tecso.donde.estaciono.dao.impl;

import java.util.ArrayList;
import java.util.List;

import coop.tecso.donde.estaciono.dao.ParkingDao;
import coop.tecso.donde.estaciono.dao.generic.DaoGeneric;
import coop.tecso.donde.estaciono.model.Parking;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class ParkingDaoImpl extends DaoGeneric implements ParkingDao {

	private List<Parking> parkingListCache = new ArrayList<Parking>();

	// TODO: hacer un metodo que actualize la cache cada vez que se de de alta
	// un estacionamiento

	@Override
	public List<Parking> findAllParking() {

		if (this.getParkingListCache().size() > 0) {
			return this.getParkingListCache();
		}

		List<Parking> parkingList = this.getMongoTemplate().findAll(Parking.class);

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
