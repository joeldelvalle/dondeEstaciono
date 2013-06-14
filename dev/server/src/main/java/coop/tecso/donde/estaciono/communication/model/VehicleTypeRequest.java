package coop.tecso.donde.estaciono.communication.model;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class VehicleTypeRequest {

	private String parkingIdentificationCode;

	private String description;

	public String getParkingIdentificationCode() {
		return parkingIdentificationCode;
	}

	public void setParkingIdentificationCode(String parkingIdentificationCode) {
		this.parkingIdentificationCode = parkingIdentificationCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
