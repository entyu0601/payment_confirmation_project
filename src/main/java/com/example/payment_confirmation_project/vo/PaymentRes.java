package com.example.payment_confirmation_project.vo;

import java.util.List;

public class PaymentRes {

	private String message;

	private List<PaymentInfo> paymentList;

	private PaymentInfo paymentInfo;

	public PaymentRes() {

	}

	public PaymentRes(String message) {
		this.message = message;
	}

	public PaymentRes(List<PaymentInfo> paymentList, String message) {
		this.paymentList = paymentList;
		this.message = message;
	}

	public PaymentRes(PaymentInfo paymentInfo, String message) {
		this.paymentInfo = paymentInfo;
		this.message = message;
	}

	public PaymentInfo getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<PaymentInfo> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List<PaymentInfo> paymentList) {
		this.paymentList = paymentList;
	}

}
