package com.hotel.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.HbmBinder;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import com.hotel.HButil.HButil;
import com.hotel.dao.CustomerDAO;
import com.hotel.pojo.Customer;
@Component(value="customerDAO") 
public class CustomerDAOImpl  implements CustomerDAO{
	
	/*public CustomerDAOImpl( ) {
		System.out.println("customerDAO is start");
		
	}*/
	private Session s;
	//1.录锟斤拷突锟斤拷锟较�锟斤拷锟斤拷涌突锟斤拷锟�
	@Override
	public boolean addCustomer(Customer customer) {
		try {
			s=HButil.getSession();
			Transaction tr=s.beginTransaction();
			s.save(customer);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("addCustomer is error!");
		}finally{
			HButil.closeSession(s);
		}
		
		return false;
	}

//	锟斤拷1锟斤拷锟介看锟斤拷锟叫客伙拷
	@Override
	public List<Customer> listAllCustomers() {
		s=HButil.getSession();
		Query q=s.createQuery("from Customer");
		return q.list();
	}

//	锟斤拷2锟斤拷锟斤拷菘突锟斤拷锟斤拷证锟斤拷锟斤拷/****************淇敼****************/
	@Override
	public List<Customer> findCustomerByCardid(String cardid) {
		s=HButil.getSession();
		Criteria  c=s.createCriteria(Customer.class);
		c.add(Expression.eq("cardid", cardid));
		return c.list();
	}


//锟斤拷3锟斤拷锟斤拷锟斤拷锟阶∽刺拷榭�
	@Override
	public List<Customer> findCustomerByStatus(int status) {
		s=HButil.getSession();
		Criteria c=s.createCriteria(Customer.class);
		c.add(Expression.eq("status", status));
		return c.list();
		
	}
//	3.锟睫改客伙拷锟斤拷息
	@Override
	public boolean updateCustomer(Customer customer) {
		try {
			Customer cus=this.loadCustomerInfoByCusId(customer);
			int id=cus.getCusid();
			s=HButil.getSession();
			Transaction tr=s.beginTransaction();
			System.out.println(cus);
			if (cus!=null) {
				cus = customer;
				cus.setCusid(id);
				s.update(cus);
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

		

//	锟斤拷5锟斤拷锟斤拷菘突锟絠d锟斤拷锟揭ｏ拷锟斤拷锟斤拷锟矫伙拷锟斤拷	
		@Override
		public Customer loadCustomerInfoByCusId(Customer customer) {
			Customer cus=null;
			try {
				s=HButil.getSession();
				String hql="from Customer where cusid=?";
				Query query=(Query)s.createQuery(hql);
				query.setInteger(0, customer.getCusid());
				cus=(Customer)query.uniqueResult();
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("findUser is error!");
			}finally {
				HButil.closeSession(s);
			}
			return cus;
			
			
		}

//		4.锟斤拷页 * 锟斤拷1锟斤拷每页锟斤拷示锟斤拷锟斤拷锟斤拷锟斤拷锟�
		
		@Override
		public List<Customer> getCustomersByPage(int page, int count) {
			s=HButil.getSession();
			Criteria  c=s.createCriteria(Customer.class);
			c.setFirstResult((page-1)*count);
			c.setMaxResults(count);
			return c.list();
		}
//4.锟斤拷页 *锟斤拷2锟斤拷锟斤拷取锟斤拷锟斤拷锟斤拷锟斤拷锟�
		@Override
		public Integer getAllCustomersCount() {
			s=HButil.getSession();
			Criteria  c=s.createCriteria(Customer.class);
			c.setProjection(Projections.count("cusid"));
			return Integer.parseInt(c.uniqueResult().toString());
		}

		
//		*  锟斤拷4锟斤拷锟斤拷菘突锟斤拷锟斤拷锟侥ｏ拷锟斤拷询锟斤拷锟斤拷锟斤拷
		@Override
		public List<Customer> findCustomerVague(String cusname) {
			s=HButil.getSession();
			String hql="from Customer as customer where customer.cusname like:cusname";
			Query query=s.createQuery(hql);
			query.setString("cusname", "%" + cusname + "%");
			List<Customer> customers=query.list();
			return customers;
			
		}

		/**********************************新增*******************************/
		@Override
		public Customer findCustomerByTel(String custel) {
			s=HButil.getSession();
			Criteria  c=s.createCriteria(Customer.class);
			c.add(Expression.eq("custel", custel));
			return (Customer) c.uniqueResult();
		}

		@Override
		public Customer loadCustomer(Integer cusid) {
			s=HButil.getSession();
			Criteria c=s.createCriteria(Customer.class);
			c.add(Expression.eq("cusid", cusid));
			
			return null;
		}
		
		//********新增*******
		//修改余额
				@Override
				public boolean updateBalance(Double balance,String custel) {
					Customer customer=this.findCustomerByTel(custel);
					Double ba=customer.getBalance();
					s=HButil.getSession();
					Transaction tr=s.beginTransaction();
					String hql="update Customer cus set cus.balance=? where  cus.custel=?";
					Query query=(Query)s.createQuery(hql);
					query.setDouble(0, ba+balance);
					query.setString(1, custel);
					int s= query.executeUpdate();
					tr.commit();
					
					if(s==0){
						return false;
					}else{
						return true;
					}
					
				}

	



		

}
