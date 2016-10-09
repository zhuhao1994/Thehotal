package com.hotel.dao;

import java.util.List;

import com.hotel.pojo.Goods;
import com.hotel.pojo.Goodsconsume;
/**
 * 1.������Ѽ�¼�����Ѽ�¼������  ����ID+��ƷID+��Ʒ������
 * 2.�鿴���Ѽ�¼(���е����Ѽ�¼)
 * 3.������Ʒ���Ѽ�¼
 * -----������Ʒ���Ʋ�ѯ
 * -----�����̷���Ų�ѯ
 * @param gsc
 * @return
 */
public interface GoodsConsumeDAO {
	
	public boolean addGoodsconsume(Goodsconsume gsc);//������Ѽ�¼
	public List<Goodsconsume> listGoodsconsume();//�鿴���Ѽ�¼
	//������Ʒ���Ѽ�¼(������Ʒ����)
	public List<Goodsconsume> findGoodsconsumeByname(String goodsname);
			
	//���ݷ���Ų���
	public List<Goodsconsume> findGoodsconsumeByroomno(String roomno);
		

	
}
