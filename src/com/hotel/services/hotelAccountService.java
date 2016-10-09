package com.hotel.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hotel.dao.HotelAccountDAO;
import com.hotel.dao.PageinationDAO;
import com.hotel.pojo.Guestroom;
import com.hotel.pojo.Hotelaccount;
import com.hotel.pojo.Pager;

@Component(value = "haService")
public class hotelAccountService {
	private HotelAccountDAO haDao;
	private PageinationDAO pageNext;
	
	public PageinationDAO getPageNext() {
		return pageNext;
	}
	public void setPageNext(PageinationDAO pageNext) {
		System.out.println("set pageNext");
		this.pageNext = pageNext;
	}
	public HotelAccountDAO getHaDao() {
		return haDao;
	}
	public void setHaDao(HotelAccountDAO haDao) {
		this.haDao = haDao;
	}
	
//**************service方法*******
	public List<Hotelaccount> showhotelAccount()
	{
		System.out.println("进入 ：hotelAccountService");
		System.out.println(haDao);
		List<Hotelaccount> has =haDao.findAllHA();
		return has;
	}
	//分页:查询===from  Hotelaccount===
	public Pager showPageHa(int nowPage)
	{
		Pager page = new Pager();
		page.setPage(nowPage);
		page.setRows(10);
		page.setHql("from  Hotelaccount");
		Pager newPage = pageNext.pagerff(page, null);
		return newPage;
	}
	//分页：
	//分页查询
	public Pager findHAPager( String hql,String keyContent,int tpage)
	{
		Pager page = new Pager();
		page.setPage(tpage);
		page.setRows(10);
		page.setHql(hql);
		Map<String, Object> param= new HashMap<String, Object>();
	 	if(keyContent == "")
			{
				System.out.println("房号为空！");
				System.out.println(hql);
				param = null;
			}else{
				Guestroom gst = new Guestroom();
				gst.setRoomid(Integer.parseInt(keyContent));
				param.put("guestroom", gst);
			}
	 
		
		Pager newPage = pageNext.pagerff(page, param);
		System.out.println("执行了！"+newPage.getList().size());
		return newPage;
	}
	
	//====根据账单编号获取账单
	public Hotelaccount findMyAccount(int hid)
	{

		Pager page = new Pager();
		page.setPage(1);
		page.setRows(10);
		page.setHql("from  Hotelaccount where hid=:hid");
		Map<String, Object> param= new HashMap<String, Object>();
		param.put("hid", hid);
		Hotelaccount ha= (Hotelaccount) pageNext.pagerff(page, param).getList().get(0);
		System.out.println("我的账单 -姓名 ： "+ha.getCustomer().getCusname());
		return ha;
	}
	
}
