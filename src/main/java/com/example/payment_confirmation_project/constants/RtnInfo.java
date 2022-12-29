package com.example.payment_confirmation_project.constants;

public enum RtnInfo {

	CREATED_SUCCESSFUL("200", "資料創建成功"),
	GET_ID_SUCCESSFUL("200", "成功抓取ID"),
	UPDATE_SUCCESSFUL("200", "資料更新成功"),
	TIME_SELECT("200", "成功選取"),
	DATA_IS_FOUND("200", "找到資料"),
	
	DATA_NOT_FOUND("400", "Data is not found!"),
	DATA_NOT_EXIST("400", "無此房屋訊息，請重新填寫"),
	TIME_SELECT_WRONG("400", "選取時間範圍不合格式!"),
	RENTMONTH_ALREADT_EXIST("400", "已繳過該月份的房租"),
	
	DEADLINE_INCORRECT_DATA("400", "予定日資料不得為空，範圍在1~31日之內"),
	DATE_INCORRECT_DATA("400", "時間不得為空"),
	METHOD_INCORRECT_DATA("400", "入金方法不得為空"),
	CHECKED_INCORRECT_DATA("400", "Checked範圍在0~2"),
	MONTH_INCORRECT_DATA("400", "繳交月份不得小於0"),
	AMOUNT_INCORRECT_DATA("400", "入金金額不得小於0"),
	RENTMONTH_INCORRECT_DATA("400", "月數範圍在1~12月");	

	private String code;

	private String message;

	private RtnInfo(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
