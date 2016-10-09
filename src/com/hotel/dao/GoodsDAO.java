package com.hotel.dao;

import java.util.List;

import com.hotel.pojo.Customer;
import com.hotel.pojo.Goods;

public interface GoodsDAO {
	/*
	 * 1.�����Ʒ��������Ʒ�������ԣ�����+�۸�+���� ������ӣ�ID�Զ����ɣ���
	 * 2.������Ʒ�����Ը�����Ʒ��ID���ң�Ҳ���Ը�����Ʒ���Ʋ��ң�
	 * 3.������Ʒ�����Բ�ѯ������Ʒ��
	 * 4.ɾ����Ʒ���Ȳ�ѯ����Ʒ�Ƿ���ڣ����Ը�����Ʒ��IDɾ������Ʒ������֮ǰ���������Ѽ�¼���ڣ�
	 * 5.�޸���Ʒ���Ȳ�ѯ��Ʒ�Ƿ���ڣ����ڲſ��Զ���Ʒ�����޸ģ�
	 */
	
	//������Ʒ�������Ʒ��
	public boolean addGoods(Goods goods);
	
	//������Ʒ��������ƷID���ң�
	public Goods findGoods(Goods goodsid);
	
	//������Ʒ(������Ʒid)
	public List<Goods> findGoodsbyid(int goodsid);
	
	//������Ʒ���Ʋ���
	public List<Goods> findGoodByName(String goodsname);
	
	//������Ʒ���Ʋ����Ʒid
	public Goods findGoodByNamecid(String goodsname);
	
	/*//������Ʒ�۸����
	public List<Goods> findGoodByPrice(String goodsprice);*/
	
	//������Ʒ
	public List<Goods> listGoods();
	
	//ɾ����Ʒ(������Ʒidɾ��)
	public boolean deletGoods(Goods goodsid);
	
	//�޸���Ʒ
	public void updateGoods(Goods goods);
	
	//public List findByPage(int pageNo,int pageSize);
}
