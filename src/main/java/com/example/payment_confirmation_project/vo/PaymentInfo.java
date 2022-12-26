package com.example.payment_confirmation_project.vo;

import java.time.LocalDate;

public class PaymentInfo {

	private int id;

	private String objectId;

	private String building;

	private String ownerName;

	private String tenantName;

	private int rent;

	private int paymentDeadline;

	private LocalDate paymentDate;

	private String paymentMethod;

	private int lateChecked;

	private int paymentMonths;

	private int paymentAmount;

	private int rentsMonth;

	public PaymentInfo() {

	}

	public PaymentInfo(int id, String objectId, String building, String ownerName, String tenantName, int rent,
			int paymentDeadline, LocalDate paymentDate, String paymentMethod, int lateChecked, int paymentMonths,
			int paymentAmount, int rentsMonth) {
		super();
		this.id = id;
		this.objectId = objectId;
		this.building = building;
		this.ownerName = ownerName;
		this.tenantName = tenantName;
		this.rent = rent;
		this.paymentDeadline = paymentDeadline;
		this.paymentDate = paymentDate;
		this.paymentMethod = paymentMethod;
		this.lateChecked = lateChecked;
		this.paymentMonths = paymentMonths;
		this.paymentAmount = paymentAmount;
		this.rentsMonth = rentsMonth;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getPaymentDeadline() {
		return paymentDeadline;
	}

	public void setPaymentDeadline(int paymentDeadline) {
		this.paymentDeadline = paymentDeadline;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public int getLateChecked() {
		return lateChecked;
	}

	public void setLateChecked(int lateChecked) {
		this.lateChecked = lateChecked;
	}

	public int getPaymentMonths() {
		return paymentMonths;
	}

	public void setPaymentMonths(int paymentMonths) {
		this.paymentMonths = paymentMonths;
	}

	public int getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(int paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public int getRentsMonth() {
		return rentsMonth;
	}

	public void setRentsMonth(int rentsMonth) {
		this.rentsMonth = rentsMonth;
	}

}
