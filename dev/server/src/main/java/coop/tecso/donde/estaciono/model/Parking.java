package coop.tecso.donde.estaciono.model;

import java.util.Calendar;
import java.util.List;

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
public class Parking {

	@Id
	private ObjectId id;

	// aka codigo unico que representa al estacionamiento. el cliente debe
	// conocer este codigo
	private String identificationCode;

	private String name;

	private Coordinates coordinates;

	private String address;

	private Locality locality;

	private Province province;

	private Country country;

	private List<Phone> phones;

	private List<Email> emails;

	private List<AdditionalServices> additionalServices;

	private ParkingRates parkingRates;

	private Integer totalPlaces;

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

	public String getIdentificationCode() {
		return identificationCode;
	}

	public void setIdentificationCode(String identificationCode) {
		this.identificationCode = identificationCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Locality getLocality() {
		return locality;
	}

	public void setLocality(Locality locality) {
		this.locality = locality;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	public List<AdditionalServices> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(List<AdditionalServices> additionalServices) {
		this.additionalServices = additionalServices;
	}

	public ParkingRates getParkingRates() {
		return parkingRates;
	}

	public void setParkingRates(ParkingRates parkingRates) {
		this.parkingRates = parkingRates;
	}

	public Integer getTotalPlaces() {
		return totalPlaces;
	}

	public void setTotalPlaces(Integer totalPlaces) {
		this.totalPlaces = totalPlaces;
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

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	@Override
	public String toString() {
		return "identificationCode: " + this.getIdentificationCode() + "  -  name: " + this.getName() + "  -  state: " + this.getState();
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

		Parking parking = (Parking) obj;

		return this.getId().equals(parking.getId());
	}

	@Override
	public int hashCode() {

		int hashCode = 3;

		hashCode = 7 * hashCode + this.getId().hashCode();

		hashCode = 7 * hashCode + this.getIdentificationCode().hashCode();

		return hashCode;

	}

}
