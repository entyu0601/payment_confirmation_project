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

	/* do Query -->���o��Ʈw�Ҧ����(�w�X�ֹL����i���T) */
	@Override
	public PaymentRes doQueryInfo() {
		List<PaymentInfo> paymentList = paymentDao.doQueryInfo();
		if (CollectionUtils.isEmpty(paymentList)) {
			return new PaymentRes(RtnInfo.DATA_NOT_FOUND.getMessage());
		}
		return new PaymentRes(paymentList, RtnInfo.DATA_IS_FOUND.getMessage());
	}

	/* getPaymentInfo ById -->�Q�Χ��payment�ߤ@ID�A���o���@�㵧��� */
	@Override
	public PaymentRes getPaymentInfo(int id) {
		PaymentInfo paymentInfo = paymentDao.getPaymentById(id);
		if (paymentInfo == null) {
			return new PaymentRes(RtnInfo.DATA_IS_FOUND.getMessage());
		}
		return new PaymentRes(paymentInfo, RtnInfo.GET_ID_SUCCESSFUL.getMessage());
	}

	/* do Query By PaymentDate --> �~���˯����� */
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

	/* do Query By RentsMonth --> ����˯����� */
	@Override
	public PaymentRes doQueryByRentsMonth(int rentsMonth) {
		List<PaymentInfo> paymentList = paymentDao.doQueryInfoByRentsMonth(rentsMonth);
		if (CollectionUtils.isEmpty(paymentList)) {
			return new PaymentRes(RtnInfo.DATA_NOT_FOUND.getMessage());
		}
		return new PaymentRes(paymentList, RtnInfo.DATA_IS_FOUND.getMessage());
	}

	/* createPayment --> �Ыؿ�J��� */
	@Override
	public PaymentDataRes createPayment(String objectId, int paymentDeadline, LocalDate paymentDate,
			String paymentMethod, int paymentMonths, int rentsMonth) throws Exception {

		// �P�_��J���e�榡
		if (checkParam(paymentDeadline, paymentDate, paymentMethod, paymentMonths, rentsMonth) != null) {
			return checkParam(paymentDeadline, paymentDate, paymentMethod, paymentMonths, rentsMonth);
		}

		// �P�_�O�_�������θ��
		Optional<Personal> idOp = personalDao.findById(objectId);
		if (!idOp.isPresent()) {
			return new PaymentDataRes(RtnInfo.DATA_NOT_EXIST.getMessage());
		} else {

			// ���o����ú��ۦP����Я�
			List<Payment> paymentList = paymentDao.findByObjectId(objectId);
			List<Integer> rentMonthList = new ArrayList<>();
			for (Payment item : paymentList) {
				rentMonthList.add(item.getRentsMonth());
			}

			if (rentMonthList.contains(rentsMonth)) {
				return new PaymentDataRes(RtnInfo.RENTMONTH_ALREADT_EXIST.getMessage());
			}

			// �]�w �۰ʤJ�O���B
			Payment payment = new Payment();
			Personal personal = idOp.get();
			payment.setPaymentAmount(personal.getRent() * paymentMonths);

			// �P�w ���/�wú��
			if (paymentDate.getDayOfMonth() > paymentDeadline) {
				payment.setLateChecked(2); // ���
			} else {
				payment.setLateChecked(1); // �wú��
			}

			// �Ы�Payment
			Payment newPayment = new Payment(payment.getId(), objectId, paymentDeadline, paymentDate, paymentMethod,
					payment.getLateChecked(), paymentMonths, payment.getPaymentAmount(), rentsMonth);
			paymentDao.save(newPayment);
			return new PaymentDataRes(newPayment, RtnInfo.CREATED_SUCCESSFUL.getMessage());
		}

	}

	/* updatePayment --> �s��\��(��ʤ��) */
	@Override
	public PaymentDataRes updatePayment(int id, String objectId, LocalDate paymentDate, int paymentMonths,
			int rentsMonth) throws Exception {

		// �P�_��J���e�榡
		if (paymentDate == null) {
			return new PaymentDataRes(RtnInfo.DATE_INCORRECT_DATA.getMessage());
		} else if (paymentMonths < 0) {
			return new PaymentDataRes(RtnInfo.MONTH_INCORRECT_DATA.getMessage());
		} else if (checkMonth(rentsMonth)) {
			return new PaymentDataRes(RtnInfo.RENTMONTH_INCORRECT_DATA.getMessage());
		}

		// ���n�ק諸���ȸ��
		Optional<Payment> paymentOp = paymentDao.findById(id);
		if (!paymentOp.isPresent()) {
			return new PaymentDataRes(RtnInfo.DATA_NOT_EXIST.getMessage());
		}

		// �]�w ���oú���Ƥ��/�۰ʤJ�O���B
		Optional<Personal> idOp = personalDao.findById(objectId);
		Personal personal = idOp.get();
		Payment payment = paymentOp.get();

		payment.setPaymentAmount(personal.getRent() * paymentMonths);

		// �P�w ���/�wú��
		if (paymentDate.getDayOfMonth() > payment.getPaymentDeadline()) {
			payment.setLateChecked(2); // ���
		} else {
			payment.setLateChecked(1); // �wú��
		}

		// �Ы�Payment
		Payment newPayment = new Payment(id, objectId, payment.getPaymentDeadline(), paymentDate,
				payment.getPaymentMethod(), payment.getLateChecked(), paymentMonths, payment.getPaymentAmount(),
				rentsMonth);
		paymentDao.save(newPayment);
		return new PaymentDataRes(newPayment, RtnInfo.UPDATE_SUCCESSFUL.getMessage());
	}
	
	
	/* ========================================================== */

	/* �ˬd��J�榡 */
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

	/* �ˬdRentsMonth�O�_�ŦX�榡 */
	private boolean checkMonth(int rentsMonth) {
		List<Integer> week = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
		return !week.contains(rentsMonth);
	}

	

}
