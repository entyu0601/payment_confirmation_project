package com.example.payment_confirmation_project.vo;

public class MonthsInfo {

	private String objectId;

	private String building;

	private String ownerName;

	private String tenantName;

	private int january;

	private int february;

	private int march;

	private int april;

	private int may;

	private int june;

	private int july;

	private int august;

	private int september;

	private int october;

	private int november;

	private int december;

	public MonthsInfo() {

	}

	public MonthsInfo(String objectId, String building, String ownerName, String tenantName, int january, int february,
			int march, int april, int may, int june, int july, int august, int september, int october, int november,
			int december) {
		this.objectId = objectId;
		this.building = building;
		this.ownerName = ownerName;
		this.tenantName = tenantName;
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

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
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
