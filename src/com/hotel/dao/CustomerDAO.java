package com.hotel.dao;


import java.util.List;

import com.hotel.pojo.Customer;



public interface CustomerDAO {
/*
 * 1.录入客户信息 （添加客户）
 * 2.查看客户信息（查找）
 * 
 * 	（1）查看所有客户
 * 	（2）根据客户身份证查找
 *  （3）根据入住状态查看
 *  （4）根据客户姓名（模糊查询）查找
 *  （5）根据客户id查找（单个用户）
 * 3.修改客户信息
 * 4.分页
 * （1）每页显示多少条数据
 * （2）获取数据总条数
 */
	//1.录入客户信息 （添加客户）
	public boolean addCustomer(Customer customer);
	
	// 2.查看客户信息（查找）
	//（1）查看所有客户
	public List<Customer> listAllCustomers();
	
	//（2）根据客户身份证查找
	public List<Customer> findCustomerByCardid(String cardid);
	
	//（3）根据入住状态查看
	public List<Customer> findCustomerByStatus(int status);
	
	//（4）根据客户姓名（模糊查询）查找
	public List<Customer> findCustomerVague(String cusname);
	
	//（5）根据客户id查找（单个用户）
	public Customer loadCustomerInfoByCusId(Customer customer);
	
	//3.修改客户信息
	public boolean updateCustomer(Customer customer);
		
		
	//4.分页
	//（1）每页显示多少条数据
	public List<Customer> getCustomersByPage(int page, int count);
	//（2）获取数据总条数
	public Integer getAllCustomersCount();
	
	/**************************新增**********************/
	
	public Customer findCustomerByTel(String custel);
	
	public Customer loadCustomer(Integer cusid);
	
	public boolean updateBalance(Double balance, String custel);

	
}
