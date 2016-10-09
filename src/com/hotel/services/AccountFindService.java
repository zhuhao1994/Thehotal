package com.hotel.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.stereotype.Component;

import com.hotel.HButil.ExcelHP;
import com.hotel.HButil.HButil;
import com.hotel.HButil.HcRtoJson;
import com.hotel.HButil.PagerToJson;
import com.hotel.dao.AccountFindDAO;
import com.hotel.dao.PageinationDAO;
import com.hotel.pojo.HcRecord;
import com.hotel.pojo.Pager;

@Component(value = "accountFindService")
public class AccountFindService {
	private AccountFindDAO accountFindDAO;
	private PageinationDAO pageNext;
	private List<HcRecord> hc;
	private JSONArray  hcRJs=null;
	public PageinationDAO getPageNext() {
		return pageNext;
	}
	public void setPageNext(PageinationDAO pageNext) {
		System.out.println("set pageNext");
		this.pageNext = pageNext;
	}
	public List<HcRecord> findAccountByYear(String year){
		System.out.println("accountFindService");
		return accountFindDAO.findAccountByYear(year);
		
	}
	//��ʾһ���µļ�¼
	public Pager showPageOneMonth(int nowPage,String date)
	{
		Pager page = new Pager();
		page.setPage(nowPage);
		page.setRows(10);//and month(leavetime)=:month
		page.setHql("from Hotelaccount where accuntstatus=1 and year(leavetime)=:year and month(leavetime)=:month");
		Map<String, Object> param= new HashMap<String, Object>();
		param.put("year", 2016);
		param.put("month", 2);
		Pager newPage = pageNext.pagerff(page, param);
		return newPage;
	}
	public Pager xxx(Date date,int nowPage) {
		String hq = "select leavetime,count(hid),count(cusid),sum(consume),sum(allconsume) from Hotelaccount where accuntstatus=1 ";
		Pager page = new Pager();
		page.setPage(nowPage);
		page.setRows(10);//and month(leavetime)=:month
		page.setHql("from Hotelaccount where accuntstatus=1 and year(leavetime)=:year and month(leavetime)=:month");
		Map<String, Object> param= new HashMap<String, Object>();
		param.put("year", 2016);
		param.put("month", 2);
		//pageNext=new PageinationDAOImpl();
		Pager newPage = pageNext.pagerff(page, param);
		return newPage;
	}
	//ͳ�Ƽ�¼����˵��
	public List<HcRecord> countByNull(String year ,String month,String day){
		if(year==null||year.equals("null")||year.equals("")){
			hc=accountFindDAO.countAll();
		}else if(month==null||month.equals("null")||month.equals("")){
			System.out.println(year+"serivce");
			hc=accountFindDAO.countByMOnthOfYear(year);
		}else if(day==null||day.equals("null")||day.equals("")){
			System.out.println(day+"serivce");
			hc=accountFindDAO.countByDayOfMonth(year, month);
		}else {
			hc=accountFindDAO.countByOneDay(year, month, day);
		}
		if(hc==null||hc.size()<0) { 
			System.out.println("countByNull ����");
			return null;
		}
		return hc;
		
	}
	//����jsonArray
	public JSONArray empPf(String username,String year ,String month,String day){
		System.out.println("empPfservice ");
		if(year==null||year.equals("null")||year.equals("")){
			hc=accountFindDAO.empPf(username);
			hcRJs=HcRtoJson.hcRtoJson(username, hc);
		}else if(month==null||month.equals("null")||month.equals("")){
			hc=accountFindDAO.empPf(username, year);
			hcRJs=HcRtoJson.hcRtoJson(username, hc, year);
		}else if(day==null||day.equals("null")||day.equals("")){
			hc=accountFindDAO.empPf(username, year, month);
			hcRJs=HcRtoJson.hcRtoJson(username, hc, year, month);
		}else {
			System.out.println("empPf(username, year, month, day)");
			hc=accountFindDAO.empPf(username, year, month, day);
			hcRJs=HcRtoJson.hcRtoJson(username, hc, year, month, day);
		}
		if(hc==null||hc.size()<0) { 
			System.out.println("countByNull ����");
			return null;
		}
		return hcRJs;
		
	}
	//��ѯ����Ա��ҵ��
		public Pager empPf(String year, String month, String day,int nowPage,int rows) {
			String s="select usertable.userid,usertable.realname,leavetime,count(hid),count(cusid),sum(consume),sum(allconsume) from Hotelaccount where accuntstatus=1";
			StringBuffer sb=new StringBuffer(s);
			Map<String, Object> param= new HashMap<String, Object>();
			//�������Դ���������""
			if(!(year==null||year.equals("null")||year.equals(""))){
				sb.append(" and year(leavetime)=:year ");
			}
			if(!(month==null||month.equals("null")||month.equals(""))){
				sb.append(" and month(leavetime)=:month ");
			}
			if(!(day==null||day.equals("null")||day.equals(""))){
				sb.append(" and day(leavetime)=:day ");
			}
			sb.append(" group by usertable.userid order by sum(allconsume) desc");
			if(!(year==null||year.equals("null")||year.equals(""))){
				param.put("year", Integer.parseInt(year));
			}
			if(!(month==null||month.equals("null")||month.equals(""))){
				param.put("month", Integer.parseInt(month));
			}
			if(!(day==null||day.equals("null")||day.equals(""))){
				param.put("day", Integer.parseInt(day));
			}
			Pager page = new Pager();
			page.setPage(nowPage);
			if(rows<=0){
				page.setRows(4);
			}else{
				page.setRows(rows);
			}
			page.setHql(sb.toString());
			Pager newPage = pageNext.pagerff(page, param);
			
			return newPage;
		}
		
	public boolean toExcleService(String username,String year ,String month,String day,String path){
		System.out.println("toExcleservice ");
		Boolean bool=null;
		String excelname=username;
		int cutdateNum=1;
		if(year==null||year.equals("null")||year.equals("")){
			hc=accountFindDAO.empPf(username);
			excelname+="ÿ��ҵ��";
			cutdateNum=1;
			String[] title={"Ա��","��","�ͻ�����","ס������","��Ʒ����","�ϼ�"};
			bool = ExcelHP.listToExcel(hc, title, excelname, cutdateNum, username,path);
		}else if(month==null||month.equals("null")||month.equals("")){
			hc=accountFindDAO.empPf(username, year);
			excelname+=year+"ҵ��";
			cutdateNum=2;
			String[] title={"Ա��","��","��","�ͻ�����","ס������","��Ʒ����","�ϼ�"};
			bool = ExcelHP.listToExcel(hc, title, excelname, cutdateNum, username,path);
		}else if(day==null||day.equals("null")||day.equals("")){
			hc=accountFindDAO.empPf(username, year, month);
			excelname+=year+"��"+month+"��"+"ҵ��";
			cutdateNum=3;
			String[] title={"Ա��","��","��","��","�ͻ�����","ס������","��Ʒ����","�ϼ�"};
			bool = ExcelHP.listToExcel(hc, title, excelname, cutdateNum, username,path);
		}else {
			hc=accountFindDAO.empPf(username, year, month,day);
			excelname+=year+"��"+month+"��"+day+"��"+"ҵ��";
			cutdateNum=3;
			String[] title={"Ա��","��","��","��","�ͻ�����","ס������","��Ʒ����","�ϼ�"};
			bool = ExcelHP.listToExcel(hc, title, excelname, cutdateNum, username,path);
		}
		if(hc==null||hc.size()<0) { 
			System.out.println("toExcleService ����");
		}
		return bool;
		
	}
	
	public AccountFindDAO getAccountFindDAO() {
		return accountFindDAO;
	}

	public void setAccountFindDAO(AccountFindDAO accountFindDAO) {
		this.accountFindDAO = accountFindDAO;
	}
	
}
