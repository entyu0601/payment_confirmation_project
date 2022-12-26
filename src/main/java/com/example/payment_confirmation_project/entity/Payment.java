package com.example.payment_confirmation_project.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "object_id")
	private String objectId;

	@Column(name = "payment_deadline")
	private int paymentDeadline;

	@Column(name = "payment_date")
	private LocalDate paymentDate;

	@Column(name = "payment_method")
	private String paymentMethod;

	@Column(name = "late_checked")
	private int lateChecked = 0;

	@Column(name = "payment_months")
	private int paymentMonths;

	@Column(name = "payment_amount")
	private int paymentAmount;

	@Column(name = "rents_month")
	private int rentsMonth;

	public Payment() {

	}

	public Payment(int id, String objectId, int paymentDeadline, LocalDate paymentDate, String paymentMethod,
			int lateChecked, int paymentMonths, int paymentAmount, int rentsMonth) {
		this.id = id;
		this.objectId = objectId;
		this.paymentDeadline = paymentDeadline;
		this.paymentDate = paymentDate;
		this.paymentMethod = paymentMethod;
		this.lateChecked = lateChecked;
		this.paymentMonths = paymentMonths;
		this.paymentAmount = paymentAmount;
		this.rentsMonth = rentsMonth;
	}

	public Payment(int paymentDeadline, LocalDate paymentDate, String paymentMethod, int lateChecked, int paymentMonths,
			int paymentAmount, int rentsMonth) {
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
