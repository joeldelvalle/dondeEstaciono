package coop.tecso.donde.estaciono.communication;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.json.CustomJsonDeserialize;
import coop.tecso.donde.estaciono.json.CustomJsonSerializer;
import coop.tecso.donde.estaciono.model.Mac;

/**
 * 
 * @author joel.delvalle
 * 
 *         clase request de los rest
 * 
 */
public class DESRequest {

	private Mac mac;

	private String userHash;

	private Object payload;

	@JsonSerialize(using = CustomJsonSerializer.class)
	public Object getPayload() {
		return payload;
	}

	public <T> T getPayload(Class<T> clazz) throws DondeEstacionoServerException {

		try {

			return clazz.cast(payload);

		} catch (ClassCastException ce) {
			throw new DondeEstacionoServerException(ce);
		}
	}

	@JsonDeserialize(using = CustomJsonDeserialize.class)
	public void setPayload(Object payload) {
		this.payload = payload;
	}

	public Mac getMac() {
		return mac;
	}

	public void setMac(Mac mac) {
		this.mac = mac;
	}

	public String getUserHash() {
		return userHash;
	}

	public void setUserHash(String userHash) {
		this.userHash = userHash;
	}

}
