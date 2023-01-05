package com.example.payment_confirmation_project.repository;

import java.time.LocalDate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.payment_confirmation_project.entity.Payment;
import com.example.payment_confirmation_project.vo.PaymentInfo;

@Repository
public interface PaymentDao extends JpaRepository<Payment, Integer> {

	/* doQueryInfo */
	@Query("select new com.example.payment_confirmation_project.vo.PaymentInfo"
			+ "(pay.id, pay.objectId, per.building, per.ownerName, per.tenantName, per.rent, pay.paymentDeadline,"
			+ " pay.paymentDate, pay.paymentMethod, pay.lateChecked, pay.paymentMonths, pay.paymentAmount, pay.rentsMonth) "
			+ "	from Personal per join Payment pay on per.objectId = pay.objectId order by pay.paymentDate DESC")
	public List<PaymentInfo> doQueryInfo();

	/* doQueryInfo ById */
	@Query("select new com.example.payment_confirmation_project.vo.PaymentInfo"
			+ "(pay.id, pay.objectId, per.building, per.ownerName, per.tenantName, per.rent, pay.paymentDeadline,"
			+ " pay.paymentDate, pay.paymentMethod, pay.lateChecked, pay.paymentMonths, pay.paymentAmount, pay.rentsMonth) "
			+ "	from Personal per join Payment pay on per.objectId = pay.objectId " + "	where pay.id = :id ")
	public PaymentInfo getPaymentById(@Param("id") int id);

	/* doQueryInfo ByPaymentDate */
	@Query("select new com.example.payment_confirmation_project.vo.PaymentInfo"
			+ "(pay.id, pay.objectId, per.building, per.ownerName, per.tenantName, per.rent, pay.paymentDeadline,"
			+ " pay.paymentDate, pay.paymentMethod, pay.lateChecked, pay.paymentMonths, pay.paymentAmount, pay.rentsMonth) "
			+ "	from Personal per join Payment pay on per.objectId = pay.objectId "
			+ "	where pay.paymentDate between :startDate and :endDate")
	public List<PaymentInfo> findByPaymentDateBetween(@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate);

	/* doQueryInfo ByRentsMonth */
	@Query("select new com.example.payment_confirmation_project.vo.PaymentInfo"
			+ "(pay.id, pay.objectId, per.building, per.ownerName, per.tenantName, per.rent, pay.paymentDeadline,"
			+ " pay.paymentDate, pay.paymentMethod, pay.lateChecked, pay.paymentMonths, pay.paymentAmount, pay.rentsMonth) "
			+ "	from Personal per join Payment pay on per.objectId = pay.objectId "
			+ "	where pay.rentsMonth = :rentsMonth ")
	public List<PaymentInfo> doQueryInfoByRentsMonth(@Param("rentsMonth") int rentsMonth);
	
	public List<Payment> findByObjectId(String objectId);

	public List<PaymentInfo> doQueryWithPageSizeAndStartPosition(int pageSize, int startPosition);

}
