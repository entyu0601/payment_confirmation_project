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

	/* do Query -->取得資料庫所有資料(已合併過的兩張表資訊) */
	@Override
	public PaymentRes doQueryInfo() {
		List<PaymentInfo> paymentList = paymentDao.doQueryInfo();
		if (CollectionUtils.isEmpty(paymentList)) {
			return new PaymentRes(RtnInfo.DATA_NOT_FOUND.getMessage());
		}
		return new PaymentRes(paymentList, RtnInfo.DATA_IS_FOUND.getMessage());
	}

	/* getPaymentInfo ById -->利用抓取payment唯一ID，取得那一整筆資料 */
	@Override
	public PaymentRes getPaymentInfo(int id) {
		PaymentInfo paymentInfo = paymentDao.getPaymentById(id);
		if (paymentInfo == null) {
			return new PaymentRes(RtnInfo.DATA_IS_FOUND.getMessage());
		}
		return new PaymentRes(paymentInfo, RtnInfo.GET_ID_SUCCESSFUL.getMessage());
	}

	/* do Query By PaymentDate --> 年月檢索機能 */
	@Override
	public PaymentRes doQueryByPaymentDate(LocalDate startDate, LocalDate endDate) {

		if (startDate.isAfter(endDate) || endDate.isBefore(startDate)) {
			return new PaymentRes(RtnInfo.TIME_SELECT_WRONG.getMessage());
		}

		List<PaymentInfo> paymentList = paymentDao.findByPaymentDateBetween(startDate, endDate);
		if (CollectionUtils.isEmpty(paymentList)) {
			return new PaymentRes(RtnInfo.DATA_NOT_FOUND.getMessage());
		}
		return new PaymentRes(paymentList, RtnInfo.DATA_IS_FOUND.getMessage());
	}

	/* do Query By RentsMonth --> 月份檢索機能 */
	@Override
	public PaymentRes doQueryByRentsMonth(int rentsMonth) {
		List<PaymentInfo> paymentList = paymentDao.doQueryInfoByRentsMonth(rentsMonth);
		if (CollectionUtils.isEmpty(paymentList)) {
			return new PaymentRes(RtnInfo.DATA_NOT_FOUND.getMessage());
		}
		return new PaymentRes(paymentList, RtnInfo.DATA_IS_FOUND.getMessage());
	}

	/* createPayment --> 創建輸入資料 */
	@Override
	public PaymentDataRes createPayment(String objectId, int paymentDeadline, LocalDate paymentDate,
			String paymentMethod, int paymentMonths, int rentsMonth) throws Exception {

		// 判斷輸入內容格式
		if (checkParam(paymentDeadline, paymentDate, paymentMethod, paymentMonths, rentsMonth) != null) {
			return checkParam(paymentDeadline, paymentDate, paymentMethod, paymentMonths, rentsMonth);
		}

		// 判斷是否有此租屋資料
		Optional<Personal> idOp = personalDao.findById(objectId);
		if (!idOp.isPresent()) {
			return new PaymentDataRes(RtnInfo.DATA_NOT_EXIST.getMessage());
		} else {

			// 不得重複繳交相同月份房租
			List<Payment> paymentList = paymentDao.findByObjectId(objectId);
			List<Integer> rentMonthList = new ArrayList<>();
			for (Payment item : paymentList) {
				rentMonthList.add(item.getRentsMonth());
			}

			if (rentMonthList.contains(rentsMonth)) {
				return new PaymentDataRes(RtnInfo.RENTMONTH_ALREADT_EXIST.getMessage());
			}

			// 設定 自動入力金額
			Payment payment = new Payment();
			Personal personal = idOp.get();
			payment.setPaymentAmount(personal.getRent() * paymentMonths);

			// 判定 遲交/已繳交
			if (paymentDate.getDayOfMonth() > paymentDeadline) {
				payment.setLateChecked(2); // 遲交
			} else {
				payment.setLateChecked(1); // 已繳交
			}

			// 創建Payment
			Payment newPayment = new Payment(payment.getId(), objectId, paymentDeadline, paymentDate, paymentMethod,
					payment.getLateChecked(), paymentMonths, payment.getPaymentAmount(), rentsMonth);
			paymentDao.save(newPayment);
			return new PaymentDataRes(newPayment, RtnInfo.CREATED_SUCCESSFUL.getMessage());
		}

	}

	/* updatePayment --> 編輯功能(更動日期) */
	@Override
	public PaymentDataRes updatePayment(int id, String objectId, LocalDate paymentDate, int paymentMonths,
			int rentsMonth) throws Exception {

		// 判斷輸入內容格式
		if (paymentDate == null) {
			return new PaymentDataRes(RtnInfo.DATE_INCORRECT_DATA.getMessage());
		} else if (paymentMonths < 0) {
			return new PaymentDataRes(RtnInfo.MONTH_INCORRECT_DATA.getMessage());
		} else if (checkMonth(rentsMonth)) {
			return new PaymentDataRes(RtnInfo.RENTMONTH_INCORRECT_DATA.getMessage());
		}

		// 找到要修改的租客資料
		Optional<Payment> paymentOp = paymentDao.findById(id);
		if (!paymentOp.isPresent()) {
			return new PaymentDataRes(RtnInfo.DATA_NOT_EXIST.getMessage());
		}

		// 設定 不得繳重複月份/自動入力金額
		Optional<Personal> idOp = personalDao.findById(objectId);
		Personal personal = idOp.get();
		Payment payment = paymentOp.get();

		payment.setPaymentAmount(personal.getRent() * paymentMonths);

		// 判定 遲交/已繳交
		if (paymentDate.getDayOfMonth() > payment.getPaymentDeadline()) {
			payment.setLateChecked(2); // 遲交
		} else {
			payment.setLateChecked(1); // 已繳交
		}

		// 創建Payment
		Payment newPayment = new Payment(id, objectId, payment.getPaymentDeadline(), paymentDate,
				payment.getPaymentMethod(), payment.getLateChecked(), paymentMonths, payment.getPaymentAmount(),
				rentsMonth);
		paymentDao.save(newPayment);
		return new PaymentDataRes(newPayment, RtnInfo.UPDATE_SUCCESSFUL.getMessage());
	}
	
	
	/* ========================================================== */

	/* 檢查輸入格式 */
	private PaymentDataRes checkParam(int paymentDeadline, LocalDate paymentDate, String paymentMethod,
			int paymentMonths, int rentsMonth) throws Exception {
		if (paymentDeadline < 0 || paymentDeadline > 31) {
			return new PaymentDataRes(RtnInfo.DEADLINE_INCORRECT_DATA.getMessage());
		} else if (paymentDate == null) {
			return new PaymentDataRes(RtnInfo.DATE_INCORRECT_DATA.getMessage());
		} else if (paymentMethod == null) {
			return new PaymentDataRes(RtnInfo.METHOD_INCORRECT_DATA.getMessage());
		} else if (paymentMonths < 0) {
			return new PaymentDataRes(RtnInfo.MONTH_INCORRECT_DATA.getMessage());
		} else if (checkMonth(rentsMonth)) {
			return new PaymentDataRes(RtnInfo.RENTMONTH_INCORRECT_DATA.getMessage());
		}
		return null;

	}

	/* 檢查RentsMonth是否符合格式 */
	private boolean checkMonth(int rentsMonth) {
		List<Integer> week = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
		return !week.contains(rentsMonth);
	}

	

}
