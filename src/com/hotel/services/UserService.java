package com.hotel.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.hotel.dao.PageinationDAO;
import com.hotel.dao.UserDAO;
import com.hotel.daoImpl.UserDAOImpl;
import com.hotel.pojo.Pager;
import com.hotel.pojo.Usertable;

@Component(value="userService")
public class UserService {
	private UserDAO userDao;
	private PageinationDAO pageNext;
	
	public PageinationDAO getPageNext() {
		return pageNext;
	}

	public void setPageNext(PageinationDAO pageNext) {
		this.pageNext = pageNext;
	}

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
//鏌ヨ鎵�鏈�
	public List<Usertable> listAllUser(){
		List<Usertable> usertable=userDao.listAllUser();
		return usertable;
	}
	//娣诲姞
	public boolean addUser(Usertable user){		
		return userDao.addUser(user);		
	}
	//閫氳繃鍛樺伐ID鏌ヨ
		public Usertable findUserById(Usertable user){
			return userDao.findUserById(user);
			
		} 
	//淇敼
	public boolean updateUser(Usertable userid){		
		return userDao.updateUser(userid);	
	}
	//鍛樺伐淇敼瀵嗙爜
	public boolean updateUserpassword(Integer userid,String password){
		return userDao.updateUserpassword(userid, password);
	}

	//鍒犻櫎
	public boolean deleteUser(Usertable userid){
		return userDao.deleteUser(userid);
		
	}	
	
	
	//閫氳繃涓嶅悓鐨勬潯浠舵煡璇�
		//閫氳繃userid鏌ヨ
	public List<Usertable> findTheUserByUserid(int userid){
		return userDao.findTheUserByUserid(userid);
	}
		//閫氳繃username鏌ヨ
	public List<Usertable> findTheUserByUsername(String username){		
		return userDao.findTheUserByUsername(username);		
	}
		//閫氳繃position鏌ヨ
	public List<Usertable> findTheUserByUserposition(String position){
		return userDao.findTheUserByUserposition(position);
		
	}
	//鍒嗛〉
	public Pager showPage(int nowPage)
	{
		Pager page = new Pager();
		page.setPage(nowPage);
		page.setRows(10);
		page.setHql("from  Usertable");
		Pager newPage = pageNext.pagerff(page, null);
		return newPage;
	}
	//鏌ヨ鍒嗛〉
	public Pager findPager(int key ,String hql,String keyContent,int tpage)
	{
		Pager page = new Pager();
		page.setPage(tpage);
		page.setRows(10);
		page.setHql(hql);
		Map<String, Object> param= new HashMap<String, Object>();
		if(key == 1)//閫氳繃鍛樺伐id
		 param.put("userid",Integer.parseInt(keyContent));
		else if(key == 2)//閫氳繃鍛樺伐濮撳悕
			param.put("username",keyContent);
		else if(key == 3)//閫氳繃鑱屽姟
			param.put("position",keyContent);		
		Pager newPage = pageNext.pagerff(page, param);
		return newPage;
	}
	//閫氳繃鐢ㄦ埛鍚嶆煡鎵�
	public Usertable findUserByUsername(Usertable username){
			return userDao.findUserByUsername(username);		
	}
	public Usertable findByUsername(String username){
		Usertable user=userDao.findByUsername(username);
		return user;
	}

}
