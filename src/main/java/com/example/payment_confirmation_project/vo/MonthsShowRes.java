package com.example.payment_confirmation_project.vo;

import java.util.List;

import com.example.payment_confirmation_project.entity.MonthsShow;

public class MonthsShowRes {

	private MonthsShow monthsShow;

	private List<MonthsInfo> monthsList;

	private String message;

	public MonthsShowRes() {

	}

	public MonthsShowRes(String message) {
		this.message = message;
	}

	public MonthsShowRes(List<MonthsInfo> monthsList, String message) {
		this.monthsList = monthsList;
		this.message = message;
	}

	public MonthsShowRes(MonthsShow monthsShow, String message) {
		this.monthsShow = monthsShow;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MonthsShow getMonthsShow() {
		return monthsShow;
	}

	public void setMonthsShow(MonthsShow monthsShow) {
		this.monthsShow = monthsShow;
	}

	public List<MonthsInfo> getMonthsList() {
		return monthsList;
	}

	public void setMonthsList(List<MonthsInfo> monthsList) {
		this.monthsList = monthsList;
	}

}
