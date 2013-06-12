package coop.tecso.donde.estaciono.model;

import coop.tecso.donde.estaciono.model.common.State;

/**
 * 
 * @author joel.delvalle
 * 
 *         solo existen dos tipos: Publicidad / Oferta
 * 
 *         en caso de que sea oferta (isOffer=true) inplica que debe accionar
 *         sobre el valor del estacionamiento al momento de abonar
 * 
 */
public class MarketingType extends State {

	private Long id;

	private String description;

	private Boolean isOffer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsOffer() {
		return isOffer;
	}

	public void setIsOffer(Boolean isOffer) {
		this.isOffer = isOffer;
	}

	@Override
	public String toString() {
		return "description: " + this.getDescription() + "  -  isOffer: " + this.getIsOffer() + "  -  state: " + this.getState();
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

		MarketingType marketingType = (MarketingType) obj;

		return this.getId().equals(marketingType.getId());
	}

	@Override
	public int hashCode() {

		int hashCode = 3;

		hashCode = 7 * hashCode + this.getId().hashCode();

		return hashCode;

	}

}
