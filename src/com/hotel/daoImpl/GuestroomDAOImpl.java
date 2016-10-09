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
import com.hotel.dao.GuestroomDAO;
import com.hotel.pojo.Guestroom;

@Component(value="guestroomDao") 
public class GuestroomDAOImpl implements GuestroomDAO{
	private Session s;
	
	//添加客房
	@Override
	public boolean addGuestroom(Guestroom guestroom) {
		s = HButil.getSession();
		Transaction tr=s.beginTransaction();
		try {
			s.save(guestroom);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("addGuestroom is error!");
		}finally{
			HButil.closeSession(s);
		}
		return false;
	}

	//删除客房
	@Override
	public boolean deleteGuestroom(Guestroom guestroom) {
		Guestroom g = this.findTheRoom(guestroom);//调用查找方法
		s = HButil.getSession();
		Transaction tr = s.beginTransaction();
		try {
			s.delete(g);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
			System.out.println("deleteGuestroom is error!");
		}finally{
			HButil.closeSession(s);
		}
		return false;
	}

	//查找（根据房间号）
	@Override
	public List<Guestroom> findRoomByRoomno(int roomno) {
		Criteria  c=HButil.getSession().createCriteria(Guestroom.class);
		c.add(Expression.eq("roomno", roomno));
		return c.list();
	}

	//查找（根据客房类型）
	@Override
	public List<Guestroom> findRoomByRoomtype(String roomtype) {
		Criteria  c=HButil.getSession().createCriteria(Guestroom.class);
		c.add(Expression.eq("roomtype", roomtype));
		return c.list();
	}

	//查找（根据客房状态）
	@Override
	public List<Guestroom> findRoomByRoomstatus(int roomstatus) {
		Criteria  c=HButil.getSession().createCriteria(Guestroom.class);
		c.add(Expression.eq("roomstatus", roomstatus));
		return c.list();
	}

	//查找（根据客房价格）
	@Override
	public List<Guestroom> findRoomByRoomprice(float roomprice) {
		Criteria c = HButil.getSession().createCriteria(Guestroom.class);
		c.add(Expression.eq("roomprice", roomprice));
		return c.list();
	}
	
	//查看所有
	@Override
	public List<Guestroom> listAllRooms() {
		Query q = HButil.getSession().createQuery("from Guestroom");
		return q.list();
	}

	//修改客房
	@Override
	public boolean updateGuestroom(Guestroom guestroom) {
		try{
			Guestroom g = this.findTheRoomByRoomid(guestroom);
			int id = g.getRoomid();
			s=HButil.getSession();
			Transaction tr = s.beginTransaction();
			if(g!=null){
				g = guestroom;
				g.setRoomid(id);
				
				s.update(g);
				System.out.println("111111111111");
				tr.commit();
				return true;
			}else{
				System.out.println("数据不存在");
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("updateGuestroom is error!");
			return false;
		}finally{
			HButil.closeSession(s);
		}
	}
	//查找通过房号
	@Override
	public Guestroom findTheRoom(Guestroom guestroom) {
		Guestroom g = null;
		try {
			s =HButil.getSession();
			Transaction tr = s.beginTransaction();
			String hql = "from Guestroom where roomno=?";
			Query q = (Query) s.createQuery(hql);
			q.setInteger(0, guestroom.getRoomno());
			g = (Guestroom) q.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("findGuestroom is error!");
		} finally{
			HButil.closeSession(s);
		}
		return g;
	}

	//查找通过客房id
	@Override
	public Guestroom findTheRoomByRoomid(Guestroom guestroom) {
		Guestroom g = null;
		try {
			s =HButil.getSession();
			Transaction tr = s.beginTransaction();
			String hql = "from Guestroom where roomid=?";
			Query q = (Query) s.createQuery(hql);
			q.setInteger(0, guestroom.getRoomid());
			g = (Guestroom) q.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("findGuestroom is error!");
		} finally{
			HButil.closeSession(s);
		}
		return g;
	}
	
	//分页（每页显示多少条数据）
	@Override
	public List<Guestroom> getRoomsByPage(int page, int count) {
		Criteria c = HButil.getSession().createCriteria(Guestroom.class);
		c.setFirstResult((page-1)*count);
		c.setMaxResults(count);
		return c.list();
	}

	//分页（获取数据总条数）
	@Override
	public Integer getAllRoomsCount() {
		Criteria c = HButil.getSession().createCriteria(Guestroom.class);
		c.setProjection(Projections.count("roomid"));
		return Integer.parseInt(c.uniqueResult().toString());
	}

	@Override
	public Guestroom findRoom(String roomno) {
		Guestroom g = null;
		try {
			s =HButil.getSession();
			Transaction tr = s.beginTransaction();
			String hql = "from Guestroom where roomno=?";
			Query q = (Query) s.createQuery(hql);
			q.setString(0, roomno);
			g = (Guestroom) q.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("findGuestroom is error!");
		} finally{
			HButil.closeSession(s);
		}
		return g;

	}

}
