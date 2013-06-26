package coop.tecso.donde.estaciono.communication.model;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 
 * @author joel.delvalle
 * 
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL) 
public class LoginRequest {

	private String user;

	private String password;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
