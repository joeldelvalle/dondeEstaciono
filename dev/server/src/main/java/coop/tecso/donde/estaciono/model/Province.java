package coop.tecso.donde.estaciono.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import coop.tecso.donde.estaciono.model.common.State;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class Province extends State {

	private Long id;

	private Country country;

	private String name;

	@JsonIgnore
	public Long getId() {
		return id;
	}

	@JsonIgnore
	public void setId(Long id) {
		this.id = id;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "name: " + this.getName() + "  -  country: " + this.getCountry().getName() + "  -  state: " + this.getState();
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

		Province province = (Province) obj;

		if (this.getId().equals(province.getId()) && this.getCountry().getId().equals(province.getCountry().getId())) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {

		int hashCode = 3;

		hashCode = 7 * hashCode + this.getId().hashCode();

		hashCode = 7 * hashCode + this.getName().hashCode();

		hashCode = 7 * hashCode + this.getCountry().hashCode();

		return hashCode;

	}

}
