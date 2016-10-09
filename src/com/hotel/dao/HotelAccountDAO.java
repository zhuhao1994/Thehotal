package com.hotel.dao;

import java.util.List;

import com.hotel.pojo.Guestroom;
import com.hotel.pojo.Hotelaccount;
public interface HotelAccountDAO {
	/*
	 * 1.��ס:����Ҫ��--->�ͻ� + �ͷ� + Ѻ�� + �������ˣ�ǰ̨����Ա
	 * */
	public int addhotelAccount(Hotelaccount ha);
	/* 
	 * 1.�Ƚ�����Ʒ���ѽ��ˣ�-->������Ʒ���ѹ������ɣ����ӣ���Ʒ���Ѽ�¼
	 * 2.��  ���ź��˷�״̬Ϊ0  ��addhotelAccount��ѯ�� ���� aa
	 * 3.���� aa �˷�״̬ 1�������ѣ���Ʒ���� + ס������--����ҳ���ȡ ��
	 * 4.ͬʱ����roomno��status�͸�customer��roomstatusΪ0��1.����roomid �� cusid��
	 * */
	public List<Hotelaccount> findAllHA(); 
	public Hotelaccount findtheCheckOut(Hotelaccount ha);
	public void updatehotelAccount(Hotelaccount ha, float goodsConsum);
	
	
	//***���ݷ���Ų�ѯ����id
	public Guestroom fingHsID(int xx);
	//***����id��ѯ����
	public Guestroom fingHsByID(int xx);
	//**�˷���������ס�˵�״̬ �� �ͷ���״̬*
	public void updateCustemrStatus(Hotelaccount ha);
	public void updateGustRoomStatus(Hotelaccount ha);
	
}
