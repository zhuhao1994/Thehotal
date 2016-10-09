package com.hotel.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.accessibility.AccessibleRelation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Component;

import com.hotel.pojo.Usertable;
import com.hotel.services.UserService;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

@Component(value = "registerAction")
@ParentPackage(value = "struts-default")
@Namespace(value = "/")
@Action(value = "RegisterAction")
@Results({
	@Result(name = "addSuccess", location = "/page/user/listadmin.jsp"),
})
public class RegisterAction extends ActionSupport implements SessionAware{
	private Map<String, Object> session;
	private UserService userService;
	private Usertable user;
	private String username;
	
	
	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public UserService getUserService() {
		return userService;
	}



	public void setUserService(UserService userService) {
		this.userService = userService;
	}



	public Usertable getUser() {
		return user;
	}



	public void setUser(Usertable user) {
		this.user = user;
	}



	@Override
	public void setSession(Map<String, Object> arg0) {
		
	}

	
	public String rigisterUser() throws ParseException{
		HttpServletRequest request=ServletActionContext.getRequest();
		
		String username=request.getParameter("username");
		String password=request.getParameter("repassword");
		String realname=request.getParameter("realname");
		SimpleDateFormat sim = new SimpleDateFormat("yyyy/MM/dd");
		String userbirth=request.getParameter("userbirth");
		String usersex=request.getParameter("usersex");
		String position="前台服务员";
		Double salary=Double.parseDouble(request.getParameter("salary"));
		Usertable usertable=new Usertable(username,password,realname,sim.parse(userbirth),usersex,position,salary);
		if(userService.addUser(usertable)){
			request.setAttribute("type", username);
			request.getSession().setAttribute("id", "username");
			request.setAttribute("autoSearch", "autoSearch");
			return "addSuccess";
		}else{
			return "addFails";
		}
		
	}
	
	public void findByUsername(){
		Usertable user=userService.findByUsername(username);
		try {
			HttpServletResponse response=ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			
			PrintWriter out=response.getWriter();
			if(user!=null){
				out.write("用户名存在！");
			}else{
				out.write("");
			}
			out.flush();
			out.close();
			
		} catch (IOException e) {
			System.out.println("findUserByUsername is error!");
			e.printStackTrace();
		}
		
	}
}
