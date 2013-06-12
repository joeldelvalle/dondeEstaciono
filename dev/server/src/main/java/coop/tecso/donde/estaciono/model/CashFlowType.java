package coop.tecso.donde.estaciono.model;

import coop.tecso.donde.estaciono.model.common.State;

/**
 * 
 * @author joel.delvalle
 * 
 *         vamos a tener dos genericos que son: INGRESO / EGRESO, esos irian sin
 *         tener Parking asignado
 * 
 */
public class CashFlowType extends State {

	private Long id;
	
	private Parking parking;
	
	private String description;
	
	// aka ponemos si el cashFlowType suma o resta al momento de utilizarlo
	private String inOut;
	
	private Boolean defaultVehicleOutput;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Parking getParking() {
		return parking;
	}

	public void setParking(Parking parking) {
		this.parking = parking;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInOut() {
		return inOut;
	}

	public void setInOut(String inOut) {
		this.inOut = inOut;
	}

	public Boolean getDefaultVehicleOutput() {
		return defaultVehicleOutput;
	}

	public void setDefaultVehicleOutput(Boolean defaultVehicleOutput) {
		this.defaultVehicleOutput = defaultVehicleOutput;
	}

	@Override
	public String toString() {
		return "parking: " + this.getParking() + "  -  description: " + this.getDescription() + "  -  state: " + this.getState();
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

		CashFlowType cashFlowType = (CashFlowType) obj;

		return (this.getParking().equals(cashFlowType.getParking()) && this.getId().equals(cashFlowType.getId()));
	}

	@Override
	public int hashCode() {

		int hashCode = 3;

		hashCode = 7 * hashCode + this.getId().hashCode();

		hashCode = 7 * hashCode + this.getParking().hashCode();

		return hashCode;

	}

}
