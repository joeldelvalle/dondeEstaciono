package coop.tecso.donde.estaciono.model;

import java.util.Calendar;

import org.bson.types.ObjectId;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class Permission {

	private ObjectId id;

	private String code;

	private String description;

	private String state;

	private Calendar stateDate;

	@JsonIgnore
	public ObjectId getId() {
		return id;
	}

	@JsonIgnore
	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String descripcion) {
		this.description = descripcion;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Calendar getStateDate() {
		return stateDate;
	}

	public void setStateDate(Calendar stateDate) {
		this.stateDate = stateDate;
	}

	@Override
	public String toString() {
		return "code: " + this.getCode() + "  -  description: " + this.getDescription() + "  -  state: " + this.getState();
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

		Permission permission = (Permission) obj;

		return this.getId().equals(permission.getId());
	}

	@Override
	public int hashCode() {

		int hashCode = 3;

		hashCode = 7 * hashCode + this.getId().hashCode();

		hashCode = 7 * hashCode + this.getCode().hashCode();

		hashCode = 7 * hashCode + this.getDescription().hashCode();

		return hashCode;

	}

}
