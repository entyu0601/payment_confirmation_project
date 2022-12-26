package com.example.payment_confirmation_project.vo;

import java.time.LocalDate;

public class PersonlReq {

	private String objectId;

	private String ownerName;

	private String tenantName;

	private LocalDate paymentDate;

	private int rentsMonth;

	private boolean lateChecked;

	public PersonlReq() {

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

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public int getRentsMonth() {
		return rentsMonth;
	}

	public void setRentsMonth(int rentsMonth) {
		this.rentsMonth = rentsMonth;
	}

	public boolean isLateChecked() {
		return lateChecked;
	}

	public void setLateChecked(boolean lateChecked) {
		this.lateChecked = lateChecked;
	}

}
