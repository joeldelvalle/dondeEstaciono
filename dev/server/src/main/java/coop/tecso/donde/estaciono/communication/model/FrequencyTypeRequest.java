package coop.tecso.donde.estaciono.communication.model;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 
 * @author joel.delvalle
 * 
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class FrequencyTypeRequest {

	private Long id;

	private String parkingIdentificationCode;

	private String description;

	private Integer type;

	private Integer time;

	private Integer idTimeType;

	private Integer priority;

	private Boolean combinablePreviousFreq;

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

	public Integer getIdTimeType() {
		return idTimeType;
	}

	public void setIdTimeType(Integer idTimeType) {
		this.idTimeType = idTimeType;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Boolean getCombinablePreviousFreq() {
		return combinablePreviousFreq;
	}

	public void setCombinablePreviousFreq(Boolean combinablePreviousFreq) {
		this.combinablePreviousFreq = combinablePreviousFreq;
	}

}
