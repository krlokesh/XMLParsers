package com.basics.xmlParsers;

public class DBMapper {
	
	private String ExceptionMsgCode;
	private String ExceptionMsg;
	private String Cause;
	private String Resolution;
	private String Notes;
	private String Type;
			
	public String getExceptionMsg() {
		return ExceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		ExceptionMsg = exceptionMsg;
	}

	public String getCause() {
		return Cause;
	}

	public void setCause(String cause) {
		Cause = cause;
	}

	public String getResolution() {
		return Resolution;
	}

	public void setResolution(String resolution) {
		Resolution = resolution;
	}

	public String getNotes() {
		return Notes;
	}

	public void setNotes(String notes) {
		Notes = notes;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	
	public String getExceptionMsgCode() {
		return ExceptionMsgCode;
	}

	public void setExceptionMsgCode(String exceptionMsgCode) {
		ExceptionMsgCode = exceptionMsgCode;
	}
	
	public String toString() {
		return "\"" + Type + "\",\"" + Cause + "\",\"" + Resolution + "\",\"" + Notes + "\"";

	}
}
