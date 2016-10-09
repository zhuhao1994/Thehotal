package com.hotel.pojo;

public class Manager implements java.io.Serializable {

	private Integer managerid;
	private String mananame;
	private String manapassword;

	public Manager() {
	}

	public Manager(String mananame, String manapassword) {
		this.mananame = mananame;
		this.manapassword = manapassword;
	}

	public Integer getManagerid() {
		return this.managerid;
	}

	public void setManagerid(Integer managerid) {
		this.managerid = managerid;
	}

	public String getMananame() {
		return this.mananame;
	}

	public void setMananame(String mananame) {
		this.mananame = mananame;
	}

	public String getManapassword() {
		return this.manapassword;
	}

	public void setManapassword(String manapassword) {
		this.manapassword = manapassword;
	}

}
