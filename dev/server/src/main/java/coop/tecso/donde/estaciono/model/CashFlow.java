package coop.tecso.donde.estaciono.model;

import java.util.Calendar;

import coop.tecso.donde.estaciono.model.common.State;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class CashFlow extends State {

	private Long id;

	private Parking parking;

	private Calendar date;

	private CashFlowType cashFlowType;

	private String description;

	private Double amount;

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

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public CashFlowType getCashFlowType() {
		return cashFlowType;
	}

	public void setCashFlowType(CashFlowType cashFlowType) {
		this.cashFlowType = cashFlowType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "parking: " + this.getParking() + "  -  cashFlowType: " + this.getCashFlowType() + "  -  description: " + this.getDescription()
				+ "  -  state: " + this.getState();
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

		CashFlow cashFlow = (CashFlow) obj;

		return (this.getParking().equals(cashFlow.getParking()) && this.getId().equals(cashFlow.getId()));
	}

	@Override
	public int hashCode() {

		int hashCode = 3;

		hashCode = 7 * hashCode + this.getId().hashCode();

		if (this.getParking() != null) {
			hashCode = 7 * hashCode + this.getParking().hashCode();
		}

		return hashCode;

	}
}