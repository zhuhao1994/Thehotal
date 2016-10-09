package com.hotel.daoImpl;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Component;

import com.hotel.HButil.HButil;
import com.hotel.dao.UserDAO;
import com.hotel.pojo.Customer;
import com.hotel.pojo.Usertable;

@Component(value="userDao")
public class UserDAOImpl extends HButil implements UserDAO{
	private  Session s;
	
	public UserDAOImpl() {
		System.out.println("userdaoimpl");
	}

	//鐧诲綍
	@Override
	public Usertable login(Usertable user) {
		Criteria c=HButil.getSession().createCriteria(Usertable.class);
		c.add(Expression.eq("username", user.getUsername()));
		c.add(Expression.eq("password", user.getPassword()));
		return (Usertable) c.uniqueResult();
	}
	
	//娣诲姞鍛樺伐
	@Override
	public boolean addUser(Usertable userid) {
		System.out.println("addUser");
		try{
		 s = HButil.getSession();
		Transaction tr= s.beginTransaction();
			s.save(userid);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("addPicture is error!");
			return false;
		}finally {
			HButil.closeSession(s);
		}
	}
	
	//鍛樺伐缂栧彿
		@Override
		public Usertable findUserById(Usertable user){
			Usertable usertable=new Usertable();
			try{
			s=HButil.getSession();
			String hql = "from Usertable where userid =?";
			Query query = (Query) s.createQuery(hql);
		    query.setInteger(0, user.getUserid());
		    usertable= (Usertable) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("findUser is error!");
		}finally {
			HButil.closeSession(s);
		}
			return usertable;
		}
		
		
	//鏄剧ず鎵�鏈�
	@Override
	public List<Usertable> listAllUser() {
		Query q=HButil.getSession().createQuery("from Usertable");
		return q.list();
		
	}
	
	//淇敼
	@Override
	public boolean updateUser(Usertable userid) {
		Usertable usertable = this.findUserById(userid);
		int id=usertable.getUserid();
		s=HButil.getSession();
		Transaction ts=s.beginTransaction();
		try {
			if(usertable !=null){
			usertable=userid;
			usertable.setUserid(id);
			s.update(usertable);
			System.out.println("#############");
			ts.commit();
			return true;
			}else{
				System.out.println("updateUser is error!");
				return false;
			}
		} catch (Exception e) {
			return false;
		}finally{
			HButil.closeSession(s);
		}
	}
	//鍒犻櫎
	@Override
	public boolean deleteUser(Usertable userid) {
		try {
			Usertable usertable = this.findUserById(userid);
			s= HButil.getSession();
			Transaction ts = s.beginTransaction();
			s.delete(usertable);
			ts.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("deleteUser is error!");
			return false;
		}finally {
			HButil.closeSession(s);
		}
	}
	//鍒嗛〉鎿嶄綔
	@Override
	public List<Usertable> getUsersByPage(int page, int count) {
		Criteria c=HButil.getSession().createCriteria(Usertable.class);
		c.setFirstResult((page-1)*count);
		c.setMaxResults(count);
		return c.list();
	}
	@Override
	public int getAllUsersCount() {
		Criteria c=HButil.getSession().createCriteria(Usertable.class);
		c.setProjection(Projections.count("userid"));		
		return Integer.parseInt(c.uniqueResult().toString());
	}
	//閫氳繃涓嶅悓鐨勬潯浠惰繘琛屾煡璇紙鏄剧ず鎵�鏈夌殑鍛樺伐淇℃伅涓婄殑鏌ヨ锛�
		//閫氳繃id鏌ヨ
	@Override
	public List<Usertable> findTheUserByUserid(int userid) {
		Criteria c = HButil.getSession().createCriteria(Usertable.class);
		c.add(Expression.eq("userid", userid));
		return c.list();
	}
		//閫氳繃鐢ㄦ埛鍚嶆煡璇�
	@Override
	public List<Usertable> findTheUserByUsername(String username) {
		Criteria c = HButil.getSession().createCriteria(Usertable.class);
		c.add(Expression.eq("username", username));
		return c.list();
	}
		//閫氳繃鑱屽姟杩涜鏌ヨ
	@Override
	public List<Usertable> findTheUserByUserposition(String position) {
		Criteria c = HButil.getSession().createCriteria(Usertable.class);
		c.add(Expression.eq("position", position));
		return c.list();
	}
	
	//閫氳繃鐢ㄦ埛鍚嶆煡鎵�
		public Usertable findUserByUsername(Usertable username) {
			Usertable usertable=new Usertable();
			try{
			s=HButil.getSession();
			String hql = "from Usertable where username =?";
			Query query = (Query) s.createQuery(hql);
		    query.setString(0, username.getUsername());
		    usertable= (Usertable) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("findUser is error!");
		}finally {
			HButil.closeSession(s);
		}
			return usertable;
		}
	
	//鍛樺伐淇敼瀵嗙爜
		@Override
		public boolean updateUserpassword(Integer userid,String password) {
			
			s = HButil.getSession();
			Transaction tr= s.beginTransaction();
			String hql="update  Usertable  u  set u.password=? where u.userid=?";
			Query query=(Query)s.createQuery(hql);
			query.setString(0, password);
			query.setInteger(1, userid);
			int s=query.executeUpdate();
			tr.commit();
			if(s==0){
				return false;
			}else{
				return true;
			}
			
		}

	
	@Override
	public Usertable findByUsername(String username) {
		Criteria c=HButil.getSession().createCriteria(Usertable.class);
		c.add(Expression.eq("username",username));
		return (Usertable)c.uniqueResult();
	}

	
}
