package coop.tecso.donde.estaciono.model;

import coop.tecso.donde.estaciono.model.common.State;
import coop.tecso.donde.estaciono.utils.DESConstants;
import coop.tecso.donde.estaciono.utils.DESTime;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class VehicleType extends State {

	private Long id;

	private Parking parking;

	private String description;

	public VehicleType() {
		this.setState(DESConstants.Database.States.ENABLED);
		this.setStateDate(DESTime.getToday().getTime());
	}

	public VehicleType(Parking parking, String description) {
		this.parking = parking;
		this.description = description;
		this.setState(DESConstants.Database.States.ENABLED);
		this.setStateDate(DESTime.getToday().getTime());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Parking getParking() {
		return parking;
	}

	public void setParking(Parking parking) {
		this.parking = parking;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "parking: " + this.getParking() + "  -  description: " + this.getDescription() + "  -  state: " + this.getState();
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

		VehicleType vehicleType = (VehicleType) obj;

		return (this.getParking().equals(vehicleType.getParking()) && this.getId().equals(vehicleType.getId()));
	}

	@Override
	public int hashCode() {

		int hashCode = 3;

		hashCode = 7 * hashCode + this.getId().hashCode();

		hashCode = 7 * hashCode + this.getParking().hashCode();

		return hashCode;

	}

}
