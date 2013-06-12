package coop.tecso.donde.estaciono.model;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class PlaceAvailables {

	private Long id;

	private Parking parking;

	private Integer freePlaces;

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

	public Integer getFreePlaces() {
		return freePlaces;
	}

	public void setFreePlaces(Integer freePlaces) {
		this.freePlaces = freePlaces;
	}

	@Override
	public String toString() {
		return "parking: " + this.getParking() + "  -  freePlaces: " + this.getFreePlaces();
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

		PlaceAvailables placeAvailables = (PlaceAvailables) obj;

		return (this.getParking().equals(placeAvailables.getParking()) && this.getId().equals(placeAvailables.getId()));
	}

	@Override
	public int hashCode() {

		int hashCode = 3;

		hashCode = 7 * hashCode + this.getId().hashCode();

		hashCode = 7 * hashCode + this.getParking().hashCode();

		return hashCode;

	}

}
