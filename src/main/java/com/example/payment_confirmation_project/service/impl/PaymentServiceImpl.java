package com.example.payment_confirmation_project.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.payment_confirmation_project.constants.RtnInfo;

import com.example.payment_confirmation_project.entity.Payment;
import com.example.payment_confirmation_project.entity.Personal;

import com.example.payment_confirmation_project.repository.PaymentDao;
import com.example.payment_confirmation_project.repository.PersonalDao;
import com.example.payment_confirmation_project.service.ifs.PaymentService;

import com.example.payment_confirmation_project.vo.PaymentDataRes;
import com.example.payment_confirmation_project.vo.PaymentInfo;
import com.example.payment_confirmation_project.vo.PaymentRes;

@Service
public class PaymentServiceImpl extends BaseDao implements PaymentService {

	@Autowired
	private PaymentDao paymentDao;

	@Autowired
	private PersonalDao personalDao;

	/*
	 * do Query PaymentInfo -->入力確認機能
	 */
	@Override
	public PaymentRes doQueryInfo() {

		// paymentDaoで定義されたメソッドを参照。
		List<PaymentInfo> paymentList = paymentDao.doQueryInfo();

		// 「paymentList」がnullかどうかを判断。
		if (CollectionUtils.isEmpty(paymentList)) {
			return new PaymentRes(RtnInfo.DATA_IS_NOT_FOUND.getMessage());
		}
		return new PaymentRes(paymentList, RtnInfo.DATA_IS_FOUND.getMessage());
	}

	/*
	 * get PaymentInfo By Id --> IDを利用して、該当賃貸物件データを検索
	 */
	@Override
	public PaymentRes getPaymentInfo(int id) {

		// paymentDaoで定義されたメソッドを参照。
		PaymentInfo paymentInfo = paymentDao.getPaymentById(id);

		// 「paymentInfo」がnullかどうかを判断。
		if (paymentInfo == null) {
			return new PaymentRes(RtnInfo.DATA_IS_FOUND.getMessage());
		}
		return new PaymentRes(paymentInfo, RtnInfo.GET_ID_SUCCESSFUL.getMessage());
	}

	/*
	 * do Query By PaymentDate --> 年月檢索機能を利用して、入力確認データを検索
	 */
	@Override
	public PaymentRes doQueryByPaymentDate(LocalDate startDate, LocalDate endDate) {

		// 時間の判断。
		if (startDate.isAfter(endDate) || endDate.isBefore(startDate)) {
			return new PaymentRes(RtnInfo.TIME_FORMAT_IS_FAILED.getMessage());
		}

		// paymentDaoで定義されたメソッドを参照。
		List<PaymentInfo> paymentList = paymentDao.findByPaymentDateBetween(startDate, endDate);

		// 「paymentList」がnullかどうかを判断。
		if (CollectionUtils.isEmpty(paymentList)) {
			return new PaymentRes(RtnInfo.DATA_IS_NOT_FOUND.getMessage());
		}
		return new PaymentRes(paymentList, RtnInfo.DATA_IS_FOUND.getMessage());
	}

	/*
	 * do Query By RentsMonth --> 月份檢索機能を利用して、入力確認データを検索
	 */
	@Override
	public PaymentRes doQueryByRentsMonth(int rentsMonth) {

		// 引数「rentsMonth」が＜＝０かどうかを判断。
		if (rentsMonth <= 0) {
			return new PaymentRes(RtnInfo.RENTMONTH_IS_INCORRECT_DATA.getMessage());
		}

		// paymentDaoで定義されたメソッドを参照。
		List<PaymentInfo> paymentList = paymentDao.doQueryInfoByRentsMonth(rentsMonth);

		// 「paymentList」がnullかどうかを判断。
		if (CollectionUtils.isEmpty(paymentList)) {
			return new PaymentRes(RtnInfo.DATA_IS_NOT_FOUND.getMessage());
		}
		return new PaymentRes(paymentList, RtnInfo.DATA_IS_FOUND.getMessage());
	}

	/*
	 * createPayment --> テナント支払情報の作成
	 */
//	@Override
//	public PaymentDataRes createPayment(String objectId, int paymentDeadline, LocalDate paymentDate,
//			String paymentMethod, int paymentMonths, int rentsMonth) throws Exception {
//
//		// 入力内容のフォーマット判断
//		if (checkParam(paymentDeadline, paymentDate, paymentMethod, paymentMonths, rentsMonth) != null) {
//			return checkParam(paymentDeadline, paymentDate, paymentMethod, paymentMonths, rentsMonth);
//		}
//
//		// 該当賃貸物件の有無を判断
//		Optional<Personal> idOp = personalDao.findById(objectId);
//		if (!idOp.isPresent()) {
//			return new PaymentDataRes(RtnInfo.DATA_IS_NOT_EXIST.getMessage());
//		} else {
//
//			// 同月分の家賃の二重払いは不可
//			List<Payment> paymentList = paymentDao.findByObjectId(objectId);
//			List<Integer> rentMonthList = new ArrayList<>();
//			for (Payment item : paymentList) {
//				rentMonthList.add(item.getRentsMonth());
//			}
//
//			if (rentMonthList.contains(rentsMonth)) {
//				return new PaymentDataRes(RtnInfo.RENTMONTH_IS_ALREADT_EXIST.getMessage());
//			}
//
//			// 自動入金額計算を設定　--> 賃料*月数＝入金額
//			Payment payment = new Payment();
//			Personal personal = idOp.get();
//			payment.setPaymentAmount(personal.getRent() * paymentMonths);
//
//			// 遅れ／入金済みを判断 --> 入金日が入金予定日より遅い場合で
//			if (paymentDate.getDayOfMonth() > paymentDeadline) {
//				payment.setLateChecked(2); // 遅れ
//			} else {
//				payment.setLateChecked(1); // 入金済み
//			}
//
//			// テナント支払情報の作成
//			Payment newPayment = new Payment(payment.getId(), objectId, paymentDeadline, paymentDate, paymentMethod,
//					payment.getLateChecked(), paymentMonths, payment.getPaymentAmount(), rentsMonth);
//			paymentDao.save(newPayment);
//			return new PaymentDataRes(newPayment, RtnInfo.CREATED_SUCCESSFUL.getMessage());
//		}
//
//	}

	/*
	 * updatePayment --> テナント支払情報の更新（編集機能）
	 */
	@Override
	public PaymentDataRes updatePayment(int id, String objectId, LocalDate paymentDate, int paymentMonths,
			int rentsMonth) throws Exception {

		// 入力内容のフォーマット判断
		if (paymentDate == null) {
			return new PaymentDataRes(RtnInfo.DATE_IS_EMPTY.getMessage());
		} else if (paymentMonths < 0) {
			return new PaymentDataRes(RtnInfo.MONTH_IS_INCORRECT_DATA.getMessage());
		} else if (checkMonth(rentsMonth)) {
			return new PaymentDataRes(RtnInfo.RENTMONTH_IS_INCORRECT_DATA.getMessage());
		}

		// 該当テナントの有無を判断
		Optional<Payment> paymentOp = paymentDao.findById(id);
		if (!paymentOp.isPresent()) {
			return new PaymentDataRes(RtnInfo.DATA_IS_NOT_EXIST.getMessage());
		}

		// 自動入金額計算を設定　--> 賃料*月数＝入金額
		Optional<Personal> idOp = personalDao.findById(objectId);
		Personal personal = idOp.get();
		Payment payment = paymentOp.get();

		payment.setPaymentAmount(personal.getRent() * paymentMonths);

		// 遅れ／入金済みを判断 --> 入金日が入金予定日より遅い場合で
		if (paymentDate.getDayOfMonth() > payment.getPaymentDeadline()) {
			payment.setLateChecked(2); // 遅れ
		} else {
			payment.setLateChecked(1); // 入金済み
		}

		// テナント支払情報の更新
		Payment newPayment = new Payment(id, objectId, payment.getPaymentDeadline(), paymentDate,
				payment.getPaymentMethod(), payment.getLateChecked(), paymentMonths, payment.getPaymentAmount(),
				rentsMonth);
		paymentDao.save(newPayment);
		return new PaymentDataRes(newPayment, RtnInfo.UPDATE_SUCCESSFUL.getMessage());
	}

	/* ========================================================== */

//	/* 方法：入力内容のフォーマット判断 */
//	@SuppressWarnings("unused")
//	private PaymentDataRes checkParam(int paymentDeadline, LocalDate paymentDate, String paymentMethod,
//			int paymentMonths, int rentsMonth) throws Exception {
//		if (paymentDeadline < 0 || paymentDeadline > 31) {
//			return new PaymentDataRes(RtnInfo.DEADLINE_IS_INCORRECT_DATA.getMessage());
//		} else if (paymentDate == null) {
//			return new PaymentDataRes(RtnInfo.DATE_IS_EMPTY.getMessage());
//		} else if (paymentMethod == null) {
//			return new PaymentDataRes(RtnInfo.METHOD_IS_INCORRECT_DATA.getMessage());
//		} else if (paymentMonths < 0) {
//			return new PaymentDataRes(RtnInfo.MONTH_IS_INCORRECT_DATA.getMessage());
//		} else if (checkMonth(rentsMonth)) {
//			return new PaymentDataRes(RtnInfo.RENTMONTH_IS_INCORRECT_DATA.getMessage());
//		}
//		return null;
//
//	}

	/* 方法：月数の入力内容のフォーマット判断 */
	private boolean checkMonth(int rentsMonth) {
		List<Integer> week = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
		return !week.contains(rentsMonth);
	}

}
