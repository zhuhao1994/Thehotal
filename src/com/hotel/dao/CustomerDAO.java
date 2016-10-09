package com.hotel.dao;


import java.util.List;

import com.hotel.pojo.Customer;



public interface CustomerDAO {
/*
 * 1.¼��ͻ���Ϣ ����ӿͻ���
 * 2.�鿴�ͻ���Ϣ�����ң�
 * 
 * 	��1���鿴���пͻ�
 * 	��2�����ݿͻ����֤����
 *  ��3��������ס״̬�鿴
 *  ��4�����ݿͻ�������ģ����ѯ������
 *  ��5�����ݿͻ�id���ң������û���
 * 3.�޸Ŀͻ���Ϣ
 * 4.��ҳ
 * ��1��ÿҳ��ʾ����������
 * ��2����ȡ����������
 */
	//1.¼��ͻ���Ϣ ����ӿͻ���
	public boolean addCustomer(Customer customer);
	
	// 2.�鿴�ͻ���Ϣ�����ң�
	//��1���鿴���пͻ�
	public List<Customer> listAllCustomers();
	
	//��2�����ݿͻ����֤����
	public List<Customer> findCustomerByCardid(String cardid);
	
	//��3��������ס״̬�鿴
	public List<Customer> findCustomerByStatus(int status);
	
	//��4�����ݿͻ�������ģ����ѯ������
	public List<Customer> findCustomerVague(String cusname);
	
	//��5�����ݿͻ�id���ң������û���
	public Customer loadCustomerInfoByCusId(Customer customer);
	
	//3.�޸Ŀͻ���Ϣ
	public boolean updateCustomer(Customer customer);
		
		
	//4.��ҳ
	//��1��ÿҳ��ʾ����������
	public List<Customer> getCustomersByPage(int page, int count);
	//��2����ȡ����������
	public Integer getAllCustomersCount();
	
	/**************************����**********************/
	
	public Customer findCustomerByTel(String custel);
	
	public Customer loadCustomer(Integer cusid);
	
	public boolean updateBalance(Double balance, String custel);

	
}
