package com.hotel.actions;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.hotel.dao.UserDAO;
import com.hotel.pojo.Usertable;
@ParentPackage(value = "struts-default") // struts注解
@Namespace(value = "/")
@Action(value = "loginAction")
@Results(value = {
		@Result(name = "loginFail", location = "/page/checkInRoom/userlogin.jsp"),
		@Result(name = "loginSuccess", location = "/index.jsp")
		  })
public class loginAction {
	private UserDAO userDao;
	
	public UserDAO getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
	
	private static HttpServletRequest request;
	//***************************
	public String loginCheck()
	{
		request = ServletActionContext.getRequest();
		Usertable ut = new  Usertable();
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			Usertable user = new Usertable();
			user.setUsername(username);
			user.setPassword(password);
			ut = userDao.login(user);
		} catch (Exception e) {
			System.out.println("loginCheck is error!");
		}
		if(ut != null)
		{
			request.getSession().setAttribute("loginUser", ut);//存值：登录的用户
			return "loginSuccess";
		}else{
			request.setAttribute("failLog", "账户名或者密码错误！");
			return "loginFail";
		}
		
	}
	public String loginOut()
	{
		request = ServletActionContext.getRequest();
		request.getSession().removeAttribute("loginUser");
		return "loginFail";
	}

}
