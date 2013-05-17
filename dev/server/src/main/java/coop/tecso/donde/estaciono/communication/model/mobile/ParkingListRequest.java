package coop.tecso.donde.estaciono.communication.model.mobile;

import coop.tecso.donde.estaciono.model.Coordinates;

/**
 * 
 * @author joel.delvalle
 *
 */
public class ParkingListRequest {

	private Coordinates coordinates;
	
	private Integer maxSizeList;

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public Integer getMaxSizeList() {
		return maxSizeList;
	}

	public void setMaxSizeList(Integer maxSizeList) {
		this.maxSizeList = maxSizeList;
	}
	
}
