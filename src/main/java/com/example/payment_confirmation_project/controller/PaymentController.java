package com.example.payment_confirmation_project.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.payment_confirmation_project.entity.MonthsShow;
import com.example.payment_confirmation_project.entity.Payment;
import com.example.payment_confirmation_project.service.ifs.PaymentService;
import com.example.payment_confirmation_project.vo.MonthsShowRes;
import com.example.payment_confirmation_project.vo.PaymentDataRes;
import com.example.payment_confirmation_project.vo.PaymentReq;
import com.example.payment_confirmation_project.vo.PaymentRes;
import com.example.payment_confirmation_project.vo.PersonlReq;

@CrossOrigin // 連前端的東西
@RestController
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping(value = "/api/doQueryInfo")
	public PaymentRes doQueryInfo() {
		return paymentService.doQueryInfo();
	}

	@PostMapping(value = "/api/doQueryInfoByObjectId")
	public PaymentRes doQueryInfo(@RequestBody PaymentReq payReq) {
		return paymentService.doQueryInfo(payReq.getObjectId());
	}

	@PostMapping(value = "/api/doQueryByPaymentDate")
	public PaymentRes doQueryByPaymentDate(@RequestBody PersonlReq req) {
		return paymentService.doQueryByPaymentDate(req.getPaymentDate());
	}

	@PostMapping(value = "/api/doQueryByRentsMonth")
	public PaymentRes doQueryByRentsMonth(@RequestBody PersonlReq req) {
		return paymentService.doQueryByRentsMonth(req.getRentsMonth());
	}

	@PostMapping(value = "/api/createPayment")
	public PaymentDataRes createPayment(@RequestBody PaymentReq payReq) throws Exception {
		return paymentService.createPayment(payReq.getObjectId(), payReq.getPaymentDeadline(), payReq.getPaymentDate(),
				payReq.getPaymentMethod(), payReq.getPaymentMonths(), payReq.getRentsMonth());
	}

	@PostMapping(value = "/api/updatePayment")
	public PaymentDataRes updatePayment(@RequestBody PaymentReq payReq) throws Exception {
		return paymentService.updatePayment(payReq.getId(), payReq.getObjectId(), payReq.getPaymentDate(),
				payReq.getPaymentMonths(), payReq.getRentsMonth());
	}

	@PostMapping(value = "/api/getPaymentInfo")
	public PaymentRes getPaymentInfo(@RequestBody PaymentReq payReq) {
		return paymentService.getPaymentInfo(payReq.getId());
	}

//	=============================================
	@PostMapping(value = "/api/allMonths")
	public MonthsShowRes allMonths(@RequestBody MonthsShow monthsShow) throws Exception {
		return paymentService.allMonths(monthsShow.getObjectId());
	}

	@PostMapping(value = "/api/doQueryMonthsInfo")
	public MonthsShowRes doQueryMonthsInfo() {
		return paymentService.doQueryMonthsInfo();
	}

}
