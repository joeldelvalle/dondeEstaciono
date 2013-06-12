package coop.tecso.donde.estaciono.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import coop.tecso.donde.estaciono.model.common.State;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class ServiceDES extends State {

	private Long id;

	// aka codigo identificacion del servicio
	private String identificationCode;

	private String description;

	private Boolean billed;

	@JsonIgnore
	public Long getId() {
		return id;
	}

	@JsonIgnore
	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean isBilled() {
		return billed;
	}

	public void setBilled(Boolean billed) {
		this.billed = billed;
	}

	public Boolean getBilled() {
		return billed;
	}

	public String getIdentificationCode() {
		return identificationCode;
	}

	public void setIdentificationCode(String identificationCode) {
		this.identificationCode = identificationCode;
	}

	@Override
	public String toString() {
		return "descripton: " + this.getDescription() + "  -  payment: " + this.isBilled() + "  -  state: " + this.getState();
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

		ServiceDES service = (ServiceDES) obj;

		return this.getId().equals(service.getId());
	}

	@Override
	public int hashCode() {

		int hashCode = 3;

		hashCode = 7 * hashCode + this.getId().hashCode();

		return hashCode;

	}

}
