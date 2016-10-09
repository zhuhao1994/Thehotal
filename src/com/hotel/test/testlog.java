package com.hotel.test;


import com.hotel.dao.LogtableDAO;
import com.hotel.daoImpl.LogtableDAOImpl;
import com.hotel.pojo.Customer;
import com.hotel.pojo.Logtable;
import com.hotel.pojo.Usertable;

public class testlog {
  private static LogtableDAO logDao;//在成员方法中不能引用实力变量：注意
	public static void testaddLog()
	{
		Usertable ut = new Usertable();
		ut.setUserid(1);
		Logtable lt = new Logtable();
		lt.setUsertable(ut);
		lt.setLog("谢谢吴曦！");
		logDao.addLog(lt);
	}
	public static void main(String[] args) {
		logDao = new LogtableDAOImpl();
		testaddLog();
	}
}
