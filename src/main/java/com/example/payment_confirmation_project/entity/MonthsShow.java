package com.example.payment_confirmation_project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "show_month")
public class MonthsShow {

	@Id
	@Column(name = "object_id")
	private String objectId;

	@Column(name = "january")
	private int january = 0;

	@Column(name = "february")
	private int february = 0;

	@Column(name = "march")
	private int march = 0;

	@Column(name = "april")
	private int april = 0;

	@Column(name = "may")
	private int may = 0;

	@Column(name = "june")
	private int june = 0;

	@Column(name = "july")
	private int july = 0;

	@Column(name = "august")
	private int august = 0;

	@Column(name = "september")
	private int september = 0;

	@Column(name = "october")
	private int october = 0;

	@Column(name = "november")
	private int november = 0;

	@Column(name = "december")
	private int december = 0;

	public MonthsShow() {

	}

	public MonthsShow(String objectId, int january, int february, int march, int april, int may, int june, int july,
			int august, int september, int october, int november, int december) {
		this.objectId = objectId;
		this.january = january;
		this.february = february;
		this.march = march;
		this.april = april;
		this.may = may;
		this.june = june;
		this.july = july;
		this.august = august;
		this.september = september;
		this.october = october;
		this.november = november;
		this.december = december;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public int getJanuary() {
		return january;
	}

	public void setJanuary(int january) {
		this.january = january;
	}

	public int getFebruary() {
		return february;
	}

	public void setFebruary(int february) {
		this.february = february;
	}

	public int getMarch() {
		return march;
	}

	public void setMarch(int march) {
		this.march = march;
	}

	public int getApril() {
		return april;
	}

	public void setApril(int april) {
		this.april = april;
	}

	public int getMay() {
		return may;
	}

	public void setMay(int may) {
		this.may = may;
	}

	public int getJune() {
		return june;
	}

	public void setJune(int june) {
		this.june = june;
	}

	public int getJuly() {
		return july;
	}

	public void setJuly(int july) {
		this.july = july;
	}

	public int getAugust() {
		return august;
	}

	public void setAugust(int august) {
		this.august = august;
	}

	public int getSeptember() {
		return september;
	}

	public void setSeptember(int september) {
		this.september = september;
	}

	public int getOctober() {
		return october;
	}

	public void setOctober(int october) {
		this.october = october;
	}

	public int getNovember() {
		return november;
	}

	public void setNovember(int november) {
		this.november = november;
	}

	public int getDecember() {
		return december;
	}

	public void setDecember(int december) {
		this.december = december;
	}

}
