package coop.tecso.donde.estaciono.communication;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import coop.tecso.donde.estaciono.json.CustomJsonDeserialize;
import coop.tecso.donde.estaciono.json.CustomJsonSerializer;

/**
 * 
 * @author joel.delvalle
 * 
 */
@XmlRootElement
public class DESResponse {

	private String status;

	private Object payload;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@JsonSerialize(using = CustomJsonSerializer.class, include = JsonSerialize.Inclusion.NON_NULL)
	public Object getPayload() {
		return payload;
	}

	@JsonDeserialize(using = CustomJsonDeserialize.class)
	public void setPayload(Object payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		return "status: " + status + " - payload: " + payload;
	}

}
