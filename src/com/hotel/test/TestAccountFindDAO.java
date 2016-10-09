package com.hotel.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hotel.dao.PageinationDAO;
import com.hotel.daoImpl.AccountFindDAOImpl;
import com.hotel.daoImpl.PageinationDAOImpl;
import com.hotel.pojo.HcRecord;
import com.hotel.pojo.Hotelaccount;
import com.hotel.pojo.Pager;
import com.hotel.services.AccountFindService;

public class TestAccountFindDAO {
	private static AccountFindDAOImpl a=new AccountFindDAOImpl();
	public static void  find(){
		System.out.println(a.findAccountByCustomer("420625201608031342").size());
	}
	public static void  findByDate(){
		System.out.println(a.findAccountByMonth("2016", "03").size());
	}
	public static void  findAccountByYear(){
		System.out.println("test findAccountByYear(2016)");
		System.out.println(a.findAccountByYear("2016").size());
	}
	public static void testPageByCondition() {
		Pager page = new Pager();
		page.setPage(1);
		page.setRows(10);
		page.setHql("from Hotelaccount where accuntstatus=1 and year(leavetime)=:year and month(leavetime)=:month");
		Map<String, Object> param= new HashMap<String, Object>();
		param.put("year", 2016);
		param.put("month", 2);
	   PageinationDAO pageNext=new PageinationDAOImpl();
		Pager newPage = pageNext.pagerff(page, param);
		List<Hotelaccount> has = (List<Hotelaccount>) newPage.getList();
		for (Hotelaccount ha : has) {
			System.out.println(ha.getAllconsume());
		}
	}
	public static void testcountAll(){
		System.out.println(a.countAll().size());;
	}
	public static void testcountByMOnthOfYear(){
		System.out.println(a.countByMOnthOfYear("2016").size());;
	}
	public static void testcountByDayOfMonth(){
		System.out.println(a.countByDayOfMonth("2016", "02").size());
	}
	public static void testcountByOneDay(){
		System.out.println(a.countByOneDay("2016", "02", "02").size());
	}
	public static void testempPf(){
		System.out.println(a.empPf("王龙").size());
	}
	public static void main(String[] args) throws ParseException {
		//find();
		/*findByDate();*/
		//findAccountByYear();
		//testPageByCondition();
		//testcountAll();
		//testcountByMOnthOfYear();
		//testcountByDayOfMonth();
		//testcountByOneDay();
		
		testempPf();
	}
}
