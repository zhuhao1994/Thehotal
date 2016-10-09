package com.hotel.actions;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.Password;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Component;

import com.hotel.pojo.Hotelaccount;
import com.hotel.pojo.Pager;
import com.hotel.pojo.Usertable;
import com.hotel.services.UserService;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component(value = "userAction")
@ParentPackage(value = "struts-default")
@Namespace(value = "/")
@Action(value = "UserAction")
@Results({ 
		@Result(name = "showUserSeccess", location = "/page/user/listadmin.jsp"),
		@Result(name = "showUserFails", location = "/index.jsp"), 
		@Result(name = "adduserSuccess", location = "/page/user/listadmin.jsp"),
		@Result(name = "findUserByIdSuccess", location = "/page/user/updateadmin.jsp"),
		@Result(name = "deleteuserSuccess", location = "/page/user/listadmin.jsp"),	
		@Result(name = "findUserByIdFails", location = "/page/user/updateuser.jsp"),	
		@Result(name = "updateUserpassword", location = "/page/user/listuser.jsp"),
		@Result(name = "updateSuccess", location = "/page/user/updateadmin1.jsp"),
		@Result(name = "findUserByUsernameSuccess", location = "/page/user/updateuser.jsp"),
		@Result(name = "findSuccess", location = "/page/user/listuser.jsp"),
		@Result(name = "findFails", location = "/page/user/listuser.jsp")
		
})
		
public class UserAction extends ActionSupport implements SessionAware {
	private UserService userService;
	private Map<String, Object> session;
	private Usertable usertable;
	private String userbirth;
	private static HttpServletRequest request;
	private static HttpServletResponse response;
	private int userid;
	private String username;
	private String password;
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public Usertable getUsertable() {
		return usertable;
	}

	public void setUsertable(Usertable usertable) {
		this.usertable = usertable;
	}

	public String getUserbirth() {
		return userbirth;
	}

	public void setUserbirth(String userbirth) {
		this.userbirth = userbirth;
	}

	

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = session;
	}
	//显示所有
	public String showUser() {
		List<Usertable> showUsers = userService.listAllUser();
		if (showUsers != null) {
			request = ServletActionContext.getRequest();
			request.setAttribute("showUsers", showUsers);
			return "showUserSeccess";
		}else{
			return "showUserFails";
		}
	}
	//添加
	public String addUser(){
		SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = s.parse(userbirth);
			System.out.println(userbirth);
			System.out.println(date);
			usertable.setUserbirth(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		boolean adduser=userService.addUser(usertable);
		if(adduser){
			System.out.println("增加进来了！");
			return showUser();
		}
		else
			return "showUserFails";
	}
	//添加时通过员工用户名判重
	public void findTheUserByUsername(){
		System.out.println("员工用户名判重");
		try {
		response=ServletActionContext.getResponse();
		request=ServletActionContext.getRequest();
		response.setCharacterEncoding("utf-8");
		request.getParameter("username");
		List<Usertable> usertable=userService.findTheUserByUsername(username);
			PrintWriter out=response.getWriter();
		if(usertable.isEmpty()){
				out.print("");
			}else{
				out.print("用户名已存在!");
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	//通过不同的条件查找员工
	public String findTheUserById(){
		request=ServletActionContext.getRequest();
		List<Usertable> usertable=new ArrayList<Usertable>();
		try {
		String id = request.getParameter("userta");//查询方法
		String type=request.getParameter("usert").trim();//查询参数
		request.getSession().setAttribute("id", id);//存储
		if(type==""){   //查询内容为空
			System.out.println("查询内容为空");
			showPage();
			return "showUserSeccess";			
		}
		request.getSession().setAttribute("id", id);//将查询的方法存起来
		type=URLDecoder.decode(type,"utf-8");
		request.setAttribute("type", type);//将查询的参数存起来
		int page=Integer.parseInt(request.getParameter("nowpage"));
		System.out.println("获取到的page " + page);
		Pager pager=new Pager();
		if(id.equals("userid")){
			String hql="from Usertable where userid=:userid";
			pager=userService.findPager(1, hql, type, page);			
			}else if(id.equals("username")){
				String hql="from Usertable where username=:username";
				pager=userService.findPager(2, hql, type, page);
			}else{
				String hql="from Usertable where position=:position";
				pager=userService.findPager(3, hql, type, page);
			}
		usertable=(List<Usertable>) pager.getList();
		if(usertable!=null){
			//将结果分页后显示
			request.setAttribute("haPage", pager);
			request.setAttribute("showUsers", usertable);
			return "showUserSeccess";
		} else {
			return "showUserFails";
		}
	} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
	}
	if(usertable!=null){
		request.setAttribute("showUsers", usertable);
			return "showUserSeccess";
		}else{
			return "showUserFails";
		}		
	}
	//通过员工编号
	public String findUserById(){
		request = ServletActionContext.getRequest();
		String id=request.getParameter("userid");
		System.out.println("通过id查找");
		Usertable usertable=new Usertable();
		usertable.setUserid(Integer.parseInt(id));
		//usertable.setUserid(Integer.valueOf(id));
		Usertable user=userService.findUserById(usertable);
		request.setAttribute("findUserById", user);
		System.out.println(user.getUserid());
		return "findUserByIdSuccess";	
	}
	
	//修改
	public String updateUser(){
		request= ServletActionContext.getRequest();
		System.out.println("修改员工方法");	
		userService.updateUser(usertable);
		request.setAttribute("upt", usertable);
		System.out.println("修改员工方法");
		return "updateSuccess";
				
	} 
	//删除
	public String deleteUser(){
		System.out.println("删除方法");
		request = ServletActionContext.getRequest();
		String uid=request.getParameter("id");
		int usid=Integer.parseInt(uid);
		System.out.println(usid);
		Usertable usert=new Usertable();
		usert.setUserid(usid);
		userService.deleteUser(usert);
		return showUser();
	}
	//分页显示
	public String showPage()
	{
		request = ServletActionContext.getRequest();
		int nowPage = Integer.parseInt(request.getParameter("nowpage"));
		Pager pager= userService.showPage(nowPage);
		List<Usertable> has = (List<Usertable>) pager.getList();
		if (has.size() > 0) {
			request.setAttribute("showUsers", has);
			request.setAttribute("haPage", pager);
			return "showUserSeccess";
		} else
			return "showUserFails";
	}
	
	public String findUserByUsername(){
		request=ServletActionContext.getRequest();
		String username=request.getParameter("username");
		Usertable usertable=new Usertable();
		usertable.setUsername(username);
		Usertable ut=userService.findUserByUsername(usertable);
		request.setAttribute("findUserByUsername", ut);
		return "findUserByUsernameSuccess";
	}
	
	public String findByUsername(){
		
	HttpServletRequest request=ServletActionContext.getRequest();
	usertable=(Usertable) request.getAttribute("loginUser");
	Usertable user=userService.findByUsername(usertable.getUsername());
	if(user!=null){
	request.setAttribute("user", user);
		return "findSuccess";
	}else{
		return "findFails";
	}
	
}
	public void updatePassword() {
		HttpServletResponse response=ServletActionContext.getResponse();
		HttpServletRequest request=ServletActionContext.getRequest();
		response.setCharacterEncoding("utf-8");
		System.out.println(password);
		try {
			PrintWriter out=response.getWriter();
			if(userService.updateUserpassword(userid, password)){
				out.write("修改成功");
				Usertable user=(Usertable) request.getSession().getAttribute("loginUser");
				user.setPassword(password);
				request.getSession().removeAttribute("loginUser");
				request.getSession().setAttribute("loginUser", user);
			}else{
				out.write("修改失败");
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public void findUser(){
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter out=response.getWriter();
			Usertable usertable=userService.findByUsername(username);
			if(usertable!=null){
				out.write(usertable.getPassword());
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
