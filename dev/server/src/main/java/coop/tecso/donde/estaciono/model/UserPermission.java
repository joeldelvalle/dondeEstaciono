package coop.tecso.donde.estaciono.model;

import coop.tecso.donde.estaciono.model.common.State;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class UserPermission extends State {

	private Long id;

	private User user;

	private Permission permission;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	@Override
	public String toString() {
		return "user: " + this.getUser().toString() + "  -  permission: " + this.getPermission() + "  -  state: " + this.getState();
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

		UserPermission userPermission = (UserPermission) obj;

		return this.getId().equals(userPermission.getId());
	}

	@Override
	public int hashCode() {

		int hashCode = 3;

		hashCode = 7 * hashCode + this.getId().hashCode();

		return hashCode;

	}

}
