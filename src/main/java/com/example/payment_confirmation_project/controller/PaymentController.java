package com.example.payment_confirmation_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.payment_confirmation_project.constants.RtnInfo;
import com.example.payment_confirmation_project.repository.PaymentDaoImpl;
import com.example.payment_confirmation_project.service.ifs.PaymentService;

import com.example.payment_confirmation_project.vo.PaymentDataRes;
import com.example.payment_confirmation_project.vo.PaymentInfo;
import com.example.payment_confirmation_project.vo.PaymentReq;
import com.example.payment_confirmation_project.vo.PaymentRes;
import com.example.payment_confirmation_project.vo.PersonlReq;

@CrossOrigin // 連前端的東西
@RestController
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private PaymentDaoImpl paymentDaoImpl;

	@PostMapping(value = "/api/doQueryInfo")
	public PaymentRes doQueryInfo() {
		return paymentService.doQueryInfo();
	}

	@PostMapping(value = "/api/getPaymentInfo")
	public PaymentRes getPaymentInfo(@RequestBody PaymentReq payReq) {
		return paymentService.getPaymentInfo(payReq.getId());
	}

	@PostMapping(value = "/api/doQueryByPaymentDate")
	public PaymentRes doQueryByPaymentDate(@RequestBody PaymentReq payReq) {
		return paymentService.doQueryByPaymentDate(payReq.getStartDate(), payReq.getEndDate());
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

	@PostMapping(value = "/api/doQueryWithLimitAndPagesize")
	public PaymentRes doQueryWithLimitAndPagesize(@RequestBody PaymentReq payReq) {
		List<PaymentInfo> paymentList = paymentDaoImpl.doQueryWithPageSizeAndStartPosition(payReq.getPageSize(),
				payReq.getStartPosition());
		if (CollectionUtils.isEmpty(paymentList)) {
			return new PaymentRes(RtnInfo.DATA_NOT_FOUND.getMessage());
		}
		return new PaymentRes(paymentList, RtnInfo.DATA_IS_FOUND.getMessage());
	}


}
