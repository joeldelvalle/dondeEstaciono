package coop.tecso.donde.estaciono.model;

import java.util.Calendar;

import coop.tecso.donde.estaciono.model.common.State;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class ParkedVehicle extends State {

	private Long id;

	private Parking parking;

	// aka patente del auto/nombre del duenio
	private String description;

	// aka numeroDeLaCochera/Descripcion del lugar donde estaciono
	private String placeIdentification;

	private Calendar date;

	private VehicleType vehicleType;

	private FrequencyType frequencyType;

	private Calendar startTime;

	private Calendar endTime;

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

	public String getPlaceIdentification() {
		return placeIdentification;
	}

	public void setPlaceIdentification(String placeIdentification) {
		this.placeIdentification = placeIdentification;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
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

	public Calendar getStartTime() {
		return startTime;
	}

	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
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

		ParkedVehicle parkedVehicle = (ParkedVehicle) obj;

		return (this.getParking().equals(parkedVehicle.getParking()) && this.getId().equals(parkedVehicle.getId()));
	}

	@Override
	public int hashCode() {

		int hashCode = 3;

		hashCode = 7 * hashCode + this.getId().hashCode();

		hashCode = 7 * hashCode + this.getParking().hashCode();

		return hashCode;

	}

}
