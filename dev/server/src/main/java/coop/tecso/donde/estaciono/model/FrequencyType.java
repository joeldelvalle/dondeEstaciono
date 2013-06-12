package coop.tecso.donde.estaciono.model;

import coop.tecso.donde.estaciono.model.common.State;

/**
 * 
 * @author joel.delvalle
 *
 */
public class FrequencyType extends State {

	private Long id;
	
	private Parking parking;
	
	private String description;
	
	private Integer type;
	
	private Integer time;
	
	private TimeType timeType;
	
	private Integer priority;
	
	private Boolean combinablePreviousFrequency;

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public TimeType getTimeType() {
		return timeType;
	}

	public void setTimeType(TimeType timeType) {
		this.timeType = timeType;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Boolean getCombinablePreviousFrequency() {
		return combinablePreviousFrequency;
	}

	public void setCombinablePreviousFrequency(Boolean combinablePreviousFrequency) {
		this.combinablePreviousFrequency = combinablePreviousFrequency;
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

		FrequencyType frequencyType = (FrequencyType) obj;

		return (this.getParking().equals(frequencyType.getParking()) && this.getId().equals(frequencyType.getId()));
	}

	@Override
	public int hashCode() {

		int hashCode = 3;

		hashCode = 7 * hashCode + this.getId().hashCode();

		hashCode = 7 * hashCode + this.getParking().hashCode();

		return hashCode;

	}
}
