package coop.tecso.donde.estaciono.service.impl;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coop.tecso.donde.estaciono.dao.ParkingDao;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.model.Coordinate;
import coop.tecso.donde.estaciono.model.Parking;
import coop.tecso.donde.estaciono.service.ParkingService;
import coop.tecso.donde.estaciono.utils.DESMath;
import coop.tecso.donde.estaciono.utils.DESUtils;

/**
 * 
 * @author joel.delvalle
 * 
 */
@Service
public class ParkingServiceImpl implements ParkingService {

	@Autowired
	private ParkingDao parkingDao;

	@Override
	public List<Parking> findAllParking() throws DondeEstacionoServerException {
		return this.parkingDao.findAllParking();
	}

	@Override
	public List<Parking> findParkingByCoordinates(Coordinate coordinates) throws DondeEstacionoServerException {

		List<Parking> parkingList = this.parkingDao.findAllParking();

		Map<Double, Parking> parkingSorted = this.sortedParkingByDistanceFromOriginalCoordinates(coordinates, parkingList);

		return DESUtils.convertCollectionToList(parkingSorted.values(), Parking.class);

	}

	private Map<Double, Parking> sortedParkingByDistanceFromOriginalCoordinates(Coordinate coordinates, List<Parking> parkingList) {

		Map<Double, Parking> parkingSorted = new TreeMap<Double, Parking>();

		for (Parking parking : parkingList) {

			Double distance = DESMath.calculateDistanceBetweenToCoordinates(coordinates, parking.getCoordinates());

			parkingSorted.put(distance, parking);
		}

		return parkingSorted;
	}

}
