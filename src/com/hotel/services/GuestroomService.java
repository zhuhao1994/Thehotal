package com.hotel.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.hotel.dao.GuestroomDAO;
import com.hotel.dao.PageinationDAO;
import com.hotel.pojo.Guestroom;
import com.hotel.pojo.Pager;

@Component(value = "guestroomService")
public class GuestroomService {

	private GuestroomDAO guestroomDao;
	
	private PageinationDAO pageNext; 

	public GuestroomDAO getGuestroomDao() {
		return guestroomDao;
	}

	public void setGuestroomDao(GuestroomDAO guestroomDao) {
		this.guestroomDao = guestroomDao;
		
	}

	public PageinationDAO getPageNext() {
		return pageNext;
	}

	public void setPageNext(PageinationDAO pageNext) {
		this.pageNext = pageNext;
	}
	
	//**************service方法*******
	// 添加
	public boolean addGuestroom(Guestroom guestroom) {
		System.out.println(guestroomDao);
		return guestroomDao.addGuestroom(guestroom);

	}

	// 查看
	public List<Guestroom> listAllRooms() {
		return guestroomDao.listAllRooms();
	}
	//修改
	public boolean updateGuestroom(Guestroom guestroom){
		System.out.println(guestroomDao);
		return guestroomDao.updateGuestroom(guestroom);
	}
	//删除
	public boolean deleteGuestroom(Guestroom guestroom){
		return guestroomDao.deleteGuestroom(guestroom);
	}
	//查找（房间号）
	public List<Guestroom> findRoomByRoomno(int roomno){
		return guestroomDao.findRoomByRoomno(roomno);
	}
	//查找（客房类型）
	public List<Guestroom> findRoomByRoomtype(String roomtype){
		return guestroomDao.findRoomByRoomtype(roomtype);
	}
	//查找（客房状态）
	public List<Guestroom> findRoomByRoomstatus(int roomstatus){
		return guestroomDao.findRoomByRoomstatus(roomstatus);
	}
	
	//查找（客房id）
	public Guestroom findTheRoomByRoomid(Guestroom guestroom){
		return guestroomDao.findTheRoomByRoomid(guestroom);
	}
	//分页:查询===from  Guestroom===
	public Pager showPageHa(int nowPage){
		Pager page = new Pager();
		page.setPage(nowPage);
		page.setRows(10);
		page.setHql("from  Guestroom");
		Pager newPage = pageNext.pagerff(page, null);
		return newPage;
	}
	
	//分页查询
	public Pager findPager(int key ,String hql,String keyContent,int tpage)
	{
		Pager page = new Pager();
		page.setPage(tpage);
		page.setRows(10);
		page.setHql(hql);
		Map<String, Object> param= new HashMap<String, Object>();
		if(key == 1)//通过房间号
		 param.put("roomno",Integer.parseInt(keyContent));
		else if(key == 2)//通过客房类型
			param.put("roomtype",keyContent);
		else if(key == 3)//通过入住状态
			param=null;
		
		
		Pager newPage = pageNext.pagerff(page, param);
		return newPage;
	}

}
