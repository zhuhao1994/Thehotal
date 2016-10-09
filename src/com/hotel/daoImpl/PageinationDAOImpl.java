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
			String hql = p.getHql();// ��ȡ��ѯ���
			Query query = session.createQuery(hql).setCacheable(true);
			// ���ò���
			if (pram != null)
				query.setProperties(pram);
			// ��ѯ��������
			int count = query.list().size();
			p.setRowsTotal(count);
			int nowPage = 1;
			if (p.getPage() > 0) {
				nowPage = p.getPage();
			}
			// ָ�����Ǹ�����ʼ��ѯ������������λ���Ǵ�0��ʼ�ģ�
			query.setFirstResult((p.getPage() - 1) * p.getRows());
			// ��ҳʱ��һ������Ѱ�Ķ�����
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
