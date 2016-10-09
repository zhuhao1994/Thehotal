package com.hotel.dao;

import java.util.List;

import com.hotel.pojo.Logtable;
import com.hotel.pojo.Usertable;

public interface LogtableDAO {
	//1、添加日志
	//2、删除日志
	//3、修改日志
	
	public  boolean addLog(Logtable logtable);
	public boolean deleteLog(Integer logid);
	public boolean updateLog(Logtable logtable);
	public Logtable loadLog(Logtable logtable);
	public List<Logtable> listAllLog(Usertable usertable);

}
