package com.hotel.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hotel.dao.CustomerDAO;
import com.hotel.dao.PageinationDAO;
import com.hotel.daoImpl.CustomerDAOImpl;
import com.hotel.daoImpl.PageinationDAOImpl;
import com.hotel.pojo.Customer;
import com.hotel.pojo.Guestroom;
import com.hotel.pojo.Hotelaccount;
import com.hotel.pojo.Pager;

public class testPageNext {
	private static PageinationDAO pageDao;

	public static void testPage() {
		Pager page = new Pager();
		page.setPage(2);
		page.setRows(10);
		page.setHql("from  Hotelaccount");
		Pager newPage = pageDao.pagerff(page, null);
		List<Hotelaccount> has = (List<Hotelaccount>) newPage.getList();
		for (Hotelaccount ha : has) {
			System.out.println(ha.getAllconsume());
		}
	}
	//按条件查询=====
	public static void testPageByCondition() {
		Pager page = new Pager();
		page.setPage(1);
		page.setRows(10);
		page.setHql("from  Hotelaccount where accuntstatus=0");
//		Map<String, Object> param= new HashMap<String, Object>();
//		Guestroom gst = new Guestroom();
//		gst.setRoomid(1);
//		param.put("guestroom", gst);
		Pager newPage = pageDao.pagerff(page, null);
		List<Hotelaccount> has = (List<Hotelaccount>) newPage.getList();
		for (Hotelaccount ha : has) {
			System.out.println(ha.getCustomer().getCusname());
		}
	}
	//====分页查询：客户=== 1.第几页：2.显示几行  3.查询参数
	public static void testCustmer() {
		Pager page = new Pager();
		page.setPage(1);
		page.setRows(10);
		page.setHql("from  Customer where cusname=:cusname");
		Map<String, Object> param= new HashMap<String, Object>();
		param.put("cusname", "朱昊");
		Pager newPage = pageDao.pagerff(page, param);
		List<Customer> has = (List<Customer>) newPage.getList();
		for (Customer ha : has) {
			System.out.println(ha.getCusname());
		}
	}
	public static void testCustmer1() {
		Pager page = new Pager();
		page.setPage(1);
		page.setRows(10);
		page.setHql("from  Customer where status=0");
		Pager newPage = pageDao.pagerff(page, null);
		List<Customer> has = (List<Customer>) newPage.getList();
		for (Customer ha : has) {
			System.out.println(ha.getStatus());
		}
	}
 
	public static void main(String[] args) {
		pageDao = new PageinationDAOImpl();
//		testPage();
		testPageByCondition();
	}
}
