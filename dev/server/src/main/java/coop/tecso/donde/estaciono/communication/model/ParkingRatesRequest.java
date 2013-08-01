package coop.tecso.donde.estaciono.communication.model;

import org.codehaus.jackson.map.annotate.JsonSerialize;


/**
 * 
 * @author german.romero
 * 
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ParkingRatesRequest {

	private Long id;

	private String parkingIdentificationCode;

	private Long vehicleType;
	
	private Long frequencyType;
	
	private Double amount;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParkingIdentificationCode() {
		return parkingIdentificationCode;
	}

	public void setParkingIdentificationCode(String parkingIdentificationCode) {
		this.parkingIdentificationCode = parkingIdentificationCode;
	}

	public Long getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(Long vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Long getFrequencyType() {
		return frequencyType;
	}

	public void setFrequencyType(Long frequencyType) {
		this.frequencyType = frequencyType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
