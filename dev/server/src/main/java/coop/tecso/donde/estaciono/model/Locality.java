package coop.tecso.donde.estaciono.model;

import java.util.Calendar;

import org.bson.types.ObjectId;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @author joel.delvalle
 * 
 */
@Document
public class Locality {

	@Id
	private ObjectId id;

	private String name;

	private Province province;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
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
		return "name: " + this.getName() + "  -  province: " + this.getProvince().getName() + "  -  country: "
				+ this.getProvince().getCountry().getName() + "  -  state: " + this.getState();
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

		Locality locality = (Locality) obj;

		if (this.getId().equals(locality.getId()) && this.getProvince().equals(locality.getProvince())) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {

		int hashCode = 3;

		hashCode = 7 * hashCode + this.getId().hashCode();

		hashCode = 7 * hashCode + this.getName().hashCode();

		hashCode = 7 * hashCode + this.getProvince().hashCode();

		return hashCode;

	}

}
