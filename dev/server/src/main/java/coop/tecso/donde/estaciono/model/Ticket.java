package coop.tecso.donde.estaciono.model;

import java.util.Calendar;

import coop.tecso.donde.estaciono.model.common.State;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class Ticket extends State {

	private Long id;

	private Parking parking;

	private VehicleType vehicleType;

	private FrequencyType frequencyType;

	private Calendar date;

	private Calendar startTime;

	private Calendar endTime;

	private String vehicleDescription;

	private Double amount;

	private Marketing marketing;

	private Double discountAmount;

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

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Marketing getMarketing() {
		return marketing;
	}

	public void setMarketing(Marketing marketing) {
		this.marketing = marketing;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getVehicleDescription() {
		return vehicleDescription;
	}

	public void setVehicleDescription(String vehicleDescription) {
		this.vehicleDescription = vehicleDescription;
	}

	@Override
	public String toString() {
		return "parking: " + this.getParking() + "  -  vehicle: " + this.getVehicleDescription() + "  -  amount: " + this.getAmount()
				+ "  -  state: " + this.getState();
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

		Ticket ticket = (Ticket) obj;

		return (this.getParking().equals(ticket.getParking()) && this.getId().equals(ticket.getId()));
	}

	@Override
	public int hashCode() {

		int hashCode = 3;

		hashCode = 7 * hashCode + this.getId().hashCode();

		hashCode = 7 * hashCode + this.getParking().hashCode();

		return hashCode;

	}

}
