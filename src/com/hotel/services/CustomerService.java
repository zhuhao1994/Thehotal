package com.hotel.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.hotel.dao.CustomerDAO;
import com.hotel.dao.PageinationDAO;
import com.hotel.pojo.Customer;
import com.hotel.pojo.Pager;

@Component(value = "customerService")
public class CustomerService {
	private CustomerDAO customerDAO;
	private PageinationDAO pageNext;

	public PageinationDAO getPageNext() {
		return pageNext;
	}

	public void setPageNext(PageinationDAO pageNext) {
		System.out.println("set pageNext");
		this.pageNext = pageNext;
	}

	public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}

	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	public List<Customer> listAllCustomers() {
		List<Customer> customers = customerDAO.listAllCustomers();
		return customers;

	}

	public boolean addCustomer(Customer customer) {
		return customerDAO.addCustomer(customer);

	}

	public List<Customer> findCustomerByCardid(String cardid) {
		List<Customer> customers = customerDAO.findCustomerByCardid(cardid);
		return customers;

	}

	public Customer loadCustomerInfoByCusId(Customer customer) {
		Customer customer2 = customerDAO.loadCustomerInfoByCusId(customer);
		return customer2;
	}

	public Pager showPageHa(int nowPage) {
		Pager page = new Pager();
		page.setPage(nowPage);
		page.setRows(10);
		page.setHql("from  Customer");
		Pager newPage = pageNext.pagerff(page, null);
		return newPage;
	}

	public boolean updateCustomer(Customer customer) {
		return customerDAO.updateCustomer(customer);
	}

	// 分页查询
	public Pager findPager(int key, String hql, String keyContent, int tpage) {
		Pager page = new Pager();
		page.setPage(tpage);
		page.setRows(10);
		page.setHql(hql);
		Map<String, Object> param = new HashMap<String, Object>();
		if (key == 1)// 通过姓名
			param.put("cusname", keyContent);
		else if (key == 2)// 通过身份证号
			param.put("cardid", keyContent);
		else if (key == 3)// 通过入住状态
			param = null;
		else
			param = null;
		Pager newPage = pageNext.pagerff(page, param);
		return newPage;
	}

	// =======手机号========
	public Customer findCustomerByTel(String custel) {
		Customer customer = customerDAO.findCustomerByTel(custel);
		return customer;
	}

	
	// ========充值======
	public boolean updateBalance(Double balance, String custel) {
		return customerDAO.updateBalance(balance, custel);
	}


}
