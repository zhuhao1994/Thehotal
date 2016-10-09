package com.hotel.HButil;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hotel.pojo.Pager;

public class PagerToJson {
	private static JSONArray  empfJs;
	private static JSONObject  hj;
	//usertable.userid,usertable.username,leavetime,count(hid),count(cusid),sum(consume),sum(allconsume)
	public static JSONArray PagerToJson(Pager p) throws JSONException{
		empfJs=new JSONArray();
		List<?> list=p.getList();
		//把分页信息加入里面hj
		hj=new JSONObject();
		hj.put("nowPage", p.getPage());// 当前页码
		hj.put("pageTotal", p.getPageTotal());//总页码
		hj.put("rows", p.getRows());//总条数
		hj.put("rowsTotal", p.getRowsTotal());//每页显示条数
		empfJs.put(hj);
		//把list数据迭代成json
		for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
			Object[] x = (Object[]) iterator.next();
			hj=new JSONObject();
			hj.put("userid", Integer.parseInt(x[0].toString()));//用户id
			hj.put("username", x[1].toString());//用户名称
			hj.put("date", (Date)x[2]);//统计日期
			hj.put("item", Integer.parseInt(x[3].toString()));//记录条数
			hj.put("cusNum", Integer.parseInt(x[4].toString()));//用户数量
			float f1=Float.parseFloat(x[5].toString());
			float f2=Float.parseFloat(x[6].toString());
			float num1=(float)(Math.round(f1*100)/100);//保留两位小数
			float num2=(float)(Math.round(f2*100)/100);
			hj.put("roomconsume", num1);//住房消费
			hj.put("allconsume", num2);//总消费
			empfJs.put(hj);
		}
		return empfJs;
	}
}
