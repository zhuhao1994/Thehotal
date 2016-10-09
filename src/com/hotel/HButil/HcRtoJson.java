package com.hotel.HButil;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hotel.pojo.HcRecord;

public class HcRtoJson {
	private static JSONArray  hcRJs;
	private static JSONObject  hj;
	private final static String title[]={"emp","year","month","day","cusNum","roomconsum","goodconsum","allcon"};
//	private final static String title[]={"年","月","日","客户数量","住房总消费","商品总消费","合计"};
	public static JSONArray hcRtoJson(String username,List<HcRecord> list,String year,String month,String day){
		hcRJs=new JSONArray();
		for (HcRecord h : list) {
			hj=new JSONObject();
			try {
				hj.put(title[0], username);
				hj.put(title[1], Integer.parseInt(year));
				hj.put(title[2], Integer.parseInt(month) );
				hj.put(title[3], Integer.parseInt(day));
				hj.put(title[4],h.getCusNum() );
				hj.put(title[5], h.getConsum());
				hj.put(title[6], h.getAllconsum()-h.getConsum());
				hj.put(title[7], h.getAllconsum());
				hcRJs.put(hj);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return hcRJs;
		
	}
	public static JSONArray hcRtoJson(String username,List<HcRecord> list,String year,String month){
		hcRJs=new JSONArray();
		for (HcRecord h : list) {
			hj=new JSONObject();
			try {
				hj.put(title[0], username);
				hj.put(title[1], Integer.parseInt(year));
				hj.put(title[2], Integer.parseInt(month));
				hj.put(title[3], h.getDate().getDate());
				hj.put(title[4],h.getCusNum() );
				hj.put(title[5], h.getConsum());
				hj.put(title[6], h.getAllconsum()-h.getConsum());
				hj.put(title[7], h.getAllconsum());
				hcRJs.put(hj);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return hcRJs;
		
	}
	public static JSONArray hcRtoJson(String username,List<HcRecord> list,String year){
		hcRJs=new JSONArray();
		for (HcRecord h : list) {
			hj=new JSONObject();
			try {
				hj.put(title[0], username);
				hj.put(title[1], Integer.parseInt(year));
				hj.put(title[2], h.getDate().getMonth()+1);
				hj.put(title[4],h.getCusNum() );
				hj.put(title[5], h.getConsum());
				hj.put(title[6], h.getAllconsum()-h.getConsum());
				hj.put(title[7], h.getAllconsum());
				hcRJs.put(hj);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return hcRJs;
		
	}
	public static JSONArray hcRtoJson(String username,List<HcRecord> list){
		hcRJs=new JSONArray();
		for (HcRecord h : list) {
			hj=new JSONObject();
			try {
				hj.put(title[0], username);
				hj.put(title[1], h.getDate().getYear()+1900);
				hj.put(title[4],h.getCusNum() );
				hj.put(title[5], h.getConsum());
				hj.put(title[6], h.getAllconsum()-h.getConsum());
				hj.put(title[7], h.getAllconsum());
				hcRJs.put(hj);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return hcRJs;
		
	}
}
