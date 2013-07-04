package coop.tecso.donde.estaciono.model.common;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class State extends GenericModel {

	private String state;

	private Date stateDate;

	@JsonIgnore
	public String getState() {
		return state;
	}

	@JsonIgnore
	public void setState(String state) {
		this.state = state;
	}

	@JsonIgnore
	public Date getStateDate() {
		return stateDate;
	}

	@JsonIgnore
	public void setStateDate(Date stateDate) {
		this.stateDate = stateDate;
	}

}
