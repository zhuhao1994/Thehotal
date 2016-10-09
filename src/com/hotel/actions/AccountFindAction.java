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
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hotel.HButil.EmpfToExcel;
import com.hotel.HButil.ExcelHP;
import com.hotel.HButil.PagerToJson;
import com.hotel.pojo.HcRecord;
import com.hotel.pojo.Hotelaccount;
import com.hotel.pojo.Pager;
import com.hotel.services.AccountFindService;
import com.opensymphony.xwork2.ActionSupport;

@Component(value = "accountFindAction")
@ParentPackage(value = "struts-default")
@Namespace(value = "/")
@Action(value = "AccountFindAction")
@Results({ @Result(name = "showOneYear", location = "/page/accountFind/showYearAccound.jsp"),
		@Result(name = "NoshowAllHa", location = "/index.jsp"),
		@Result(name = "showOneMonth", location = "/page/accountFind/showMonthAccound.jsp"),

		// countByRecords方法
		@Result(name = "countYearAll", location = "/page/accountFind/yearAll.jsp"),
		@Result(name = "countOneYear", location = "/page/accountFind/countOneYear.jsp"), // countOneYear
		@Result(name = "countOneMonth", location = "/page/accountFind/countOneMonth.jsp"),
		@Result(name = "countOneDay", location = "/page/accountFind/countOneDay.jsp"),
		// empPf方法 员工业绩
		@Result(name = "countYearAll", location = "/page/accountFind/yearAll.jsp"),
		@Result(name = "countOneYear", location = "/page/accountFind/countOneYear.jsp"), // countOneYear
		@Result(name = "countOneMonth", location = "/page/accountFind/countOneMonth.jsp"),
		@Result(name = "countOneDay", location = "/page/accountFind/countOneDay.jsp"),

})
public class AccountFindAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Autowired
	private AccountFindService accountFindService;
	// private static HttpServletResponse response;
	private static HttpServletRequest request;
	private static HttpServletResponse response;

	public AccountFindAction() {
	}

	public void setAccountFindService(AccountFindService accountFindService) {
		this.accountFindService = accountFindService;
	}

	public AccountFindService getAccountFindService() {
		return accountFindService;
	}

	public String showOneYear() {
		// response = ServletActionContext.getResponse();
		// System.out.println("showOneYear");
		request = ServletActionContext.getRequest();
		List oneyear = accountFindService.findAccountByYear("2016");
		if (oneyear.size() > 0) {
			request.setAttribute("oneyear", oneyear);
			return "showOneYear";
		} else
			return "NoshowAllHa";
	}

	public String showOneMonth() {
		System.out.println("showOneMonth");
		request = ServletActionContext.getRequest();
		int nowPage = Integer.parseInt(request.getParameter("nowpage"));
		Pager pager = accountFindService.showPageOneMonth(nowPage, "2016-02");
		List<Hotelaccount> oneMonth = (List<Hotelaccount>) pager.getList();
		if (oneMonth.size() > 0) {
			// 根据不同的内容做查询
			request.setAttribute("oneMonth", oneMonth);
			request.setAttribute("monthPage", pager);
			return "showOneMonth";
		} else
			return null;
	}

	public String countByRecords() {
		System.out.println("countByRecords");
		request = ServletActionContext.getRequest();
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		List<HcRecord> hc = accountFindService.countByNull(year, month, day);
		System.out.println(hc.size());
		request.setAttribute("countByRecords", hc);
		if (year == null || year.equals("null") || year.equals("")) {
			// 统计每年
			return "countYearAll";
		} else if (month == null || month.equals("null") || month.equals("")) {
			return "countOneYear";
		} else if (day == null || day.equals("null") || day.equals("")) {
			System.out.println("countOneMonth");
			return "countOneMonth";
		} else {
			return "countOneDay";
		}
	}

	public void empPf() {
		response = ServletActionContext.getResponse();
		response.setContentType("text/json;charset=utf-8");
		request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		System.out.println(year);
		System.out.println(month);
		System.out.println(day);
		System.out.println(username);
		try {
			PrintWriter out = response.getWriter();
			if (username.equals(null) || username.equals("null") || username.equals("")) {
				System.out.println("username 未取到或没输入");
				out.write("usernamenull");
			} else {
				JSONArray hcRJs = accountFindService.empPf(username, year, month, day);
				System.out.println(hcRJs.toString());
				out.write(hcRJs.toString());
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void allempPf() {
		response = ServletActionContext.getResponse();
		response.setContentType("text/json;charset=utf-8");
		request = ServletActionContext.getRequest();
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String nowPage = request.getParameter("nowPage");
		System.out.println(year);
		System.out.println(month);
		System.out.println(day);
		System.out.println(nowPage);
		try {
			PrintWriter out = response.getWriter();
			Pager newPage = accountFindService.empPf(year, month, day, Integer.parseInt(nowPage),0);
			JSONArray empfJs;
			try {
				empfJs = PagerToJson.PagerToJson(newPage);
				out.write(empfJs.toString());
			} catch (Exception e) {
				e.printStackTrace();
				out.write("");
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			System.out.println("dfhj");
			e.printStackTrace();
		}
	}

	// 用于返回所有记录（json)；
	public void allempPfToExcel() {
		response = ServletActionContext.getResponse();
		response.setContentType("text/json;charset=utf-8");
		request = ServletActionContext.getRequest();
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String rowsTotal = request.getParameter("rowsTotal");
		System.out.println(year);
		System.out.println(month);
		System.out.println(day);
		System.out.println(rowsTotal);
		try {
			PrintWriter out = response.getWriter();
			Pager newPage = accountFindService.empPf(year, month, day, Integer.parseInt("1"),Integer.parseInt(rowsTotal));
			String path = request.getRealPath("/");
			Boolean bool=false;
			if(year==null||year.equals("null")||year.equals("")){
				String title[]={"员工编号","员工","客户数量","住房消费","商品消费","合计"};
				bool=EmpfToExcel.listToExcel(newPage.getList(), title, "所有员工总业绩表", 0, path);
			}
			else if(month==null||month.equals("null")||month.equals("")){
				String title[]={"员工编号","员工","年","客户数量","住房消费","商品消费","合计"};
				bool=EmpfToExcel.listToExcel(newPage.getList(), title, "所有员工总业绩表", 1, path);
			}
			else if(day==null||day.equals("null")||day.equals("")){
				String title[]={"员工编号","员工","年","月","客户数量","住房消费","商品消费","合计"};
				bool=EmpfToExcel.listToExcel(newPage.getList(), title, "所有员工总业绩表", 2, path);
			}else{
				String title[]={"员工编号","员工","年","月","日","客户数量","住房消费","商品消费","合计"};
				bool=EmpfToExcel.listToExcel(newPage.getList(), title, "所有员工总业绩表", 3, path);
			}
			
			JSONArray hcRJs = new JSONArray();
			JSONObject hj = new JSONObject();
			System.out.println(bool);
			if (bool) {
				// 传给页面excel 地址
				// System.out.println("excel/excel successs");
				String url=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"; 
				hj.put("url", url+"excel/tongji.xls");
				hcRJs.put(hj);
				out.write(hcRJs.toString());
			} else {
				hj.put("url", "");
				System.out.println("excel false ");
				out.write("");
			}
		} catch (Exception e) {
			System.out.println("dfhj");
			e.printStackTrace();
		}
	}

	public void dateToexcel() {
		response = ServletActionContext.getResponse();
		response.setContentType("text/text;charset=utf-8");
		request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		System.out.println(year);
		System.out.println(month);
		System.out.println(day);
		System.out.println(username);
		try {
			PrintWriter out = response.getWriter();
			String title[] = null;
			System.out.println("dateToexcel");
			if (username.equals(null) || username.equals("null") || username.equals("")) {
				System.out.println("excel错误");
				out.write("usernamenull");
			} else {
				String path = request.getRealPath("/");
				// System.out.println(path);
				Boolean bool = accountFindService.toExcleService(username, year, month, day, path);
				JSONArray hcRJs = new JSONArray();
				JSONObject hj = new JSONObject();
				if (bool) {
					// 传给页面excel 地址
					// System.out.println("excel/excel successs");
					String url=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"; 
					hj.put("url", url+"excel/tongji.xls");
					hcRJs.put(hj);
					out.write(hcRJs.toString());
				} else {
					hj.put("url", "");
					System.out.println("excel false ");
					out.write("");
				}
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
