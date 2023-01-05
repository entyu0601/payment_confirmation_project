package com.example.payment_confirmation_project.service.ifs;

import java.time.LocalDate;

import com.example.payment_confirmation_project.vo.PaymentDataRes;
import com.example.payment_confirmation_project.vo.PaymentRes;

public interface PaymentService {

	/* do Query PaymentInfo -->　入力確認機能*/
	public PaymentRes doQueryInfo();
	
	/* get PaymentInfo By Id -->　IDを利用して、該当賃貸物件データを検索*/
	public PaymentRes getPaymentInfo(int id);
	
	/* do Query By PaymentDate --> 年月檢索機能を利用して、入力確認データを検索 */
	public PaymentRes doQueryByPaymentDate(LocalDate startDate, LocalDate endDate);

	/* do Query By RentsMonth --> 月份檢索機能を利用して、入力確認データを検索 */
	public PaymentRes doQueryByRentsMonth(int rentsMonth);

//	/* createPayment --> テナント支払情報の作成 */
//	public PaymentDataRes createPayment(String objectId, int paymentDeadline, LocalDate paymentDate,
//			String paymentMethod, int paymentMonths, int rentsMonth) throws Exception;

	/* updatePayment --> テナント支払情報の更新（編集機能） */
	public PaymentDataRes updatePayment(int id, String objectId, LocalDate paymentDate, int paymentMonths,
			int rentsMonth) throws Exception;
	
	
}
