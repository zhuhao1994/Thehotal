package com.hotel.dao;

import java.util.List;

import com.hotel.pojo.Guestroom;


public interface GuestroomDAO {
	/*
	 * 1.���ӿͷ�������ͷ��������ԣ�
	 * 2.ɾ���ͷ�������ɾ����
	 * 3.�鿴�ͷ�
	 * 		1)���ݷ����
	 * 		2)���ݿͷ��۸�
	 *  	3)���ݿͷ�����
	 *  	4)���ݿͷ�״̬
	 *  	5)�鿴���пͷ�
	 * 4.�޸Ŀͷ�
	 * 5.��ҳ
	 */
	//���ӿͷ���Ϣ
	public boolean addGuestroom(Guestroom guestroom);
	//ɾ���ͷ���Ϣ
	public boolean deleteGuestroom(Guestroom guestroom);
	//����
	public Guestroom findTheRoom(Guestroom guestroom);//���ݷ��ţ����Ķ���
	public Guestroom findRoom(String roomno);//���ݷ��ţ�String���ͣ�

	public Guestroom findTheRoomByRoomid(Guestroom guestroom);//��������id
	//���ݷ���Ų���
	public List<Guestroom> findRoomByRoomno(int roomno);
	//���ݿͷ����Ͳ���
	public List<Guestroom> findRoomByRoomtype(String roomtype);
	//���ݿͷ�״̬����
	public List<Guestroom> findRoomByRoomstatus(int roomstatus);
	//���ݿͷ��۸����
	public List<Guestroom> findRoomByRoomprice(float roomprice);
	//�鿴���пͷ�
	public List<Guestroom> listAllRooms();
	//�޸Ŀͷ�
	public boolean updateGuestroom(Guestroom guestroom);
	//��ҳ
	public List<Guestroom> getRoomsByPage(int page, int count);
	public Integer getAllRoomsCount();
}
