package com.hotel.dao;

import java.util.List;
import com.hotel.pojo.*;

public interface UserDAO {
	/* 1.员锟斤拷锟斤拷陆
	 * 2.锟斤拷锟皆憋拷锟斤拷锟较拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷裕锟皆憋拷锟斤拷锟脚ｏ拷锟矫伙拷锟斤拷锟斤拷锟斤拷锟诫，锟斤拷实锟斤拷锟斤拷锟斤拷锟斤拷锟秸ｏ拷锟皆憋拷职锟今，癸拷锟绞ｏ拷业锟斤拷锟斤拷
	 * 3.通锟斤拷员锟斤拷锟斤拷挪锟窖拷锟接︼拷锟皆憋拷锟斤拷锟较�
	 * 4.锟斤拷锟斤拷员锟斤拷寻锟斤拷示锟斤拷锟斤拷员锟斤拷锟斤拷息
	 * 5.锟斤拷通锟斤拷员锟斤拷锟斤拷挪锟斤拷锟斤拷锟接︼拷锟皆憋拷锟斤拷锟斤拷锟斤拷锟斤拷薷锟�
	 * 6.锟斤拷通锟斤拷员锟斤拷锟斤拷挪锟斤拷锟斤拷锟接︼拷锟皆憋拷锟斤拷锟斤拷锟斤拷锟缴撅拷锟�
	 * */
	
	//员工登陆
	public Usertable login(Usertable user);
	
	//添加员工信息
	public boolean addUser(Usertable userid);
	
	//通过员工编号查找员工
	public Usertable findUserById(Usertable userid);
	//通过用户名查找员工
	public Usertable findUserByUsername(Usertable username);
	//显示所有员工信息
	public List<Usertable> listAllUser();
	
	//管理员修改员工信息
	public boolean updateUser(Usertable user);
	//修改员工密码
	public boolean updateUserpassword(Integer userid, String userpassword);
	
	//管理员删除
	public boolean deleteUser(Usertable userid);
	
	//分页
	public List<Usertable> getUsersByPage(int page, int count);
	public int getAllUsersCount();
	//通过不同的条件查询员工的信息
	    //编号
	public List<Usertable> findTheUserByUserid(int userid);
		//用户名
	public List<Usertable> findTheUserByUsername(String username);
		//职务
	public List<Usertable> findTheUserByUserposition(String position);
	//
	public Usertable findByUsername(String username);

}
