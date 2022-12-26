package com.example.payment_confirmation_project.service.ifs;

import java.time.LocalDate;

import com.example.payment_confirmation_project.vo.MonthsShowRes;
import com.example.payment_confirmation_project.vo.PaymentDataRes;
import com.example.payment_confirmation_project.vo.PaymentRes;

public interface PaymentService {

	/* do Query */
	public PaymentRes doQueryInfo();

	/* do Query By ObjectId */
	public PaymentRes doQueryInfo(String objectId);

	/* do Query By PaymentDate */
	public PaymentRes doQueryByPaymentDate(LocalDate paymentDate);

	/* do Query By RentsMonth */
	public PaymentRes doQueryByRentsMonth(int rentsMonth);

	/* createPayment */
	public PaymentDataRes createPayment(String objectId, int paymentDeadline, LocalDate paymentDate,
			String paymentMethod, int paymentMonths, int rentsMonth) throws Exception;

	/* updatePayment */
	public PaymentDataRes updatePayment(int id, String objectId, LocalDate paymentDate, int paymentMonths, int rentsMonth)
			throws Exception;

//	/* updatePayment */
//	public PaymentDataRes updatePayment(String objectId, LocalDate paymentDate, int paymentMonths, int rentsMonth,
//			LocalDate newPaymentDate, int newPaymentMonths, int newRentsMonth) throws Exception;

	/* do Query MonthsInfo */
	public MonthsShowRes doQueryMonthsInfo();

	/* set 12months checked */
	public MonthsShowRes allMonths(String objectId);

	public PaymentRes getPaymentInfo(int id);

}
