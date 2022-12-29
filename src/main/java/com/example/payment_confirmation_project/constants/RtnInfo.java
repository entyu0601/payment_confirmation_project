package com.example.payment_confirmation_project.constants;

public enum RtnInfo {

	CREATED_SUCCESSFUL("200", "��ƳЫئ��\"),
	GET_ID_SUCCESSFUL("200", "���\���ID"),
	UPDATE_SUCCESSFUL("200", "��Ƨ�s���\"),
	TIME_SELECT("200", "���\���"),
	DATA_IS_FOUND("200", "�����"),
	
	DATA_NOT_FOUND("400", "Data is not found!"),
	DATA_NOT_EXIST("400", "�L���ЫΰT���A�Э��s��g"),
	TIME_SELECT_WRONG("400", "����ɶ��d�򤣦X�榡!"),
	RENTMONTH_ALREADT_EXIST("400", "�wú�L�Ӥ�����Я�"),
	
	DEADLINE_INCORRECT_DATA("400", "���w���Ƥ��o���šA�d��b1~31�餧��"),
	DATE_INCORRECT_DATA("400", "�ɶ����o����"),
	METHOD_INCORRECT_DATA("400", "�J����k���o����"),
	CHECKED_INCORRECT_DATA("400", "Checked�d��b0~2"),
	MONTH_INCORRECT_DATA("400", "ú�������o�p��0"),
	AMOUNT_INCORRECT_DATA("400", "�J�����B���o�p��0"),
	RENTMONTH_INCORRECT_DATA("400", "��ƽd��b1~12��");	

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
