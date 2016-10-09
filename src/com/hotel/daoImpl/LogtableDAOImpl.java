package com.hotel.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.springframework.stereotype.Component;

import com.hotel.HButil.HButil;
import com.hotel.dao.LogtableDAO;
import com.hotel.pojo.Customer;
import com.hotel.pojo.Logtable;
import com.hotel.pojo.Usertable;
@Component(value="logtableDao") 
public class LogtableDAOImpl implements LogtableDAO {
	private Session s;
	//1、添加日志
		//2、删除日志
		//3、修改日志
		//4.查询
	
	@Override
	public boolean addLog(Logtable logtable) {
		try {
			s=HButil.getSession();
			Transaction tr=s.beginTransaction();
			s.save(logtable);
			tr.commit();
			return true;
		} catch (Exception e) {
			System.out.println("addCustomer is error!");
			e.printStackTrace();
			return false;
		}finally {
			HButil.closeSession(s);
		}

	}

	@Override
	public boolean deleteLog(Integer logid) {
		String hql="delete Logtable l where l.logid=?";
		s=HButil.getSession();
		Transaction tr=s.beginTransaction();
		try {
			Query query=s.createQuery(hql);
			query.setInteger(0, logid);
			query.executeUpdate();
			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
			return false;
		}finally {
			HButil.closeSession(s);
		}
		
	}

	@Override
	public boolean updateLog(Logtable logtable) {
		try {
			Logtable log=this.loadLog(logtable);
			int id=log.getLogid();
			s=HButil.getSession();
			Transaction tr=s.beginTransaction();
			if(log!=null){
				log=logtable;
				log.setLogid(id);
				s.update(log);
				tr.commit();
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			System.out.println("updateLog is error!");
			e.printStackTrace();
			return false;
		}finally {
			
			HButil.closeSession(s);
		}
		
		
		
		
	
	}

	@Override
	public Logtable loadLog(Logtable logtable) {
		Logtable logtable2=null;
		try {
			s=HButil.getSession();
			String hql="from Logtable where logid=?";
			Query query=(Query)s.createQuery(hql);
			query.setInteger(0,logtable.getLogid());
			logtable2=(Logtable)query.uniqueResult();
		} catch (Exception e) {
			System.out.println("loadLog is error!");
			e.printStackTrace();
		}finally {
			HButil.closeSession(s);
		}
		return logtable2;
		
		
	}
	@Override
	public List<Logtable> listAllLog(Usertable usertable) {
		s=HButil.getSession();
		Criteria  c=s.createCriteria(Logtable.class);
		c.add(Expression.eq("usertable", usertable));
		return c.list();
	}
}
