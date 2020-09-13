package ru.omsu.imit.main.utils;

public enum ErrorCode {

	SUCCESS("", ""),
	ORGANIZATION_NOT_FOUND("organization", "Organization not found"),
	DEPARTMENT_NOT_FOUND("department", "Department not found"),
	EMPLOYEE_NOT_FOUND("employee", "Employee not found"),
	SCHEDULE_NOT_FOUND("schedule", "Schedule not found"),
	EMPTY_PARAM("json", "Param is null or empty"),
	NULL_REQUEST("json", "Null request"),
	JSON_PARSE_EXCEPTION("json", "Json parse exception :  %s"),
	WRONG_URL("url", "Wrong URL"),
	METHOD_NOT_ALLOWED("url", "Method not allowed")
	;	

	private String field;
	private String message;

	private ErrorCode(String field, String message) {
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}


}
