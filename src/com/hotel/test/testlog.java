package com.hotel.test;


import com.hotel.dao.LogtableDAO;
import com.hotel.daoImpl.LogtableDAOImpl;
import com.hotel.pojo.Customer;
import com.hotel.pojo.Logtable;
import com.hotel.pojo.Usertable;

public class testlog {
  private static LogtableDAO logDao;//�ڳ�Ա�����в�������ʵ��������ע��
	public static void testaddLog()
	{
		Usertable ut = new Usertable();
		ut.setUserid(1);
		Logtable lt = new Logtable();
		lt.setUsertable(ut);
		lt.setLog("лл���أ�");
		logDao.addLog(lt);
	}
	public static void main(String[] args) {
		logDao = new LogtableDAOImpl();
		testaddLog();
	}
}
