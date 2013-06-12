package coop.tecso.donde.estaciono.model;

import coop.tecso.donde.estaciono.model.common.State;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class Permission extends State {

	private Long id;

	private ServiceDES service;

	private PermissionAction permissionAction;

	private String identificationCode;

	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ServiceDES getService() {
		return service;
	}

	public void setService(ServiceDES service) {
		this.service = service;
	}

	public PermissionAction getPermissionAction() {
		return permissionAction;
	}

	public void setPermissionAction(PermissionAction permissionAction) {
		this.permissionAction = permissionAction;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIdentificationCode() {
		return identificationCode;
	}

	public void setIdentificationCode(String identificationCode) {
		this.identificationCode = identificationCode;
	}

	@Override
	public String toString() {
		return "description: " + this.getDescription() + "  -  service" + this.getService().toString() + "  -  permissionAcction: "
				+ this.getPermissionAction().toString() + "  -  state: " + this.getState();
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

		Permission permission = (Permission) obj;

		return this.getId().equals(permission.getId());
	}

	@Override
	public int hashCode() {

		int hashCode = 3;

		hashCode = 7 * hashCode + this.getId().hashCode();

		return hashCode;

	}

}
