package com.hotel.actions;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;

import com.hotel.pojo.Hotelaccount;
import com.hotel.pojo.Pager;
import com.hotel.services.hotelAccountService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Component(value = "hotel")

@ParentPackage(value = "struts-default") // strutsע��
@Namespace(value = "/")
@Action(value = "HotelAction")
@Results(value = { @Result(name = "showAllHa", location = "/page/checkInRoom/showHotelAccount.jsp"),
		@Result(name = "NoshowAllHa", location = "/index.jsp"),
		@Result(name = "showPageAllHa", location = "/page/checkInRoom/showHotelAccount.jsp"), })
public class HotelAccountAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private hotelAccountService haService;

	private Hotelaccount ha;

	public HotelAccountAction() {
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

	public String showAllHa() {
		request = ServletActionContext.getRequest();
		List<Hotelaccount> has = haService.showhotelAccount();
		if (has.size() > 0) {
			request.setAttribute("allHotelAccount", has);
			return "showAllHa";
		} else
			return "NoshowAllHa";
	}

	// ===��ҳ��ѯ�����Ѽ�¼
	public String shoaPageHa() throws UnsupportedEncodingException {
		request = ServletActionContext.getRequest();
		request.setCharacterEncoding("utf-8");
		try {

			int nowPage = Integer.parseInt(request.getParameter("nowpage"));
			Pager pager = haService.showPageHa(nowPage);
			// ��ȡ��ѯ�ķ���
			String theKey = request.getParameter("theKey").trim().toString();
			String keyContent = request.getParameter("keyContent").toString();
			keyContent = URLDecoder.decode(keyContent, "utf-8");
			System.out.println(keyContent);
			if (!theKey.equals("no")) {
				if (!theKey.equals("yes")) {
					request.getSession().setAttribute("theKey", theKey);
				} else if (theKey.equals("yes")) {
					try {
						if (request.getSession().getAttribute("theKey") == null) {
							System.out.println("kong");
						} else {
							theKey = request.getSession().getAttribute("theKey").toString();
							System.out.println(theKey);
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				request.setAttribute("keyContent", keyContent);

				if (!keyContent.equals("")) {
					pager = findHAbyCusName(theKey, keyContent, pager);
				}
			}

			List<Hotelaccount> has = (List<Hotelaccount>) pager.getList();
			if (has.size() > 0) {
				request.setAttribute("allHotelAccount", has);
				request.setAttribute("haPage", pager);
				return "showPageAllHa";
			} else
				return "showAllHa";
		} catch (Exception e) {
			System.out.println("shoaPageHa");
			return "showPageAllHa";
		}
	}

	// =================================================
	// ���ͻ����Ʋ�ѯ
	public Pager findHAbyCusName(String theKey, String keyContent, Pager pager) {
		Pager thePager = new Pager();
		thePager = pager;
		if (theKey.equals("cusName")) {
			// ==============��ѯ�����еĽ������ȡ��ɸѡ�Ľ����������===============
			List<Hotelaccount> has = haService.showhotelAccount();
			List<Hotelaccount> theHas = new ArrayList<Hotelaccount>();// ɸѡ�����Ĵ�ŵ�
			int rowsTotal = 0;
			int pageTotal = 0;
			for (Hotelaccount ha : has) {
				if (ha.getCustomer().getCusname().equals(keyContent)) {
					rowsTotal++;
					theHas.add(ha);
				}
			}
			pageTotal = rowsTotal / thePager.getRows();
			if (rowsTotal % thePager.getRows()!=0) {
				pageTotal += 1;
			}
			thePager.setRowsTotal(rowsTotal);
			thePager.setPageTotal(pageTotal);
			thePager.setList(theHas);
			if(rowsTotal > 10)
 		      thePager = Mypage(thePager);

		} else if (theKey.equals("roomType")) {
			// ==============��ѯ�����еĽ������ȡ��ɸѡ�Ľ����������===============
			List<Hotelaccount> has = haService.showhotelAccount();
			List<Hotelaccount> theHas = new ArrayList<Hotelaccount>();// ɸѡ�����Ĵ�ŵ�
			int rowsTotal = 0;
			int pageTotal = 0;
			for (Hotelaccount ha : has) {
				if (ha.getGuestroom().getRoomtype().equals(keyContent)) {
					rowsTotal++;
					theHas.add(ha);
				}
			}
			pageTotal = rowsTotal / thePager.getRows();
			if (pageTotal == 0) {
				pageTotal = 1;
			}
			thePager.setRowsTotal(rowsTotal);
			thePager.setPageTotal(pageTotal);
			thePager.setList(theHas);
			if(rowsTotal > 10)
 		      thePager = Mypage(thePager);


		} else if (theKey.equals("userName")) {
			// ==============��ѯ�����еĽ������ȡ��ɸѡ�Ľ����������===============
			List<Hotelaccount> has = haService.showhotelAccount();
			List<Hotelaccount> theHas = new ArrayList<Hotelaccount>();// ɸѡ�����Ĵ�ŵ�
			int rowsTotal = 0;
			int pageTotal = 0;
			for (Hotelaccount ha : has) {
				if (ha.getUsertable().getUsername().equals(keyContent)) {
					rowsTotal++;
					theHas.add(ha);
				}
			}
			pageTotal = rowsTotal / thePager.getRows();
			if (pageTotal == 0) {
				pageTotal = 1;
			}
			thePager.setRowsTotal(rowsTotal);
			thePager.setPageTotal(pageTotal);
			thePager.setList(theHas);
			if(rowsTotal > 10)
 		      thePager = Mypage(thePager);
		}
		return thePager;
	}
	
	//�Լ�д�ķ�ҳ���������Ƽ�
	public Pager Mypage(Pager pager)
	{
		List<Object> lists = (List<Object>) pager.getList();
		List<Object> Mylists = new ArrayList<Object>();
		int thefirst = (pager.getPage() - 1) * pager.getRows();
		int theEnd = pager.getRows()*pager.getPage();
		System.out.println("thefirst :" +thefirst+ "  theEnd�� "+theEnd);
		if(pager.getPage() == pager.getPageTotal())
			theEnd = pager.getRowsTotal();
		for(int i = thefirst;i < theEnd; i++)
		{
			Mylists.add(lists.get(i));
		}
		pager.setList(Mylists);
		return pager;
	}
}
