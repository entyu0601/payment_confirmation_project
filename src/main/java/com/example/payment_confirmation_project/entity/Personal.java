package com.example.payment_confirmation_project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "personal")
public class Personal {

	@Id
	@Column(name = "object_id")
	private String objectId;

	@Column(name = "owner_name")
	private String ownerName;

	@Column(name = "tenant_name")
	private String tenantName;

	@Column(name = "building")
	private String building;

	@Column(name = "rent")
	private int rent;

	public Personal() {

	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public int getRent() {
		return rent;
	}

	public void setRent(int rent) {
		this.rent = rent;
	}

}
