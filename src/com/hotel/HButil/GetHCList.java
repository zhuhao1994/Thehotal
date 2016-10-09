package com.hotel.HButil;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.hotel.pojo.HcRecord;

public class GetHCList {
	public static List<HcRecord> getHcRecordList(List<?> list){
		List<HcRecord> hc = new ArrayList<HcRecord>();
		HcRecord h = null;
		for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
			Object[] x = (Object[]) iterator.next();
			h = new HcRecord();
			h.setDate((Date) x[0]);
			h.setItem(Integer.parseInt(x[1].toString()));
			h.setCusNum(Integer.parseInt(x[2].toString()));
			float f1=Float.parseFloat(x[3].toString());
			float f2=Float.parseFloat(x[4].toString());
			float num1=(float)(Math.round(f1*100)/100);//保留两位小数
			float num2=(float)(Math.round(f2*100)/100);
			h.setConsum(num1);
			h.setAllconsum(num2);
			hc.add(h);
		}
		return hc;
		
	}
}
