package com.example.payment_confirmation_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.payment_confirmation_project.entity.MonthsShow;
import com.example.payment_confirmation_project.vo.MonthsInfo;

@Repository
public interface MonthsShowDao extends JpaRepository<MonthsShow, String> {

	/* doQueryMonthsInfo */
	@Query("select new com.example.payment_confirmation_project.vo.MonthsInfo"
			+ "(per.objectId, per.building, per.ownerName, per.tenantName,"
			+ " month.january, month.february, month.march, month.april, month.may, month.june,"
			+ " month.july, month.august, month.september, month.october, month.november, month.december) "
			+ "	from Personal per join MonthsShow month on per.objectId = month.objectId ")
	public List<MonthsInfo> doQueryMonthsInfo();
}
