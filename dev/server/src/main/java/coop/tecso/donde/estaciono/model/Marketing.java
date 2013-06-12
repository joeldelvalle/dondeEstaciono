package coop.tecso.donde.estaciono.model;

import java.util.Calendar;

import coop.tecso.donde.estaciono.model.common.State;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class Marketing extends State {

	private Long id;

	private Parking parking;

	private MarketingType marketingType;

	private Calendar date;

	private String subject;

	private String description;

	private Calendar startTime;

	private Calendar endTime;

	private Double discountPercentage;

	private Boolean realTime;

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

	public MarketingType getMarketingType() {
		return marketingType;
	}

	public void setMarketingType(MarketingType marketingType) {
		this.marketingType = marketingType;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Calendar getStartTime() {
		return startTime;
	}

	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}

	public Double getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(Double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public Boolean getRealTime() {
		return realTime;
	}

	public void setRealTime(Boolean realTime) {
		this.realTime = realTime;
	}

	@Override
	public String toString() {
		return "parking: " + this.getParking() + "  -  subject: " + this.getSubject() + "  -  state: " + this.getState();
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

		Marketing marketing = (Marketing) obj;

		return (this.getParking().equals(marketing.getParking()) && this.getId().equals(marketing.getId()));
	}

	@Override
	public int hashCode() {

		int hashCode = 3;

		hashCode = 7 * hashCode + this.getId().hashCode();

		hashCode = 7 * hashCode + this.getParking().hashCode();

		return hashCode;

	}

}
