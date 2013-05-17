package coop.tecso.donde.estaciono.communication.model.common;

import java.util.List;

import coop.tecso.donde.estaciono.model.Parking;

/**
 * 
 * @author joel.delvalle
 *
 */
public class ParkingListResponse {

	private List<Parking> parkingList;

	public List<Parking> getParkingList() {
		return parkingList;
	}

	public void setParkingList(List<Parking> parkingList) {
		this.parkingList = parkingList;
	}
	
}
