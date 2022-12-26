package com.example.payment_confirmation_project.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.payment_confirmation_project.constants.RtnInfo;
import com.example.payment_confirmation_project.entity.MonthsShow;
import com.example.payment_confirmation_project.entity.Payment;
import com.example.payment_confirmation_project.entity.Personal;
import com.example.payment_confirmation_project.repository.MonthsShowDao;
import com.example.payment_confirmation_project.repository.PaymentDao;
import com.example.payment_confirmation_project.repository.PersonalDao;
import com.example.payment_confirmation_project.service.ifs.PaymentService;
import com.example.payment_confirmation_project.vo.MonthsInfo;
import com.example.payment_confirmation_project.vo.MonthsShowRes;
import com.example.payment_confirmation_project.vo.PaymentDataRes;
import com.example.payment_confirmation_project.vo.PaymentInfo;
import com.example.payment_confirmation_project.vo.PaymentRes;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentDao paymentDao;

	@Autowired
	private PersonalDao personalDao;

	@Autowired
	private MonthsShowDao monthsShowDao;

	/* do Query */
	@Override
	public PaymentRes doQueryInfo() {
		List<PaymentInfo> paymentList = paymentDao.doQueryInfo();
		if (CollectionUtils.isEmpty(paymentList)) {
			return new PaymentRes(RtnInfo.DATA_NOT_FOUND.getMessage());
		}
		return new PaymentRes(paymentList, RtnInfo.DATA_IS_FOUND.getMessage());
	}

	/* do Query By ObjectId */
	@Override
	public PaymentRes doQueryInfo(String objectId) {
		List<PaymentInfo> paymentList = paymentDao.doQueryInfo(objectId);
		if (CollectionUtils.isEmpty(paymentList)) {
			return new PaymentRes(RtnInfo.DATA_NOT_FOUND.getMessage());
		}
		return new PaymentRes(paymentList, RtnInfo.DATA_IS_FOUND.getMessage());
	}

	/* do Query By PaymentDate */
	@Override
	public PaymentRes doQueryByPaymentDate(LocalDate paymentDate) {
		List<PaymentInfo> paymentList = paymentDao.doQueryInfoByPaymentDate(paymentDate);
		if (CollectionUtils.isEmpty(paymentList)) {
			return new PaymentRes(RtnInfo.DATA_NOT_FOUND.getMessage());
		}
		return new PaymentRes(paymentList, RtnInfo.DATA_IS_FOUND.getMessage());
	}

	/* do Query By RentsMonth */
	@Override
	public PaymentRes doQueryByRentsMonth(int rentsMonth) {
		List<PaymentInfo> paymentList = paymentDao.doQueryInfoByRentsMonth(rentsMonth);
		if (CollectionUtils.isEmpty(paymentList)) {
			return new PaymentRes(RtnInfo.DATA_NOT_FOUND.getMessage());
		}
		return new PaymentRes(paymentList, RtnInfo.DATA_IS_FOUND.getMessage());
	}

	/* createPayment */
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
			return new PaymentDataRes(newPayment, RtnInfo.SUCCESSFUL.getMessage());
		}

	}

	/* updatePayment */
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

		if (payment.getRentsMonth() == rentsMonth) {
			return new PaymentDataRes(RtnInfo.RENTMONTH_ALREADT_EXIST.getMessage());
		}

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
		return new PaymentDataRes(newPayment, RtnInfo.SUCCESSFUL.getMessage());
	}

	/* do Query MonthsInfo */
	@Override
	public MonthsShowRes doQueryMonthsInfo() {
		List<MonthsInfo> monthsList = monthsShowDao.doQueryMonthsInfo();
		if (CollectionUtils.isEmpty(monthsList)) {
			return new MonthsShowRes(RtnInfo.DATA_NOT_FOUND.getMessage());
		}
		return new MonthsShowRes(monthsList, RtnInfo.DATA_IS_FOUND.getMessage());
	}

	/* getPaymentInfo */
	@Override
	public PaymentRes getPaymentInfo(int id) {
		PaymentInfo paymentInfo = paymentDao.getPaymentById(id);
		if (paymentInfo == null) {
			return new PaymentRes(RtnInfo.DATA_IS_FOUND.getMessage());
		}
		return new PaymentRes(paymentInfo, RtnInfo.SUCCESSFUL.getMessage());
	}

	/* set 12months checked */
	@Override
	public MonthsShowRes allMonths(String objectId) {

		if (!StringUtils.hasText(objectId)) {
			return new MonthsShowRes(RtnInfo.VALUE_REQUIRED.getMessage());
		}

		MonthsShowRes checkResult = checkObjectIdExist(objectId);
		if (checkResult != null) {
			return checkResult;
		}

		List<Payment> payment = paymentDao.findByObjectId(objectId);
		MonthsShow month = new MonthsShow();

		for (int i = 0; i < payment.size(); i++) {

			switch (payment.get(i).getRentsMonth()) {

			case 1:
				month.setJanuary(payment.get(i).getLateChecked());
				break;
			case 2:
				month.setFebruary(payment.get(i).getLateChecked());
				break;
			case 3:
				month.setMarch(payment.get(i).getLateChecked());
				break;
			case 4:
				month.setApril(payment.get(i).getLateChecked());
				break;
			case 5:
				month.setMay(payment.get(i).getLateChecked());
				break;
			case 6:
				month.setJune(payment.get(i).getLateChecked());
				break;
			case 7:
				month.setJuly(payment.get(i).getLateChecked());
				break;
			case 8:
				month.setAugust(payment.get(i).getLateChecked());
				break;
			case 9:
				month.setSeptember(payment.get(i).getLateChecked());
				break;
			case 10:
				month.setOctober(payment.get(i).getLateChecked());
				break;
			case 11:
				month.setNovember(payment.get(i).getLateChecked());
				break;
			case 12:
				month.setDecember(payment.get(i).getLateChecked());
				break;
			}
		}
		MonthsShow newmonth = new MonthsShow(objectId, month.getJanuary(), month.getFebruary(), month.getMarch(),
				month.getApril(), month.getMay(), month.getJune(), month.getJuly(), month.getAugust(),
				month.getSeptember(), month.getOctober(), month.getNovember(), month.getDecember());
		monthsShowDao.save(newmonth);
		return new MonthsShowRes(newmonth, RtnInfo.SUCCESSFUL.getMessage());
	}

	/* ========================================================== */
	/* 檢查格式 */
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

	/* 判斷房屋是否存在 */
	private MonthsShowRes checkObjectIdExist(String objectId) {
		List<Payment> paymentList = paymentDao.findByObjectId(objectId);
		Set<String> oldIdSet = new HashSet<>();
		Set<String> noExistIdSet = new HashSet<>();
		for (Payment existItem : paymentList) {
			oldIdSet.add(existItem.getObjectId());
		}

		if (!oldIdSet.contains(objectId)) {
			noExistIdSet.add(objectId);
		}

		if (!CollectionUtils.isEmpty(noExistIdSet)) {
			return new MonthsShowRes(RtnInfo.DATA_NOT_EXIST.getMessage());
		}
		return null;
	}

}
