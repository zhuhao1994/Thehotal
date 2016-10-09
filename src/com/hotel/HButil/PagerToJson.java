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
		//�ѷ�ҳ��Ϣ��������hj
		hj=new JSONObject();
		hj.put("nowPage", p.getPage());// ��ǰҳ��
		hj.put("pageTotal", p.getPageTotal());//��ҳ��
		hj.put("rows", p.getRows());//������
		hj.put("rowsTotal", p.getRowsTotal());//ÿҳ��ʾ����
		empfJs.put(hj);
		//��list���ݵ�����json
		for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
			Object[] x = (Object[]) iterator.next();
			hj=new JSONObject();
			hj.put("userid", Integer.parseInt(x[0].toString()));//�û�id
			hj.put("username", x[1].toString());//�û�����
			hj.put("date", (Date)x[2]);//ͳ������
			hj.put("item", Integer.parseInt(x[3].toString()));//��¼����
			hj.put("cusNum", Integer.parseInt(x[4].toString()));//�û�����
			float f1=Float.parseFloat(x[5].toString());
			float f2=Float.parseFloat(x[6].toString());
			float num1=(float)(Math.round(f1*100)/100);//������λС��
			float num2=(float)(Math.round(f2*100)/100);
			hj.put("roomconsume", num1);//ס������
			hj.put("allconsume", num2);//������
			empfJs.put(hj);
		}
		return empfJs;
	}
}
