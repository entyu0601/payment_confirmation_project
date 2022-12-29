package com.example.payment_confirmation_project;

import java.text.ParseException;

import java.time.LocalDate;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.payment_confirmation_project.repository.PaymentDao;
import com.example.payment_confirmation_project.vo.PaymentInfo;

@SpringBootTest
public class PaymentTest {

	@Autowired
	private PaymentDao paymentDao;

	@Test
	public void doQueryTest() throws ParseException {
		List<PaymentInfo> result = paymentDao.doQueryInfo();
		System.out.println(result.size());
		for (PaymentInfo item : result) {
			System.out.println(item.getId());
		}
	}

	@Test
	public void doQueryWithStarPositionTest() throws ParseException {
		List<PaymentInfo> result = paymentDao.doQueryWithPageSizeAndStartPosition(5, 0); //
		System.out.println(result.size());
		for (PaymentInfo item : result) {
			System.out.println(item.getBuilding());
		}
	}
}
