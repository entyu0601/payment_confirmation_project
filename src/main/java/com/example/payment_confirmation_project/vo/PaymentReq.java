package com.example.payment_confirmation_project.vo;

import java.time.LocalDate;

public class PaymentReq {

	private int id;

	private String objectId;

	private String paymentMethod;

	private int paymentDeadline;

	private LocalDate paymentDate;

	private LocalDate startDate;

	private LocalDate endDate;

	private boolean lateChecked;

	private int paymentMonths;

	private int paymentAmount;

	private int rentsMonth;

	private int pageSize;

	private int startPosition;

	public PaymentReq() {

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

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public boolean isLateChecked() {
		return lateChecked;
	}

	public void setLateChecked(boolean lateChecked) {
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

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(int startPosition) {
		this.startPosition = startPosition;
	}

}
