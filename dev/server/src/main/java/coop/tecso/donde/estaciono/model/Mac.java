package coop.tecso.donde.estaciono.model;

/**
 * 
 * @author joel.delvalle
 * 
 *         clase que mapea la mac de validacion, esta mac es la key para poder
 *         acceder al server
 * 
 */
public class Mac {

	private String mac;

	public Mac(String mac) {
		this.mac = mac;
	}

	public Mac() {
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
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

		Mac mac = (Mac) obj;

		return this.getMac().equals(mac.getMac());
	}

}
