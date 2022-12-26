package com.example.payment_confirmation_project.vo;

import com.example.payment_confirmation_project.entity.Payment;

public class PaymentDataRes {

	private Payment payment;

	private String message;

	public PaymentDataRes() {

	}

	public PaymentDataRes(String message) {
		this.message = message;
	}

	public PaymentDataRes(Payment payment, String message) {
		this.payment = payment;
		this.message = message;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
