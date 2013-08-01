package coop.tecso.donde.estaciono.model;

import coop.tecso.donde.estaciono.model.common.State;
import coop.tecso.donde.estaciono.utils.DESConstants;
import coop.tecso.donde.estaciono.utils.DESTime;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class ParkingRates extends State {

	private Long id;

	private Parking parking;

	private VehicleType vehicleType;

	private FrequencyType frequencyType;

	private Double amount;
	
	public ParkingRates() {
	}
	
	public ParkingRates(Long id, Parking parking, VehicleType vehicleType, FrequencyType frequencyType, Double amount) {
		this.id = id;
		this.parking = parking;
		this.vehicleType = vehicleType;
		this.frequencyType = frequencyType;
		this.amount = amount;
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

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public FrequencyType getFrequencyType() {
		return frequencyType;
	}

	public void setFrequencyType(FrequencyType frequencyType) {
		this.frequencyType = frequencyType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "parking: " + this.getParking() + "  -  vehicleType: " + this.getVehicleType().toString() + "  -  frequencyType: "
				+ this.getFrequencyType() + "  -  state: " + this.getState();
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

		ParkingRates parkingRates = (ParkingRates) obj;

		return (this.getId().equals(parkingRates.getId()) && this.getParking().equals(parkingRates.getParking())
				&& this.getVehicleType().equals(parkingRates.getVehicleType()) && this.getFrequencyType().equals(parkingRates.getFrequencyType()));
	}

	@Override
	public int hashCode() {

		int hashCode = 3;

		hashCode = 7 * hashCode + this.getId().hashCode();

		hashCode = 7 * hashCode + this.getParking().hashCode();

		hashCode = 7 * hashCode + this.getVehicleType().hashCode();

		hashCode = 7 * hashCode + this.getFrequencyType().hashCode();

		return hashCode;

	}

}
