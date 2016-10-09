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

@ParentPackage(value = "struts-default") // strutsע��
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

	// ************Action:����***********
	//�˵���ҳ���ʵ�״̬
	public String checkOutShowHa() {
		request = ServletActionContext.getRequest();
		try {
			// =3�в�ѯ������ȫ�� δ�˷� ���˷�=
			String roomNo = request.getParameter("roomNo");// ��ȡ��ѯ�Ĳ���
			System.out.println(roomNo);
			int roomid = 0;
			int nowPage = Integer.parseInt(request.getParameter("nowpage"));// ��ҳ����ǰҳ
			String searchKey = request.getParameter("searchKey");// ��ѯ�ķ���
			if(searchKey !=  null)
				request.getSession().setAttribute("searchKey", searchKey);// ===���Ż�
			else
				searchKey = request.getSession().getAttribute("searchKey").toString();// ===���Ż�
			request.setAttribute("roomNo", roomNo);

			Pager pager = new Pager();
			List<Hotelaccount> has=new ArrayList<Hotelaccount>();
			// �Ƚ��÷����id����������ݷ����===���Ż�
			if (roomNo != "" && roomNo != null) {
				List<Guestroom> rooms = guestroomDao.findRoomByRoomno(Integer.parseInt(roomNo));
				Guestroom room = rooms.get(0);
				roomid = room.getRoomid();
				System.out.println(roomNo + "����� id:" + roomid);
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
	//===�ҵ��˵�
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
					System.out.println("�ҵ��ʵ�id : "+ myAccountId);
					Hotelaccount ha = haService.findMyAccount(myAccountId);
					request.setAttribute("myAccount", ha);
					request.getSession().setAttribute("myHaIdAg", hid);
					request.getSession().setAttribute("myHaRoomId", ha.getGuestroom().getRoomid());//������Ʒ�ķ����
				
			}
			else if(hid != null && hid != "" && hid != "yfid")
			{
				int myAccountId = Integer.parseInt(hid);
				System.out.println("�ҵ��ʵ�id : "+ myAccountId);
				Hotelaccount ha = haService.findMyAccount(myAccountId);
				request.setAttribute("myAccount", ha);
				request.getSession().setAttribute("myHaIdAg", hid);
				request.getSession().setAttribute("myHaRoomId", ha.getGuestroom().getRoomid());//������Ʒ�ķ����
			}
			//=======��Ʒ�ܼ�========
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
	
	//=========�˷�����ȡ��Ʒ����===========
	public String checkOutMyaccount()
	{
		request=ServletActionContext.getRequest();
		try {
			//===��ȡ�����
			String roomNo = request.getParameter("roomno");
			int rn = Integer.parseInt(roomNo);
			
			//��ȡ��Ʒ����
			String goodsConsume = request.getParameter("goodscms");
			float gc = Float.parseFloat(goodsConsume);
			
			//��ȡ��ע
			String remarkss = request.getParameter("remark");
			System.out.println("����� �� "+roomNo + "  ��Ʒ���ѣ�" +goodsConsume+"  ��ע��"+remarkss);
			Guestroom gs = new Guestroom();
			gs.setRoomno(rn);
			Hotelaccount ha = new Hotelaccount();
			ha.setGuestroom(gs);
			ha.setRemarks(remarkss);
			haDao.updatehotelAccount(ha, gc);
			request.setAttribute("createHAsuccess", "������ӳɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("checkOutMyaccount is error!");
		}
		return "checkoutss";
	}
}
