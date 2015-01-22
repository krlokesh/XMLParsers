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
		this.ExceptionMsgCode = exceptionMsgCode;
		this.ExceptionMsg = exceptionMsg;
		this.Cause = cause;
		this.Resolution = resolution;
		this.Notes = notes;
		this.Type = type;
		this.Reviewed = reviewed;
	}

	public int getReviewed() {
		return this.Reviewed;
	}

	public void setReviewed(int reviewed) {
		this.Reviewed = reviewed;
	}

	public String getExceptionMsg() {
		return this.ExceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.ExceptionMsg = exceptionMsg;
	}

	public String getCause() {
		return this.Cause;
	}

	public void setCause(String cause) {
		this.Cause = cause;
	}

	public String getResolution() {
		return this.Resolution;
	}

	public void setResolution(String resolution) {
		this.Resolution = resolution;
	}

	public String getNotes() {
		return this.Notes;
	}

	public void setNotes(String notes) {
		this.Notes = notes;
	}

	public String getType() {
		return this.Type;
	}

	public void setType(String type) {
		this.Type = type;
	}

	
	public String getExceptionMsgCode() {
		return this.ExceptionMsgCode;
	}

	public void setExceptionMsgCode(String exceptionMsgCode) {
		this.ExceptionMsgCode = exceptionMsgCode;
	}
	
	public String toCSVString() {
		 final String COMMA = ", ";
		 final String DOUBLE_QUOTE = "\"";
		 StringBuilder retValue = new StringBuilder();
		 retValue.append(DOUBLE_QUOTE).append(this.Type).append(DOUBLE_QUOTE)
		  .append(COMMA).append(DOUBLE_QUOTE).append(this.Cause).append(DOUBLE_QUOTE)
		  .append(COMMA).append(DOUBLE_QUOTE).append(this.Resolution).append(DOUBLE_QUOTE)
		  .append(COMMA).append(DOUBLE_QUOTE).append(this.Notes).append(DOUBLE_QUOTE);
		 
		
		// New line character was not properly creating the CSV file
		int newLineIndex = 0;
		while((newLineIndex = retValue.indexOf("\n")) != -1){
			retValue.replace(newLineIndex, newLineIndex+1, "");
		}
		
		return retValue.toString();

	}
	
	public String toString() {
		 final String TAB = "    \n";
		 StringBuilder retValue = new StringBuilder();
		 retValue.append("ActionalLogAnalyzer (\n ")
		  .append(super.toString()).append(TAB)
		  .append(" ExceptionMsgCode = ").append(this.ExceptionMsgCode).append(TAB)
		  .append(" ExceptionMsg = ").append(this.ExceptionMsg).append(TAB)
		  .append(" Cause = ").append(this.Cause).append(TAB)
		  .append(" Resolution = ").append(this.Resolution).append(TAB)
		  .append(" Notes = ").append(this.Notes).append(TAB)
		  .append(" Type = ").append(this.Type).append(TAB)
		  .append(" Reviewed = ").append(this.Reviewed).append(TAB);
		  		  
		return retValue.toString();

	}
}
