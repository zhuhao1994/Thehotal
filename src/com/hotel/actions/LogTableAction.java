package com.hotel.actions;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.hotel.dao.LogtableDAO;
import com.hotel.pojo.Logtable;
import com.hotel.pojo.Usertable;

@ParentPackage(value = "struts-default") // struts注解
@Namespace(value = "/")
@Action(value = "LogAction")
@Results(value = {
		@Result(name = "addFail", location = "/page/checkInRoom/userlogin.jsp"),
		@Result(name = "addSuccess", location = "/page/logTable/addLog.jsp"),
		@Result(name = "mylogs", location = "/page/logTable/openNote.jsp"),
		  })
public class LogTableAction {
	 private LogtableDAO logtableDao;

	public LogtableDAO getLogtableDao() {
		return logtableDao;
	}

	public void setLogtableDao(LogtableDAO logtableDao) {
		this.logtableDao = logtableDao;
	}
	 private static HttpServletRequest request;
	private static HttpServletResponse response;
	//=========ajax:操作=======
	
	public void addLog()
	{
		System.out.println("进来了！");
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		try {
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			String logtime = request.getParameter("logtime");
			String thelog = request.getParameter("thelog");
			System.out.println("日志时间 (logtime): " + logtime + " \n日志内容: "+thelog);
			//获取user
			Usertable ut =  (Usertable) request.getSession().getAttribute("loginUser");
			Logtable log = new Logtable();
			log.setUsertable(ut);
			log.setLog(logtime+" : "+thelog);
			boolean bool = logtableDao.addLog(log);
			if(bool)
			  out.write("保存成功！");
			else
			  out.write("保存失败！");
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("addLog is error!");
		}
	}
	
	//返回个人所有日志
	public String myLog()
	{
		request= ServletActionContext.getRequest();
		try {
			Usertable theuser = (Usertable) request.getSession().getAttribute("loginUser");
			//查询所有日志
			List<Logtable> logs = logtableDao.listAllLog(theuser);
			request.setAttribute("mylogs", logs);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("myLog is error!");
		}
		return "mylogs";
	}

}
