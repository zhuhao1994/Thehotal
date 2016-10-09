package com.hotel.dao;

import java.util.Map;

import com.hotel.pojo.Pager;

public interface PageinationDAO {
	public Pager pagerff(Pager p, Map<String, Object> pram);
}
