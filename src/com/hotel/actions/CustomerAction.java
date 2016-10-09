package com.hotel.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.stereotype.Component;

import com.hotel.pojo.Customer;
import com.hotel.pojo.Pager;
import com.hotel.pojo.Vip;
import com.hotel.services.CustomerService;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

@Component(value = "customerAction")
@ParentPackage(value = "struts-default")
@Namespace(value = "/")
@Action(value = "CustomerAction")
@Results({ @Result(name = "customerss", location = "/page/customer/findCustomer.jsp"),
		@Result(name = "addSuccess", location = "/page/customer/findCustomer.jsp"),
		@Result(name = "addFails", location = "/page/customer/addCustomer.jsp"),
		@Result(name = "querySuccess", location = "/page/customer/findCustomer.jsp"),
		@Result(name = "queryFails", location = "/page/customer/findCustomer.jsp"),
		@Result(name = "loadSuccess", location = "/page/customer/updateCustomer.jsp"),
		@Result(name = "loadFails", location = "/page/customer/updateCustomer.jsp"),
		@Result(name = "updateSuccess", location = "/page/customer/reupdate.jsp"),
		@Result(name = "updateFails", location = "/page/customer/updateCustomer.jsp"),

})
public class CustomerAction extends ActionSupport implements SessionAware {
	private CustomerService customerService;
	private Map<String, Object> session;
	private Customer customer;
	private String cardid;
	private String custel;
	private Double balance;

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getCustel() {
		return custel;
	}

	public void setCustel(String custel) {
		this.custel = custel;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = session;
	}

	// ����ͬ�������ҿͻ�
	public String queryByTerm() {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Customer> customers = new ArrayList<Customer>();
		try {
			String term = request.getParameter("se");// ��ѯ�ķ���

			// ����ѯ�ķ���
			String keywords = request.getParameter("keywords").trim();// ��ѯ�Ĳ���
			request.getSession().setAttribute("term", term);// ����ѯ�ķ���������
			if (keywords == "")// ���Ϊ�ղ�ѯ��������
			{
				System.out.println("��ѯ����Ϊ�գ�");
				shoaPageHa();
				return "querySuccess";
			}
			request.getSession().setAttribute("term", term);// ����ѯ�ķ���������
			keywords = URLDecoder.decode(keywords, "utf-8");
			request.setAttribute("keywords", keywords);// ����ѯ�Ĳ���������

			int page = Integer.parseInt(request.getParameter("nowpage"));
			System.out.println("��ȡ����page " + page);

			Pager pager = new Pager();
			if (term.equals("cusname")) {
				String hql = "from  Customer where cusname=:cusname";
				pager = customerService.findPager(1, hql, keywords, page);

			} else if (term.equals("cardid")) {
				String hql = "from  Customer where cardid=:cardid";
				pager = customerService.findPager(2, hql, keywords, page);
			} else {
				String status = keywords;
				if (status.equals("δ��ס")) {
					String hql = "from  Customer where status=0";
					pager = customerService.findPager(3, hql, keywords, page);
				} else if (status.equals("��ס")) {
					String hql = "from  Customer where status=1";
					pager = customerService.findPager(3, hql, keywords, page);
				}
			}
			customers = (List<Customer>) pager.getList();
			if (customers != null) {
				// �������ҳ����ʾ
				request.setAttribute("haPage", pager);
				request.setAttribute("showCus", customers);
				return "querySuccess";
			} else {
				return "queryFails";
			}

		} catch (Exception e) {

		}
		if (customers != null) {
			request.setAttribute("showCus", customers);
			return "querySuccess";
		} else {
			return "queryFails";
		}
	}

	// ��ҳ��ʾ���пͻ�
	public String shoaPageHa() {
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			String thePath = request.getParameter("thePath").toString();
			if (thePath != null) {
				System.out.println("��һ��:thePath");
				request.getSession().setAttribute("thePath", thePath);
			}
		} catch (Exception e) {
			System.out.println("shoaPageHa ��һ��Ŀ¼��error!");
		}

		int nowPage = Integer.parseInt(request.getParameter("nowpage"));
		Pager pager = customerService.showPageHa(nowPage);
		List<Customer> customers = (List<Customer>) pager.getList();
		if (customers.size() > 0) {
			// ���ݲ�ͬ����������ѯ
			request.setAttribute("showCus", customers);
			request.setAttribute("haPage", pager);
			return "customerss";
		} else
			return "index";
	}

	// ����û�
	public String addCustomer() throws ParseException {
		HttpServletRequest request = ServletActionContext.getRequest();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy/MM/dd");
		Vip vip = new Vip();
		String vid = request.getParameter("vid");
		vip.setVid(Integer.valueOf(vid));
		String cusname = request.getParameter("cusname");
		String cussex = request.getParameter("cussex");
		String cardid = request.getParameter("cardid");
		String cusbirth = request.getParameter("cusbirth");
		String custel = request.getParameter("custel");
		String address = request.getParameter("address");
		String balance = request.getParameter("balance");
		String status = request.getParameter("status");

		Customer customer = new Customer(vip, cusname, cardid, cussex, custel, Double.parseDouble(balance),
				sim.parse(cusbirth), address, Integer.parseInt(status));

		if (customerService.addCustomer(customer)) {
			// ******ϣ��������������Լ�������ͻ���ѯһ��****
			request.setAttribute("keywords", cusname);
			request.getSession().setAttribute("term", "cusname");
			request.setAttribute("autoSearch", "autoSearch");
			return "addSuccess";
		} else {
			return "addFails";
		}
	}

	// ��ѯ��������===update��ʾ
	public String loadCustomer(){ 
		HttpServletRequest request =ServletActionContext.getRequest(); 
		
			String cusid=request.getParameter("cusid"); 
			System.out.println(cusid.toString());
			Customer cus=new Customer();
			cus.setCusid(Integer.valueOf(cusid));
			Customer customer=customerService.loadCustomerInfoByCusId(cus);
			System.out.println(customer.getCusname().toString());
		
	
		request.setAttribute("c", customer);
		return "loadSuccess";
	}

	// ========�޸�===========
	public String updateCustomer() throws NumberFormatException, ParseException {
		HttpServletRequest request = ServletActionContext.getRequest();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy/MM/dd");
		Vip vip = new Vip();
		String vid = request.getParameter("vid");
		vip.setVid(Integer.valueOf(vid));
		String cusid = request.getParameter("cusid");
		String cusname = request.getParameter("cusname");
		String cussex = request.getParameter("cussex");
		String cardid = request.getParameter("cardid");
		String cusbirth = request.getParameter("cusbirth");
		String y = cusbirth.substring(0, 4);
		String m = cusbirth.substring(5, 7);
		String d = cusbirth.substring(8, 10);

		System.out.println(cusbirth.toString());
		String custel = request.getParameter("custel");
		String address = request.getParameter("address");
		String balance = request.getParameter("balance");
		String status = request.getParameter("status");

		Customer customer = new Customer(Integer.valueOf(cusid), vip, cusname, cardid, cussex, custel,
				Double.parseDouble(balance), sim.parse(y + "/" + m + "/" + d), address, Integer.parseInt(status));
		if (customerService.updateCustomer(customer)) {
			request.setAttribute("c", customer);
			request.setAttribute("updateCusLog", "���³ɹ���");
			return "updateSuccess";
		} else {
			request.setAttribute("c", customer);
			request.setAttribute("updateCusLog", "���³ɹ���");
			return "updateFails";
		}

	}
	// =========��ֵ===========

	public void findCustomerByTel() {
		Customer customer=customerService.findCustomerByTel(custel);
		if(customer==null){
			System.out.println("customer is null");
		}
		System.out.println(custel);
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/json;charset=utf-8");
			PrintWriter out = response.getWriter();
			JSONObject object = new JSONObject();
			if (customer != null) {
				object.put("cusname", customer.getCusname());
				object.put("tell", "�˵绰�����Ѵ��ڣ�");
				object.put("custel", customer.getCustel());
				System.out.println("�˵绰�����Ѵ��ڣ�");
			} else {
				object.put("cusname", "�û�������!");
				System.out.println("�û�������!");
			}
			out.print(object);
			out.flush();
			out.close();
		} catch (IOException e) {
			System.out.println("findCustomerByTel is error!");
			e.printStackTrace();
		}

	}


	public void updateBalance() {
		Boolean bool = customerService.updateBalance(balance, custel);
		System.out.println("��� �� "+balance +" �绰 ��" +custel);
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/json;charset=utf-8");
			PrintWriter out = response.getWriter();
			JSONObject object = new JSONObject();
			if (bool) {
				object.put("message", "��ֵ�ɹ���");
			} else {
				object.put("message", "��ֵʧ�ܣ�");
			}
			out.print(object);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// ======ajax :����֤=======

	// ��ӿͻ�ʱ ajax�ж��û��Ѵ���(�������֤��)

	public void findCusById() {
		List<Customer> customers = customerService.findCustomerByCardid(cardid);

		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/json;charset=utf-8");
			PrintWriter out = response.getWriter();
			if (customers != null) {
				for (Customer customer : customers) {

					JSONObject obj = new JSONObject();
					obj.put("message", "�ͻ��Ѵ���");
					out.print(obj);
				}

				out.flush();
				out.close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
