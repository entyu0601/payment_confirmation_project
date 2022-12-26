package com.example.payment_confirmation_project.vo;

import java.time.LocalDate;

public class PaymentReq {

	private int id;

	private String objectId;

	private String paymentMethod;

	private int paymentDeadline;

	private LocalDate paymentDate;

	private boolean lateChecked;

	private int paymentMonths;

	private int paymentAmount;

	private int rentsMonth;

//	==========
	LocalDate newPaymentDate;

	int newPaymentMonths;

	int newRentsMonth;

	public PaymentReq() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
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

	public LocalDate getNewPaymentDate() {
		return newPaymentDate;
	}

	public void setNewPaymentDate(LocalDate newPaymentDate) {
		this.newPaymentDate = newPaymentDate;
	}

	public int getNewPaymentMonths() {
		return newPaymentMonths;
	}

	public void setNewPaymentMonths(int newPaymentMonths) {
		this.newPaymentMonths = newPaymentMonths;
	}

	public int getNewRentsMonth() {
		return newRentsMonth;
	}

	public void setNewRentsMonth(int newRentsMonth) {
		this.newRentsMonth = newRentsMonth;
	}

}
