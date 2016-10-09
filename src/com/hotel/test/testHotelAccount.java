package com.hotel.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.hotel.dao.HotelAccountDAO;
import com.hotel.dao.UserDAO;
import com.hotel.daoImpl.HotelAccountDAOImpl;
import com.hotel.daoImpl.UserDAOImpl;
import com.hotel.pojo.Customer;
import com.hotel.pojo.Guestroom;
import com.hotel.pojo.Hotelaccount;
import com.hotel.pojo.Usertable;

public class testHotelAccount {
	private static HotelAccountDAO haDao;

	// 入住操作
	public static void addHA() {
		Customer cm = new Customer();
		cm.setCusid(1);
		Guestroom gs = new Guestroom();
		gs.setRoomid(1);
		Usertable ut = new Usertable();
		ut.setUserid(1);
		Hotelaccount ha = new Hotelaccount(cm, gs, ut, 200, new Date(System.currentTimeMillis()), 0, 0, 0);
		System.out.println(haDao.addhotelAccount(ha));

	}

	public static void findAll() {
		List<Hotelaccount> has = new ArrayList<Hotelaccount>();
		has = haDao.findAllHA();
		for (Hotelaccount ha : has) {
			System.out.println(ha.getAllconsume());
		}
	}

	// 查询出未退房的账单：更据房号查询
	public static void findtheCheckOut(int xx) {
		Guestroom gs = new Guestroom();
		gs.setRoomid(haDao.fingHsID(xx).getRoomid());
		Hotelaccount ha1 = new Hotelaccount();
		ha1.setGuestroom(gs);
		Hotelaccount ha = haDao.findtheCheckOut(ha1);
		System.out.println("押金：" + ha.getDeposit() + "  入住时间" + ha.getCometime());
	}

	// *****查询房间id:房间号********
	public static void findHSId() {
		System.out.println(haDao.fingHsID(1).getRoomid());
	}

	// *****查询房间id:房间id********
	public static void findHSById() {
		System.out.println(haDao.fingHsID(101).getRoomprice());
	}

	// ***退房：更新客户的入住状态*
	public static void updateCustmerStatus() {
		Customer cm = new Customer();
		cm.setCusid(1);
		Guestroom gs = new Guestroom();
		gs.setRoomid(1);
		Usertable ut = new Usertable();
		ut.setUserid(1);
		Hotelaccount ha = new Hotelaccount(cm, gs, ut, 200, new Date(System.currentTimeMillis()), 0, 0, 0);
		haDao.updateCustemrStatus(ha);
	}

	// 测试：更改客户和客房的状态
	public static void updateGustRoomStatus() {
		Customer cm = new Customer();
		cm.setCusid(1);
		Guestroom gs = new Guestroom();
		gs.setRoomid(1);
		Usertable ut = new Usertable();
		ut.setUserid(1);
		Hotelaccount ha = new Hotelaccount(cm, gs, ut, 200, new Date(System.currentTimeMillis()), 0, 0, 0);
		haDao.updateGustRoomStatus(ha);
	}

	// --测试日期差
	// ---计算日期差
	public static int daysBetween(String smdate, String bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	// 退房操作:只需要房号
	public static void updatehotelAccount(int xx) {
		Guestroom gs = new Guestroom();
		gs.setRoomno(xx);
		Hotelaccount ha = new Hotelaccount();
		ha.setGuestroom(gs);
		haDao.updatehotelAccount(ha, 50);
	}

	//测试登录
	public static void testLogin()
	{
		UserDAO userDao = new UserDAOImpl();
		Usertable users = new Usertable();
		users.setUsername("王龙1");
		users.setPassword("123456");
		Usertable users1 = userDao.login(users);
		System.out.println(users1);
	}
	public static void main(String[] args) throws ParseException {
		haDao = new HotelAccountDAOImpl();
		addHA();// true
		// findAll();// true
		// findHSId();
		// findtheCheckOut(101);
		// updateCustmerStatus();
		// updateGustRoomStatus();
		// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd
		// HH:mm:ss");//设置日期格式
		// String time = df.format(new Date()).toString();
		// System.out.println(daysBetween("2012-09-08 10:10:10",time));
		// findHSById();
		// updatehotelAccount(101);
		testLogin();
	}
}
