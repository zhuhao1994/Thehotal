package com.hotel.actions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Component;

import com.hotel.dao.GuestroomDAO;
import com.hotel.dao.HotelAccountDAO;
import com.hotel.pojo.Guestroom;
import com.hotel.pojo.Hotelaccount;
import com.hotel.pojo.Pager;
import com.hotel.services.hotelAccountService;
import com.opensymphony.xwork2.ActionSupport;

@Component(value = "HotelAction2")

@ParentPackage(value = "struts-default") // struts注解
@Namespace(value = "/")
@Action(value = "HotelAction2")
@Results(value = { 
		@Result(name = "checkoutHa", location = "/page/checkOut/checkOutRoom.jsp"),
		@Result(name = "myAccount", location = "/page/checkOut/clearAccount.jsp"),
		@Result(name = "checkoutss", location = "/page/checkOut/clearAccount.jsp"),
		@Result(name = "testjsp", location = "/page/checkOut/test.jsp"),
		})
public class checkOutHaAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private hotelAccountService haService;
    private HotelAccountDAO haDao;
	private Hotelaccount ha;
	private GuestroomDAO guestroomDao;

	
	public HotelAccountDAO getHaDao() {
		return haDao;
	}

	public void setHaDao(HotelAccountDAO haDao) {
		this.haDao = haDao;
	}

	public GuestroomDAO getGuestroomDao() {
		return guestroomDao;
	}

	public void setGuestroomDao(GuestroomDAO guestroomDao) {
		this.guestroomDao = guestroomDao;
	}

	public checkOutHaAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public hotelAccountService getHaService() {
		return haService;
	}

	public void setHaService(hotelAccountService haService) {
		this.haService = haService;
	}

	public Hotelaccount getHa() {
		return ha;
	}

	public void setHa(Hotelaccount ha) {
		this.ha = ha;
	}

	private static HttpServletRequest request;

	// ************Action:方法***********
	//账单分页：帐单状态
	public String checkOutShowHa() {
		request = ServletActionContext.getRequest();
		try {
			// =3中查询方法：全部 未退房 已退房=
			String roomNo = request.getParameter("roomNo");// 获取查询的参数
			System.out.println(roomNo);
			int roomid = 0;
			int nowPage = Integer.parseInt(request.getParameter("nowpage"));// 分页：当前页
			String searchKey = request.getParameter("searchKey");// 查询的方法
			if(searchKey !=  null)
				request.getSession().setAttribute("searchKey", searchKey);// ===待优化
			else
				searchKey = request.getSession().getAttribute("searchKey").toString();// ===待优化
			request.setAttribute("roomNo", roomNo);

			Pager pager = new Pager();
			List<Hotelaccount> has=new ArrayList<Hotelaccount>();
			// 先将该房间的id查出来：根据房间号===待优化
			if (roomNo != "" && roomNo != null) {
				List<Guestroom> rooms = guestroomDao.findRoomByRoomno(Integer.parseInt(roomNo));
				Guestroom room = rooms.get(0);
				roomid = room.getRoomid();
				System.out.println(roomNo + "房间号 id:" + roomid);
			}
			if (searchKey.equals("noCheckOut")) {
				if (roomNo == "" || roomNo == null) {
					String hql = "from  Hotelaccount where accuntstatus=0";
					pager = haService.findHAPager( hql, "", nowPage);
				} else {
					String hql = "from  Hotelaccount where accuntstatus=0 and guestroom=:guestroom";
					pager = haService.findHAPager( hql, roomid + "", nowPage);
					
				}
			} else if (searchKey.equals("allha")) {
				if (roomNo == "" || roomNo == null) {
					String hql = "from  Hotelaccount ";
					pager = haService.findHAPager( hql, "", nowPage);
				} else {
					String hql = "from  Hotelaccount where  guestroom=:guestroom";
					pager = haService.findHAPager( hql, roomid + "", nowPage);
					
				}

			} else if (searchKey.equals("checked")) {
				if (roomNo == "" || roomNo == null) {
					String hql = "from  Hotelaccount where accuntstatus=1";
					pager = haService.findHAPager( hql, "", nowPage);
				} else {
					String hql = "from  Hotelaccount where accuntstatus=1 and guestroom=:guestroom";
					pager = haService.findHAPager( hql, roomid + "", nowPage);
					
				}
			}
			has = (List<Hotelaccount>) pager.getList();
			request.setAttribute("allHotelAccount", has);
			request.setAttribute("haPage", pager);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("checkOutShowHa is error!");
		}
		return "checkoutHa";
	}
	//===我的账单
	public String myAccount()
	{
		request = ServletActionContext.getRequest();
		try {
			String hid = request.getParameter("myHaId");
			if(hid.equals("yfid"))
			{
				System.out.println(hid);
				 hid = (String) request.getSession().getAttribute("myHaIdAg");
				 int myAccountId = Integer.parseInt(hid);
					System.out.println("我的帐单id : "+ myAccountId);
					Hotelaccount ha = haService.findMyAccount(myAccountId);
					request.setAttribute("myAccount", ha);
					request.getSession().setAttribute("myHaIdAg", hid);
					request.getSession().setAttribute("myHaRoomId", ha.getGuestroom().getRoomid());//存下商品的房间号
				
			}
			else if(hid != null && hid != "" && hid != "yfid")
			{
				int myAccountId = Integer.parseInt(hid);
				System.out.println("我的帐单id : "+ myAccountId);
				Hotelaccount ha = haService.findMyAccount(myAccountId);
				request.setAttribute("myAccount", ha);
				request.getSession().setAttribute("myHaIdAg", hid);
				request.getSession().setAttribute("myHaRoomId", ha.getGuestroom().getRoomid());//存下商品的房间号
			}
			//=======商品总价========
			String sumPrice = request.getParameter("sumPrice");
			String goodstatus = request.getParameter("goodstatus");
			request.setAttribute("goodstatus", goodstatus);
			if(sumPrice != null)
			{
				request.setAttribute("sumPrice", sumPrice);
			   System.out.println("sumPrice : "  +sumPrice);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("myAccount is error!");
		}
		return "myAccount";
	}
	public String testjsp()
	{
		return "testjsp";
	}
	
	//=========退房：获取物品消费===========
	public String checkOutMyaccount()
	{
		request=ServletActionContext.getRequest();
		try {
			//===获取房间号
			String roomNo = request.getParameter("roomno");
			int rn = Integer.parseInt(roomNo);
			
			//获取商品消费
			String goodsConsume = request.getParameter("goodscms");
			float gc = Float.parseFloat(goodsConsume);
			
			//获取备注
			String remarkss = request.getParameter("remark");
			System.out.println("房间号 ： "+roomNo + "  商品消费：" +goodsConsume+"  备注："+remarkss);
			Guestroom gs = new Guestroom();
			gs.setRoomno(rn);
			Hotelaccount ha = new Hotelaccount();
			ha.setGuestroom(gs);
			ha.setRemarks(remarkss);
			haDao.updatehotelAccount(ha, gc);
			request.setAttribute("createHAsuccess", "消费添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("checkOutMyaccount is error!");
		}
		return "checkoutss";
	}
}
