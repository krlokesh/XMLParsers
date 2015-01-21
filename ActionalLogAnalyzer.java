package com.basics.xmlParsers;

public class ActionalLogAnalyzer {
	
	private String ExceptionMsgCode;
	private String ExceptionMsg;
	private String Cause;
	private String Resolution;
	private String Notes;
	private String Type;
	private int Reviewed;
	
	public ActionalLogAnalyzer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ActionalLogAnalyzer(String exceptionMsgCode, String exceptionMsg,
			String cause, String resolution, String notes, String type,
			int reviewed) {
		super();
		ExceptionMsgCode = exceptionMsgCode;
		ExceptionMsg = exceptionMsg;
		Cause = cause;
		Resolution = resolution;
		Notes = notes;
		Type = type;
		Reviewed = reviewed;
	}

	public int getReviewed() {
		return Reviewed;
	}

	public void setReviewed(int reviewed) {
		Reviewed = reviewed;
	}

	public String getExceptionMsg() {
		return ExceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		exceptionMsg = exceptionMsg;
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
		exceptionMsgCode = exceptionMsgCode;
	}
	
	public String toString() {
		 final String TAB = "    \n";
		 StringBuilder retValue = new StringBuilder();
		 retValue.append("ActionalLogAnalyzer (\n ")
		  .append(super.toString()).append(TAB)
		  .append("     ExceptionMsgCode = ").append(this.ExceptionMsgCode).append(TAB)
		  .append("     ExceptionMsg = ").append(this.ExceptionMsg).append(TAB)
		  .append("     Cause = ").append(this.Cause).append(TAB)
		  .append("     Resolution = ").append(this.Resolution).append(TAB)
		  .append("     Notes = ").append(this.Notes).append(TAB)
		  .append("     Type = ").append(this.Type).append(TAB)
		  .append("     Reviewed = ").append(this.Reviewed).append(TAB);
		  		  

		return retValue.toString();

	}
}
