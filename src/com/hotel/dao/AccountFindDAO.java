package com.hotel.dao;

import java.util.List;

import com.hotel.pojo.HcRecord;
import com.hotel.pojo.Hotelaccount;

public interface AccountFindDAO {
	// 1�����ͻ���ѯ������� ͨ���û����֤��cardid �� Hotelaccount.accuntstatus=0��δ���˵�
	// ҳ��Ҫ��ʾ���ܶ���Ϣ
	public List<Hotelaccount> findAccountByCustomer(String cardid);

	// 2,���²�ѯ������ĳ�����Ѽ�¼����������·�
	public List<Hotelaccount> findAccountByMonth(String year, String month);

	// 3,��ѯĳ�������·ݵ����Ѽ�¼�����������ݣ���2016
	public List<HcRecord> findAccountByYear(String year);

	// 4,--ͳ��˵����ݵļ�¼
	public List<HcRecord> countAll();
	// 5,--ͳ��ĳ��ÿ��ͳ�Ƽ�¼����12��Լ�ü�¼��
	public List<HcRecord> countByMOnthOfYear(String year);
	//6,--ͳ��ĳ��ĳ��ÿ��ͳ�ƽ��
	public List<HcRecord> countByDayOfMonth(String year, String month);
	//7,--ͳ��ĳ��
	public List<HcRecord> countByOneDay(String year, String month, String day);
	
	/*Ա��ҵ��*/
	//8��ĳԱ��ÿ��ҵ��
	public List<HcRecord> empPf(String username);
	//9��ĳԱ��ĳ��ҵ��
	public List<HcRecord> empPf(String username, String year);
	//10,ĳԱ��ĳ��ÿ��ҵ��
	public List<HcRecord> empPf(String username, String year, String month);
	//11��ĳԱ��ĳ��ĳ��ĳ��ҵ��
	public List<HcRecord> empPf(String username, String year, String month, String day);
	
	/*ͳ������Ա��ҵ��*/
	public List<?>  allEmpPf(String year, String month, String day);
	//month,day Ϊ��ͳ��ĳ������Ա����ҵ�� 
	//select userid,leavetime,count(hid),count(cusid),sum(consume),sum(allconsume) from hotelaccount where accuntstatus=1 and year(leavetime)='2016' group by userid;
	//

}
