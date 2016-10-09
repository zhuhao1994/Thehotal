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
import com.hotel.dao.GoodsConsumeDAO;
import com.hotel.pojo.Goods;
import com.hotel.pojo.Goodsconsume;
@Component(value="goodsConsumeDao") 
public class GoodsConsumeDAOImpl  implements GoodsConsumeDAO{
	private Session sess=null;
	
	//������Ѽ�¼������һ������
	public boolean addGoodsconsume(Goodsconsume gsc) {
		try {
			sess = HButil.getSession();
			Transaction ts = sess.beginTransaction();
			sess.save(gsc);
			ts.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("addGoodsconsume is error!");
		}finally {
			HButil.closeSession(sess);
		}
		return false;
	}
	
	//��ѯ���е����Ѽ�¼�������Ѽ�¼��Goodsconsume���У�
	public List<Goodsconsume> listGoodsconsume() {
		List<Goodsconsume> gdc = new ArrayList<Goodsconsume>();
		try {
			sess = HButil.getSession();
			Query query = sess.createQuery("from Goodsconsume ");
			/*query.setFirstResult(0);
			query.setMaxResults(4);*/
			gdc=query.list();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("listGoodsconsume is error!");
		}finally {
			HButil.closeSession(sess);
		}
		return gdc;

	}

	//��ѯ���Ѽ�¼��������Ʒ���Ʋ�ѯ��
	@Override
	public List<Goodsconsume> findGoodsconsumeByname(String goodsname) {
		List<Goodsconsume> gdc = new ArrayList<Goodsconsume>();
		try{  
			sess = HButil.getSession();
	        Transaction tx=sess.beginTransaction();  
	        System.out.println("��ʼ��ѯ�ˣ�");
	        String hql="from Goodsconsume where goods.goodsname=?";
	        Query query = sess.createQuery(hql);
	        query.setString(0, goodsname);
	        gdc=query.list();
	        tx.commit();              
	    }catch(Exception e){  
	        e.printStackTrace();
	        System.out.println("��ѯ�����ˣ�");
	        }
		return gdc;
	}

	//���ݷ���Ų�ѯ
	@Override
	public List<Goodsconsume> findGoodsconsumeByroomno(String roomno) {
		List<Goodsconsume> gdc = new ArrayList<Goodsconsume>();
		try{  
			sess = HButil.getSession();
	        Transaction tx=sess.beginTransaction();  
	        System.out.println("��ʼ��ѯ�ˣ�");
	        String hql="from Goodsconsume where guestroom.roomno=?";  
	        Query query = sess.createQuery(hql);
	        query.setString(0, roomno);
	        gdc=query.list();
	        tx.commit();              
	    }catch(Exception e){  
	        e.printStackTrace();
	        System.out.println("��ѯ�����ˣ�");
	        }
		return gdc;
	}


}
