package com.hotel.pojo;


import java.util.HashSet;
import java.util.Set;

/**
 * Vip generated by hbm2java
 */
public class Vip implements java.io.Serializable {

	private Integer vid;
	private String vrank;
	private Float vdiscount;
	private Set customers = new HashSet(0);

	public Vip() {
	}

	public Vip(String vrank, Float vdiscount, Set customers) {
		this.vrank = vrank;
		this.vdiscount = vdiscount;
		this.customers = customers;
	}

	public Integer getVid() {
		return this.vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}

	public String getVrank() {
		return this.vrank;
	}

	public void setVrank(String vrank) {
		this.vrank = vrank;
	}

	public Float getVdiscount() {
		return this.vdiscount;
	}

	public void setVdiscount(Float vdiscount) {
		this.vdiscount = vdiscount;
	}

	public Set getCustomers() {
		return this.customers;
	}

	public void setCustomers(Set customers) {
		this.customers = customers;
	}

}