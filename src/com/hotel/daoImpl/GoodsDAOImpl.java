package com.hotel.daoImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.springframework.stereotype.Component;

import com.hotel.HButil.HButil;
import com.hotel.dao.GoodsDAO;
import com.hotel.pojo.Goods;

@Component(value="goodsDao") 
public class GoodsDAOImpl implements GoodsDAO {
	private Session sess=null;
	
	//�����Ʒ
	public boolean addGoods(Goods goods) {
		try {
			sess = HButil.getSession();
			Transaction ts = sess.beginTransaction();//��������
			sess.save(goods);//������ݲ����浽���ݿ���
			//rs = (Integer) rs1 ;
			ts.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("addGoods is error!");
		}finally {
			HButil.closeSession(sess);
		}
		return false;
	}

//������Ʒ�����У�
	public List<Goods> listGoods() {
		List<Goods> good = new ArrayList<Goods>();
		try {
			sess = HButil.getSession();
			Query query = sess.createQuery("from Goods");//����sql���ִ��
			good=query.list();//����list��������
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("listGoods is error!");
		}finally {
			HButil.closeSession(sess);
		}
		return good;

	}
	//��ҳ��ѯ
	/*public List findByPage(int pageNo,int pageSize){
		sess = HButil.getSession();
		Query query = sess.createQuery("from Goods");
		query.setFirstResult((pageNo-1)*pageSize);
		//���ô���һ�м�¼��ʼ��ȡ
		query.setMaxResults(pageSize);
		//����һ�ζ�ȡ��������¼ pageNo��ʾ�ڼ�ҳ��pagesize��ʾÿҳ��ʾ��������¼
		return query.list();
	}*/
	

//�޸���Ʒ
public void updateGoods(Goods goods) {
	try {
		//�Ȳ�����Ʒ
		Goods gd=this.findGoods(goods);
		Integer id=gd.getGoodsid();
		sess =HButil.getSession();
		Transaction ts = sess.beginTransaction();
		System.out.println(goods);
		if(gd != null){
			gd=goods;
			//�ҵ���Ʒ�����޸�
			gd.setGoodsid(id);
			sess.update(gd);
			ts.commit();
			}
		else
			System.out.println("���ݲ����ڣ�");
		
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("���ݸ���ʧ�ܣ�");
		System.out.println("updateGoods is error!");
	}finally{
		HButil.closeSession(sess);
	}
}
//��ѯ��Ʒ������ID��ѯ��
	public List<Goods> findGoodsbyid(int goodsid) {
			sess = HButil.getSession();
			Criteria c=sess.createCriteria(Goods.class);
			c.add(Expression.eq("goodsid", goodsid));
			return c.list();
	}
	
	//��ѯ��Ʒ���������Ʋ�ѯ��
	public List<Goods> findGoodByName(String goodsname) {
		sess = HButil.getSession();
		Criteria c=sess.createCriteria(Goods.class);
		c.add(Expression.eq("goodsname", goodsname));
		return c.list();
	}

//��ѯ��Ʒ�����ݼ۸��ѯ�������Ʒ
/*@Override
public List<Goods> findGoodByPrice(String goodsprice) {
	sess = HButil.getSession();
	Criteria c=sess.createCriteria(Goods.class);
	c.add(Expression.eq("goodsname", goodsprice));
	return c.list();
	
	
}*/


//ɾ����Ʒ
@Override
public boolean deletGoods(Goods goodsid) {
		try {
			sess = HButil.getSession();
			Goods thegood = this.findGoods(goodsid);
			Transaction ts = sess.beginTransaction();
			sess.delete(thegood);
			ts.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("deletGoods is error!");
		}finally {
			HButil.closeSession(sess);
		}
		return false;
}

//������Ʒ���Ʋ�ѯ����Ʒ
@Override
public Goods findGoodByNamecid(String goodsname) {
	Goods pic = new Goods();
	try {
		sess = HButil.getSession();
		Transaction ts = sess.beginTransaction();
		String hql = "from Goods where goodsname =?";
		Query query = (Query) sess.createQuery(hql);
	    query.setString(0, goodsname);
	    pic = (Goods) query.uniqueResult();
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("findGoods is error!");
	}finally {
		HButil.closeSession(sess);
	}
	return pic;
}

//������Ʒid����
@Override
public Goods findGoods(Goods goodsid) {
	Goods pic = null;
	try {
		sess = HButil.getSession();
		Transaction ts = sess.beginTransaction();
		String hql = "from Goods where goodsid =?";
		Query query = (Query) sess.createQuery(hql);
	    query.setInteger(0,goodsid.getGoodsid());
	    pic = (Goods) query.uniqueResult();
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("findGoods is error!");
	}finally {
		HButil.closeSession(sess);
	}
	return pic;
}





}