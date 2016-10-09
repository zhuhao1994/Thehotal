package com.hotel.pojo;

import java.util.Date;

public class HcRecord {
	private Date date;//统计的日期
	private int item;//记录数量
	private int cusNum;//客户数量
	private float consum;//总的物品消费
	private float allconsum;//总的综合消费
	
	public HcRecord() {
		super();
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getItem() {
		return item;
	}
	public void setItem(int item) {
		this.item = item;
	}
	public int getCusNum() {
		return cusNum;
	}
	public void setCusNum(int cusNum) {
		this.cusNum = cusNum;
	}
	public float getConsum() {
		return consum;
	}
	public void setConsum(float consum) {
		this.consum = consum;
	}
	public float getAllconsum() {
		return allconsum;
	}
	public void setAllconsum(float allconsum) {
		this.allconsum = allconsum;
	}
	@Override
	public String toString() {
		return "HcRecord [date=" + date + ", item=" + item + ", cusNum=" + cusNum + ", consum=" + consum
				+ ", allconsum=" + allconsum + "]";
	}
	public HcRecord(Date date, int item, int cusNum, float consum, float allconsum) {
		super();
		this.date = date;
		this.item = item;
		this.cusNum = cusNum;
		this.consum = consum;
		this.allconsum = allconsum;
	}
	
	
}
