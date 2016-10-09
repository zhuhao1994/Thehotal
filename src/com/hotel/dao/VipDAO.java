package com.hotel.dao;

import java.util.List;

import com.hotel.pojo.Vip;

public interface VipDAO {
//	更新会员信息
	public boolean updateVIP(Vip vip);
//	列出所有会员信息
	public List<Vip> listAll();
//	列出单条信息
	public Vip findVIPByVid(Vip vip);
	
}
