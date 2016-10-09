package com.hotel.dao;

import java.util.List;

import com.hotel.pojo.Logtable;
import com.hotel.pojo.Usertable;

public interface LogtableDAO {
	//1�������־
	//2��ɾ����־
	//3���޸���־
	
	public  boolean addLog(Logtable logtable);
	public boolean deleteLog(Integer logid);
	public boolean updateLog(Logtable logtable);
	public Logtable loadLog(Logtable logtable);
	public List<Logtable> listAllLog(Usertable usertable);

}
