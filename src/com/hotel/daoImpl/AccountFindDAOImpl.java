package com.hotel.daoImpl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.hotel.HButil.GetHCList;
import com.hotel.HButil.HButil;
import com.hotel.dao.AccountFindDAO;
import com.hotel.pojo.HcRecord;
import com.hotel.pojo.Hotelaccount;

@Component(value = "accountFindDAO")
public class AccountFindDAOImpl implements AccountFindDAO {
	private List<Hotelaccount> halist = null;
	//公用的字符串
	private static String str="select leavetime,count(hid),count(cusid),sum(consume),sum(allconsume) from Hotelaccount where accuntstatus=1";
	@Override
	public List<Hotelaccount> findAccountByCustomer(String cardid) {
		Query q = HButil.getSession().createQuery("from Hotelaccount where customer.cardid=?");
		q.setString(0, cardid);
		halist = q.list();
		return halist;
	}     

	@Override
	public List<Hotelaccount> findAccountByMonth(String year, String month) {
		String l = year + "-" + month + "%";
		Query q = HButil.getSession().createQuery("from Hotelaccount where accuntstatus=1 and leavetime like ?");
		q.setString(0, l);
		halist = q.list();
		return halist;
	}

	@Override
	public List<HcRecord> findAccountByYear(String year) {
		String hq = str+"and year(leavetime)=? group by Month(leavetime)";
		Query q = HButil.getSession().createQuery(hq);
		q.setString(0, year);
		List<HcRecord> hc = GetHCList.getHcRecordList(q.list());
		/*
		 * List<HcRecord> hc = new ArrayList<HcRecord>(); HcRecord h = null;
		 * List xx = q.list(); for (Iterator iterator = xx.iterator();
		 * iterator.hasNext();) { Object[] x = (Object[]) iterator.next(); h =
		 * new HcRecord(); h.setDate((Date) x[0]);
		 * h.setItem(Integer.parseInt(x[1].toString()));
		 * h.setCusNum(Integer.parseInt(x[2].toString())); float
		 * f1=Float.parseFloat(x[3].toString()); float
		 * f2=Float.parseFloat(x[4].toString()); float
		 * num1=(float)(Math.round(f1*100)/100);//保留两位小数 float
		 * num2=(float)(Math.round(f2*100)/100); h.setConsum(num1);
		 * h.setAllconsum(num2); hc.add(h); }
		 * System.out.println(hc.get(0).getDate());
		 */
		return hc;
	}

	@Override
	public List<HcRecord> countAll() {
		String hq = str+"group by year(leavetime)";
		Query q = HButil.getSession().createQuery(hq);
		List<HcRecord> hc = GetHCList.getHcRecordList(q.list());
		return hc;
	}

	@Override
	public List<HcRecord> countByMOnthOfYear(String year) {
		String hq = str+"and year(leavetime)=:year group by Month(leavetime)";
		Query q = HButil.getSession().createQuery(hq);
		q.setParameter("year", Integer.parseInt(year));
		List<HcRecord> hc = GetHCList.getHcRecordList(q.list());
		return hc;
	}

	@Override
	public List<HcRecord> countByDayOfMonth(String year, String month) {
		String hq = str+"and year(leavetime)=:year and Month(leavetime)=:month group by Day(leavetime)";
		Query q = HButil.getSession().createQuery(hq);
		q.setParameter("year", Integer.parseInt(year));
		q.setParameter("month", Integer.parseInt(month));
		List<HcRecord> hc = GetHCList.getHcRecordList(q.list());
		return hc;
	}

	@Override
	public List<HcRecord> countByOneDay(String year, String month, String day) {
		String hq = str+"and year(leavetime)=:year and Month(leavetime)=:month and Day(leavetime)=:day group by Day(leavetime)";
		Query q = HButil.getSession().createQuery(hq);
		q.setParameter("year", Integer.parseInt(year));
		q.setParameter("month", Integer.parseInt(month));
		q.setParameter("day", Integer.parseInt(day));
		List<HcRecord> hc = GetHCList.getHcRecordList(q.list());//
		return hc;
	}

	
	//员工业绩
	@Override
	public List<HcRecord> empPf(String username) {
		String hq = str+" and usertable.username=:username group by year(leavetime)";
		Query q = HButil.getSession().createQuery(hq);
		q.setParameter("username", username);
		List<HcRecord> hc = GetHCList.getHcRecordList(q.list());
		return hc;
	}

	@Override
	public List<HcRecord> empPf(String username, String year) {
		String hq = str+" and usertable.username=:username and year(leavetime)=:year group by Month(leavetime)";
		Query q = HButil.getSession().createQuery(hq);
		q.setParameter("username", username);
		q.setParameter("year", Integer.parseInt(year));
		List<HcRecord> hc = GetHCList.getHcRecordList(q.list());
		return hc;
	}

	@Override
	public List<HcRecord> empPf(String username, String year, String month) {
		String hq = str+"and usertable.username=:username and year(leavetime)=:year and Month(leavetime)=:month group by Day(leavetime)";
		Query q = HButil.getSession().createQuery(hq);
		q.setParameter("username", username);
		q.setParameter("year", Integer.parseInt(year));
		q.setParameter("month", Integer.parseInt(month));
		List<HcRecord> hc = GetHCList.getHcRecordList(q.list());
		return hc;
	}

	@Override
	public List<HcRecord> empPf(String username, String year, String month, String day) {
		String hq = str+"and usertable.username=:username and year(leavetime)=:year and Month(leavetime)=:month and Day(leavetime)=:day group by Day(leavetime)";
		Query q = HButil.getSession().createQuery(hq);
		q.setParameter("username", username);
		q.setParameter("year", Integer.parseInt(year));
		q.setParameter("month", Integer.parseInt(month));
		q.setParameter("day", Integer.parseInt(day));
		List<HcRecord> hc = GetHCList.getHcRecordList(q.list());
		return hc;
	}

	@Override
	public List<?> allEmpPf(String year, String month, String day) {
		String s="select usertable.userid,usertable.username,leavetime,count(hid),count(cusid),sum(consume),sum(allconsume) from Hotelaccount where accuntstatus=1";
		StringBuffer sb=new StringBuffer(s);
		if(year!=null){
			sb.append(" and year(leavetime)=:year ");
		}
		if(month!=null){
			sb.append(" and month(leavetime)=:month ");
		}
		if(day!=null){
			sb.append(" and day(leavetime)=:day ");
		}
		sb.append(" group by usertable.userid");
		Query q = HButil.getSession().createQuery(sb.toString());
		if(year!=null){
			q.setParameter("year", Integer.parseInt(year));
		}
		if(month!=null){
			q.setParameter("month", Integer.parseInt(month));
		}
		if(day!=null){
			q.setParameter("day", Integer.parseInt(day));
		}
		List<?> list=q.list();
		if(list.size()<=0){
			return null;
		}
		return list;
	}
//test
	public List<HcRecord> testObject() {
		String hq = "select new HcRecord(h.leavetime,count(h.hid),count(h.customer.cusid),sum(h.consume),sum(h.allconsume)) from Hotelaccount as h where h.accuntstatus=1 group by year(h.leavetime)";
		Query q = HButil.getSession().createQuery(hq);
		List<HcRecord> hc = GetHCList.getHcRecordList(q.list());
		return hc;
	}
	

}
