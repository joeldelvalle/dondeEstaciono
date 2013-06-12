package coop.tecso.donde.estaciono.model;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class PermissionAction {

	private Long id;

	private String description;

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

	@Override
	public String toString() {
		return "description: " + this.getDescription();
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

		PermissionAction permissionAction = (PermissionAction) obj;

		return this.getId().equals(permissionAction.getId());
	}

	@Override
	public int hashCode() {

		int hashCode = 3;

		hashCode = 7 * hashCode + this.getId().hashCode();

		return hashCode;

	}

}
