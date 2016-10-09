package com.hotel.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.validator.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hotel.dao.GuestroomDAO;
import com.hotel.pojo.Guestroom;
import com.hotel.pojo.Hotelaccount;
import com.hotel.pojo.Pager;
import com.hotel.services.GuestroomService;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
@Component(value="guestroomAction")

@ParentPackage(value="struts-default")
@Namespace(value="/")
@Action(value="GuestroomAction")
@Results(
		{
			@Result(name="addGuestroomSucc",location="/page/Guestroom/addRoom.jsp"),
			@Result(name="listGuestroomSucc",location="/page/Guestroom/listRoom.jsp"),
			@Result(name="listGuestroomPage",location="/page/Guestroom/listRoom.jsp"),
			@Result(name="updateGuestroomSucc",location="/page/Guestroom/editRoom.jsp"),
			@Result(name="findSuccess",location="/page/Guestroom/listRoom.jsp"),
			@Result(name="findFails",location="/page/Guestroom/listRoom.jsp"),
			@Result(name="deleteSuccess",location="/page/Guestroom/listRoom.jsp"),
			@Result(name="updateSuccess",location="/page/Guestroom/updatesucc.jsp"),
		}
		)
public class GuestroomAction extends ActionSupport implements SessionAware{
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	@Autowired
	private GuestroomService guestroomService;
	private Guestroom guestroom;
	private int guestroomno;
	private String guestroomtype;
	private float guestroomprice;
	private String guestroomtel;
	private Float guestroomdiscount;
	
	public GuestroomService getGuestroomService() {
		return guestroomService;
	}

	public void setGuestroomService(GuestroomService guestroomService) {
		this.guestroomService = guestroomService;
	}

	public Guestroom getGuestroom() {
		return guestroom;
	}

	public void setGuestroom(Guestroom guestroom) {
		this.guestroom = guestroom;
	}
	
	public int getGuestroomno() {
		return guestroomno;
	}

	public void setGuestroomno(int guestroomno) {
		this.guestroomno = guestroomno;
	}
	
	public String getGuestroomtype() {
		return guestroomtype;
	}

	public void setGuestroomtype(String guestroomtype) {
		this.guestroomtype = guestroomtype;
	}

	public float getGuestroomprice() {
		return guestroomprice;
	}

	public void setGuestroomprice(float guestroomprice) {
		this.guestroomprice = guestroomprice;
	}

	public String getGuestroomtel() {
		return guestroomtel;
	}

	public void setGuestroomtel(String guestroomtel) {
		this.guestroomtel = guestroomtel;
	}

	public Float getGuestroomdiscount() {
		return guestroomdiscount;
	}

	public void setGuestroomdiscount(Float guestroomdiscount) {
		this.guestroomdiscount = guestroomdiscount;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	private static HttpServletRequest request;

	//添加客房
	public String addGuestroom(){
		if(guestroomService.addGuestroom(guestroom)){
			return "addGuestroomSucc";
		}else{
			return "addGuestroomSucc";
		}
	}
	//房间号的查重
	public void findRoomno(){
		
		try {
			HttpServletResponse response=ServletActionContext.getResponse();
			HttpServletRequest request=ServletActionContext.getRequest();
			response.setCharacterEncoding("utf-8");
			request.getParameter("guestroomno");
			List<Guestroom> guestroom = guestroomService.findRoomByRoomno(guestroomno);
//			response.setContentType("text/json;charset=utf-8");
			PrintWriter out=response.getWriter();
//			JSONObject object=new JSONObject();
			if(guestroom.isEmpty()){
				out.print("");
			}else{
				out.print("客房已存在!");
			}
			
			out.flush();
			out.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//查找（根据客房id）
	public String findTheRoomByRoomid(){
		request = ServletActionContext.getRequest();
		String id =request.getParameter("roomid");
		
		Guestroom guestroom = new Guestroom();
		guestroom.setRoomid(Integer.valueOf(id));
		Guestroom guestroom2=guestroomService.findTheRoomByRoomid(guestroom);
		request.setAttribute("editRoom", guestroom2);
		return "updateGuestroomSucc";
	}
	
	//按条件查询（根据房间号，客房类型，客房状态）
	public String findRoomByTerm(){
		HttpServletRequest request=ServletActionContext.getRequest();
		List<Guestroom> guestrooms = new ArrayList<Guestroom>();
		try {
			
			String term=request.getParameter("se");
			// 将查询的方法
			String keywords = request.getParameter("keywords").trim();// 查询的参数
			request.getSession().setAttribute("term", term);// 将查询的方法存起来
			if(keywords=="")//如果为空查询所有内容
			{
				System.out.println("查询内容为空！");
				showPageHa();
				return "findSuccess";
			}
			request.getSession().setAttribute("term", term);// 将查询的方法存起来
			keywords = URLDecoder.decode(keywords, "utf-8");
			request.setAttribute("keywords", keywords);// 将查询的参数存起来

			int page = Integer.parseInt(request.getParameter("nowpage"));
			System.out.println("获取到的page " + page);

			Pager pager = new Pager();
			if(term.equals("roomno")){
				String hql = "from  Guestroom where roomno=:roomno";
				pager = guestroomService.findPager(1, hql, keywords, page);
			}else if(term.equals("roomtype")){
				String hql = "from  Guestroom where roomtype=:roomtype";
				pager = guestroomService.findPager(2, hql, keywords, page);
			}else{
				String status= keywords;
				if(status.equals("空房")){
					String hql = "from  Guestroom where roomstatus=0";
					pager = guestroomService.findPager(3, hql, keywords, page);
				}else if(status.equals("入住房")){
					String hql = "from  Guestroom where roomstatus=1";
					pager = guestroomService.findPager(4, hql, keywords, page);
				}
			}
			
			guestrooms = (List<Guestroom>) pager.getList();
			if (guestrooms != null) {
				//将结果分页后显示
				request.setAttribute("haPage", pager);
				request.setAttribute("listRooms", guestrooms);
				return "findSuccess";
			} else {
				return "findFails";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(guestrooms!=null){
			request.setAttribute("listRooms", guestrooms);
			return "findSuccess";
		}else{
			return "findFails";
		}
	}
	//修改客房信息
	public String updateGuestroom(){
		HttpServletRequest request=ServletActionContext.getRequest();
		System.out.println(guestroom.getRoomid());
		guestroomService.updateGuestroom(guestroom);
		request.setAttribute("update", guestroom);
		request.setAttribute("updateCusLog", "更新成功！");
		return "updateSuccess";
	}
	/*//删除客房信息
	public String deleteGuestroom(){
		try {
			System.out.println("1111111111111111");
			String room = request.getParameter("roomno").toString();
			System.out.println(room);
			int roomno=Integer.parseInt(room);
			guestroom.setRoomno(roomno);
			System.out.println("进来了");
			guestroomService.deleteGuestroom(guestroom);
			return "deleteSuccess";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "deleteFails";
	}*/
	//查看所有（分页）
	public String showPageHa()
	{
		request = ServletActionContext.getRequest();
		int nowPage = Integer.parseInt(request.getParameter("nowpage"));
		Pager pager= guestroomService.showPageHa(nowPage);
		List<Guestroom> listRooms = (List<Guestroom>) pager.getList();
		if (listRooms.size() > 0) {
			//根据不同的内容做查询
			request.setAttribute("listRooms", listRooms);
			request.setAttribute("haPage", pager);
			return "listGuestroomPage";
		} else
			return "listGuestroomPage";
	}
	
}
