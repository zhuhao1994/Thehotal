package com.hotel.daoImpl;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.hotel.HButil.HButil;
import com.hotel.dao.VipDAO;
import com.hotel.pojo.Vip;
@Component(value="vipDao")
public class VipDAOImpl implements VipDAO{
	private Session s;

	@Override
	public boolean updateVIP(Vip vip) {
		
		try {
			Vip vip2=this.findVIPByVid(vip);
			
			System.out.println(vip2);
			int id=vip2.getVid();
			
			s=HButil.getSession();
			Transaction tr=s.beginTransaction();
			
			if (vip2!=null) {
				vip2 = vip;
				vip2.setVid(id);
				
				s.update(vip2);
				System.out.println("ehfuewhf");
				tr.commit();
				return true;
			}else {
				System.out.println("bucunzai");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("1111");
			return false;
		}finally {
			HButil.closeSession(s);
		}
		
	}

	@Override
	public List<Vip> listAll() {
		s=HButil.getSession();
		Query q=s.createQuery("from Customer");
		
		return q.list();
	}

	@Override
	public Vip findVIPByVid(Vip vip) {
		try{
		Vip vip2=null;
		s=HButil.getSession();
		String hql="from Vip where vid=?";
		Query query=(Query)s.createQuery(hql);
		query.setInteger(0, vip.getVid());
		vip2=(Vip)query.uniqueResult();
		
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("findUser is error!");
	}finally {
		HButil.closeSession(s);
	}
	return vip;
	}
	
}
