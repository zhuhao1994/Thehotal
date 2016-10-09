package com.hotel.actions;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.http.server.ServerHttpRequest;

import com.hotel.dao.GuestroomDAO;
import com.hotel.dao.HotelAccountDAO;
import com.hotel.pojo.Customer;
import com.hotel.pojo.Guestroom;
import com.hotel.pojo.Hotelaccount;
import com.hotel.pojo.Usertable;

@ParentPackage(value = "struts-default")
@Namespace(value = "/")
@Action(value = "roomStatusAction")
@Results(
		value={
				@Result(name = "showRoomstatus",location="/page/checkInRoom/theRoomStatus.jsp"),
				@Result(name = "addCustpmer",location="/page/customer/addCustomer.jsp"),
				@Result(name = "addHotelAccount",location="/page/checkInRoom/addHotelAccount.jsp"),
				@Result(name = "createHASuccess",location="/page/checkInRoom/addHotelAccount.jsp"),
				@Result(name = "createHAFail",location="/page/checkInRoom/addHotelAccount.jsp"),
				@Result(name = "defalut",location="/index.jsp")
		}
		)
public class theRoomstatusAction {
	GuestroomDAO guestroomDao;
	HotelAccountDAO haDao;

	public theRoomstatusAction(GuestroomDAO guestroomDao) {
		super();
		this.guestroomDao = guestroomDao;
	}

	public GuestroomDAO getGuestroomDao() {
		return guestroomDao;
	}

	public void setGuestroomDao(GuestroomDAO guestroomDao) {
		this.guestroomDao = guestroomDao;
	}
	
	public HotelAccountDAO getHaDao() {
		return haDao;
	}

	public void setHaDao(HotelAccountDAO haDao) {
		this.haDao = haDao;
	}

	private static HttpServletRequest request;
	private static HttpServletResponse response;
	//*****action方法*
	public String roomStatus()
	{
		request = ServletActionContext.getRequest();
		String thePath = null;
		try {
			  thePath = request.getParameter("thePath");
		} catch (Exception e) {
			System.out.println("thePath is null");
		}
		if(thePath != null)
			request.getSession().setAttribute("thePath", thePath);
		try {
			List<Guestroom> rooms= guestroomDao.listAllRooms();
			request.setAttribute("theallrooms", rooms);
		} catch (Exception e) {
			System.out.println("roomStatus is error");
		}
		
		return "showRoomstatus";
	}
	//==添加客户
	public String directorAddCs()
	{
		request = ServletActionContext.getRequest();
		String thePath = null;
		try {
			  thePath = request.getParameter("thePath");
		} catch (Exception e) {
			System.out.println("thePath is null");
		}
		if(thePath != null)
			request.getSession().setAttribute("thePath", thePath);
		return "addCustpmer";
	}
	//==ajax选择客户
	public void  choseCustmer()
	{
		request = ServletActionContext.getRequest();
		
		response = ServletActionContext.getResponse();
		try {
			response.setContentType("text/xml;charset=utf-8");  
			int custmerid = Integer.parseInt(request.getParameter("cusId"));
			String custmerName = request.getParameter("cusName");
			System.out.println("custmerid : " + custmerid);
			System.out.println("custmerName : " + custmerName);
			request.getSession().setAttribute("chosedCusId", custmerid);//将选择的用户（id）存下来
			request.getSession().setAttribute("chosedCusName", custmerName);//将选择的用户（id）存下来
			PrintWriter pw = response.getWriter();
			pw.write(custmerName);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			System.out.println("choseCustmer is error!");
		}
	}
	//==ajax选择客房
	public void  choseGustRoom()
	{
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		try {
			response.setContentType("text/xml;charset=utf-8");  
			int roomid = Integer.parseInt(request.getParameter("roomId"));
			String roomno = request.getParameter("roomNo");
			System.out.println("roomid : " + roomid);
			System.out.println("roomno : " + roomno);
			request.getSession().setAttribute("chosedRoomId", roomid);//将选择的用户（id）存下来
			request.getSession().setAttribute("chosedRoomNo", roomno);//将选择的用户（id）存下来
			PrintWriter pw = response.getWriter();
			pw.write(roomno);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			System.out.println("choseCustmer is error!");
		}
	}
	//====生成入住记录界面
	public String addHotelAccount()
	{
		return "addHotelAccount";
	}
	//**生成入住记录*******
	public String createHA()
	{
		request = ServletActionContext.getRequest();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy/MM/dd");
		try {
			Usertable user = (Usertable) request.getSession().getAttribute("loginUser");
			int chosedCusId = Integer.parseInt(request.getSession().getAttribute("chosedCusId").toString());
			int chosedRoomId = Integer.parseInt(request.getSession().getAttribute("chosedRoomId").toString());
			Customer cm = new Customer();
			cm.setCusid(chosedCusId);
			Guestroom gs = new Guestroom();
			gs.setRoomid(chosedRoomId);
			String deposit = request.getParameter("desposit");
			String comeinTime = request.getParameter("comeinTime");
			String leaveTime = request.getParameter("outTime");
			String remark = request.getParameter("remark");
			//=====前台 客房 客户 押金 入住时间  离开时间 住房消费 总消费 备注 状态
			Hotelaccount ha = new Hotelaccount(cm, gs, user, Integer.parseInt(deposit),sim.parse(comeinTime),sim.parse(leaveTime), 0, 0,remark,0);
			int rs = haDao.addhotelAccount(ha);
			if(rs > 0)
			{
				request.setAttribute("createHA", "恭喜,入住成功！");	
				return "createHASuccess";
			}
			else
			{
				request.setAttribute("createHA", "sorry,入住失败！！");
				return "createHAFail";
			}
			
		} catch (Exception e) {
			System.out.println("createHA is error");
			request.setAttribute("createHA", "sorry,入住失败！！");
			return "createHAFail";
		}
	}
}
