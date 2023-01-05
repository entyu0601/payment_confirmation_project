package com.example.payment_confirmation_project.constants;

public enum RtnInfo {

	DATA_IS_FOUND("200", "データ検索成功"),
	GET_ID_SUCCESSFUL("200", "IDの取得成功"),
	UPDATE_SUCCESSFUL("200", "データの更新に成功"),
	
	DATA_IS_NOT_FOUND("400", "データが見つからない"),
	DATA_IS_NOT_EXIST("400", "この物件についての情報なし"),
	DATE_IS_EMPTY("400", "時間は空けてはいけない"),
	TIME_FORMAT_IS_FAILED("400", "不適切な時間範囲を選択"),
	RENTMONTH_IS_INCORRECT_DATA("400", "範囲は1月から12月です"),
	MONTH_IS_INCORRECT_DATA("400", "支払月份は0ヶ月以下ではいけない");

	/* create Payment */
//	CREATED_SUCCESSFUL("200", "データ作成成功"),
//	RENTMONTH_IS_ALREADT_EXIST("400", "該当月の家賃は入金済み"),
//	DEADLINE_IS_INCORRECT_DATA("400", "予定日の範囲は1日～31日です。"),
//	METHOD_IS_INCORRECT_DATA("400", "入金方法は空けてはいけない");
		

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
