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
public class Email {

	@Id
	private ObjectId id;

	private String description;

	private String mail;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
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
		return "description: " + this.getDescription() + "  -  mail: " + this.getMail() + "  -  state: " + this.getState();
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

		Email email = (Email) obj;

		return this.getId().equals(email.getId());
	}

	@Override
	public int hashCode() {

		int hashCode = 3;

		hashCode = 7 * hashCode + this.getId().hashCode();

		hashCode = 7 * hashCode + this.getMail().hashCode();

		return hashCode;

	}

}
