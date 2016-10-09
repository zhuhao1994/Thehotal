package com.hotel.daoImpl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.hotel.HButil.HButil;
import com.hotel.dao.PageinationDAO;
import com.hotel.pojo.Pager;
@Component(value="pageNext")
public class PageinationDAOImpl implements PageinationDAO {

	@Override
	public Pager pagerff(Pager p, Map<String, Object> pram) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HButil.getSession();
			tx = session.beginTransaction();
			String hql = p.getHql();// 获取查询语句
			Query query = session.createQuery(hql).setCacheable(true);
			// 设置参数
			if (pram != null)
				query.setProperties(pram);
			// 查询具体数据
			int count = query.list().size();
			p.setRowsTotal(count);
			int nowPage = 1;
			if (p.getPage() > 0) {
				nowPage = p.getPage();
			}
			// 指定从那个对象开始查询，参数的索引位置是从0开始的，
			query.setFirstResult((p.getPage() - 1) * p.getRows());
			// 分页时，一次最多产寻的对象数
			query.setMaxResults(p.getRows());
			List<?> list1 = query.list();
			p.setList(list1);
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return p;
	}

}
