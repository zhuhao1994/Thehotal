package com.hotel.dao;

import java.util.List;

import com.hotel.pojo.Vip;

public interface VipDAO {
//	���»�Ա��Ϣ
	public boolean updateVIP(Vip vip);
//	�г����л�Ա��Ϣ
	public List<Vip> listAll();
//	�г�������Ϣ
	public Vip findVIPByVid(Vip vip);
	
}
