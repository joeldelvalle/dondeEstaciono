package coop.tecso.donde.estaciono.model;

import coop.tecso.donde.estaciono.model.common.State;


/**
 * 
 * @author joel.delvalle
 * 
 */
public class ParkingService extends State {

	private Parking parking;

	private ServiceDES service;

	public Parking getParking() {
		return parking;
	}

	public void setParking(Parking parking) {
		this.parking = parking;
	}

	public ServiceDES getService() {
		return service;
	}

	public void setService(ServiceDES service) {
		this.service = service;
	}

	@Override
	public String toString() {
		return "parking: " + this.getParking().toString() + "  -  service: " + this.getService().toString() + "  -  state: " + this.getState();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}

		if (obj == this) {
			return true;
		}

		if (obj.getClass() != getClass()) {
			return false;
		}

		ParkingService parkingService = (ParkingService) obj;

		return (this.getParking().equals(parkingService.getParking()) && this.getService().equals(parkingService.getService()));
	}

	@Override
	public int hashCode() {

		int hashCode = 3;

		hashCode = 7 * hashCode + this.getParking().hashCode();

		hashCode = 7 * hashCode + this.getService().hashCode();

		return hashCode;

	}
}
