package coop.tecso.donde.estaciono.model;

import coop.tecso.donde.estaciono.model.common.GenericModel;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class TimeType extends GenericModel {

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

}
