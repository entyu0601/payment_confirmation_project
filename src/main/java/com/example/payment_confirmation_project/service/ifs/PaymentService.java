package com.example.payment_confirmation_project.service.ifs;

import java.time.LocalDate;

import com.example.payment_confirmation_project.vo.PaymentDataRes;
import com.example.payment_confirmation_project.vo.PaymentRes;

public interface PaymentService {

	/* do Query */
	public PaymentRes doQueryInfo();
	
	/* getPaymentInfo ById */
	public PaymentRes getPaymentInfo(int id);
	
	/* do Query By PaymentDate */
	public PaymentRes doQueryByPaymentDate(LocalDate startDate, LocalDate endDate);

	/* do Query By RentsMonth */
	public PaymentRes doQueryByRentsMonth(int rentsMonth);

	/* createPayment */
	public PaymentDataRes createPayment(String objectId, int paymentDeadline, LocalDate paymentDate,
			String paymentMethod, int paymentMonths, int rentsMonth) throws Exception;

	/* updatePayment */
	public PaymentDataRes updatePayment(int id, String objectId, LocalDate paymentDate, int paymentMonths,
			int rentsMonth) throws Exception;
		

}
