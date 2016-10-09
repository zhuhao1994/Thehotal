package com.hotel.pojo;

 
public class Logtable implements java.io.Serializable {

	private Integer logid;
	private Usertable usertable;
	private String log;
	private String suggestion;

	public Logtable() {
	}

	public Logtable(Usertable usertable) {
		this.usertable = usertable;
	}

	public Logtable(Usertable usertable, String log, String suggestion) {
		this.usertable = usertable;
		this.log = log;
		this.suggestion = suggestion;
	}

	public Integer getLogid() {
		return this.logid;
	}

	public void setLogid(Integer logid) {
		this.logid = logid;
	}

	public Usertable getUsertable() {
		return this.usertable;
	}

	public void setUsertable(Usertable usertable) {
		this.usertable = usertable;
	}

	public String getLog() {
		return this.log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public String getSuggestion() {
		return this.suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

}
