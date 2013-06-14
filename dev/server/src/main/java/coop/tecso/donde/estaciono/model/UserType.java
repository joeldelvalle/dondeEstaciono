package coop.tecso.donde.estaciono.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import coop.tecso.donde.estaciono.model.common.GenericModel;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class UserType extends GenericModel {

	private Long id;

	private String description;

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

	@Override
	public String toString() {
		return "description: " + this.getDescription();
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

		UserType userType = (UserType) obj;

		return this.getId().equals(userType.getId());
	}

	@Override
	public int hashCode() {

		int hashCode = 3;

		hashCode = 7 * hashCode + this.getId().hashCode();

		return hashCode;

	}

}
