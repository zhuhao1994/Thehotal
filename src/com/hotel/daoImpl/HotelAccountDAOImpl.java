package com.hotel.daoImpl;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.hotel.HButil.HButil;
import com.hotel.dao.HotelAccountDAO;
import com.hotel.pojo.Customer;
import com.hotel.pojo.Guestroom;
import com.hotel.pojo.Hotelaccount;
@Component(value="haDao")
public class HotelAccountDAOImpl implements HotelAccountDAO {
	@Override
	public int addhotelAccount(Hotelaccount ha) {
		Session sess = null;
		int rs = 0;
		try
		{
			sess = HButil.getSession();
			Transaction ts = sess.beginTransaction();
			Serializable ss = sess.save(ha);
			ts.commit();
			rs = ss.hashCode();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("addhotelAccount is error!");
		} finally {
			HButil.closeSession(sess);
		}
		return rs;
	}

	@Override
	public List<Hotelaccount> findAllHA() {
		Session sess = null;
		List<Hotelaccount> has = new ArrayList<Hotelaccount>();
		try {
			sess = HButil.getSession();
			String hql = "from Hotelaccount";
			Query query = (Query) sess.createQuery(hql);
			has = query.list();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("findAllHA is error!");
		} finally {
			HButil.closeSession(sess);
		}
		return has;
	}

	// �˷�����ѯ��ĳ��δ�˷��ķ���
	public Hotelaccount findtheCheckOut(Hotelaccount ha) {
		Session sess = null;
		Hotelaccount ha1 = new Hotelaccount();
		try {
			sess = HButil.getSession();
			String hql = "from Hotelaccount where roomid = ? and accuntstatus=0 ";
			Query query = (Query) sess.createQuery(hql);
			query.setInteger(0, ha.getGuestroom().getRoomid());
			ha1 = (Hotelaccount) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("findhotelAccount is error!");
		} finally {
			HButil.closeSession(sess);
		}
		return ha1;
	}

	// *******��չ��ѯ******
	public Guestroom fingHsID(int xx) {
		Session sess = null;
		Guestroom gs = null;
		try {
			sess = HButil.getSession();
			String hql = "from Guestroom where roomno = ?";
			Query query = (Query) sess.createQuery(hql);
			query.setInteger(0, xx);
			gs = (Guestroom) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("fingHsID is error!");
		} finally {
			HButil.closeSession(sess);
		}
		return gs;
	}

	// *********���Ŀͻ��Ϳͷ���״̬*
	public void updateCustemrStatus(Hotelaccount ha) {
		Session sess = null;
		try {
			sess = HButil.getSession();
			// 1.�Ȳ�ѯ���û� 2.��������
			Customer cs = new Customer();
			String hql1 = "from Customer where cusid = ?";
			Query query = sess.createQuery(hql1);
			query.setInteger(0, ha.getCustomer().getCusid());
			cs = (Customer) query.uniqueResult();
			cs.setStatus(0);
			Transaction ts = sess.beginTransaction();
			sess.update(cs);
			ts.commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("updateCustemrStatus is error!");
		} finally {
			HButil.closeSession(sess);
		}
	}

	@Override
	public void updateGustRoomStatus(Hotelaccount ha) {
		Session sess = null;
		try {
			sess = HButil.getSession();
			// 1.�Ȳ�ѯ���û� 2.��������
			Guestroom cs = new Guestroom();
			String hql1 = "from Guestroom where roomid = ?";
			Query query = sess.createQuery(hql1);
			query.setInteger(0, ha.getGuestroom().getRoomid());
			cs = (Guestroom) query.uniqueResult();
			cs.setRoomstatus(0);
			Transaction ts = sess.beginTransaction();
			sess.update(cs);
			ts.commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("updateGustRoomStatus is error!");
		} finally {
			HButil.closeSession(sess);
		}
	}

	// �˷���1.��ѯ�����ţ���ĳ��δ�˷����˵� 2.�����˵�--����������� �� �ܼ�3.ͬʱ������ס�˵�״̬�ͷ����״̬

	// ****ha��װ���з��žͿ����ˣ�ʣ�µ����ݲ�ѯ����*******
	public void updatehotelAccount(Hotelaccount ha, float goodsConsum) {
		Session sess = null;
		try {
			Guestroom gs = new Guestroom();
			gs.setRoomid(this.fingHsID(ha.getGuestroom().getRoomno()).getRoomid());
			ha.setGuestroom(gs);
			String remarkq = ha.getRemarks();
			Hotelaccount theHa = this.findtheCheckOut(ha);
			sess = HButil.getSession();
			// 1.�������� * ���ۣ����ۣ� = ס������
			//���ݷ���:���ҳ��÷��䲢��ȡ�����÷��۵ĵ���
			float roomPrice  = this.fingHsByID(ha.getGuestroom().getRoomid()).getRoomprice();
			//�����ס������
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
			String time  = df.format(new Date()).toString();
			String fromTime = theHa.getCometime().toString();
			int liveDays = daysBetween(fromTime,time);
			System.out.println("ס��������"+liveDays);
			float roomConsume = roomPrice*liveDays;
			//���£�ס���۸������ѣ��˷�ʱ�䣬�˵�����״̬
			float allConsume = (roomConsume + goodsConsum)*theHa.getCustomer().getVip().getVdiscount();
			
			//���¿ͻ� �� �ͷ���״̬
			this.updateCustemrStatus(theHa);
			this.updateGustRoomStatus(theHa);
			
			//====���µ����ݣ��뿪��ʱ�䣬ס�����ѣ���Ʒ����
			Transaction ts = sess.beginTransaction();
			theHa.setLeavetime(new Date(System.currentTimeMillis()));
			theHa.setConsume(roomConsume);
			theHa.setAllconsume(allConsume);
			theHa.setAccuntstatus(1);
			theHa.setRemarks(remarkq);
			sess.update(theHa);
			
			ts.commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("updatehotelAccount �˷����̳������ˣ�");
		} finally {
			HButil.closeSession(sess);
		}
	}

	// ---�������ڲ�
    public static int daysBetween(String smdate,String bdate) throws ParseException{  
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(sdf.parse(smdate));    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(sdf.parse(bdate));    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));     
    }

	@Override
	public Guestroom fingHsByID(int xx) {
		Session sess = null;
		Guestroom gs = null;
		try {
			sess = HButil.getSession();
			String hql = "from Guestroom where roomid = ?";
			Query query = (Query) sess.createQuery(hql);
			query.setInteger(0, xx);
			gs = (Guestroom) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("fingHsByID is error!");
		} finally {
			HButil.closeSession(sess);
		}
		return gs;
	} 

}