package com.hotel.HButil;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.hotel.pojo.HcRecord;

public class EmpfToExcel {
	//title[]{"员工id","员工",年","月","日","客户数量","住房消费","商品消费","合计"};//cutdateNum=1 每年 2该年每月 3，该年该月每日
		public static  boolean  listToExcel(List<?> list,String title[],String excelname ,int cutdateNum,String path){

			//删除文件和目录
			//deleteAll(new File(path));
			// 第一步，创建一个webbook，对应一个Excel文件  
			System.out.println("1111111111");
	        HSSFWorkbook wb = new HSSFWorkbook();  
	        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
	        HSSFSheet sheet = wb.createSheet(excelname);  
	        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
	        HSSFRow row = sheet.createRow((int) 0);  
	        // 第四步，创建单元格，并设置值表头 设置表头居中  
	        HSSFCellStyle style = wb.createCellStyle();  
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
	        int titlelength=title.length;//如果titlelength未
	        HSSFCell cell = row.createCell((short) 0); 
	        for (int i = 0; i < title.length; i++) {
	        	cell = row.createCell((short)i); 
	        	cell.setCellValue(title[i]);  
	        	cell.setCellStyle(style);  
	        }
	        if(cutdateNum==3){//所有员工年月日
	        	// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
	        	int i=0;
	        	for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
	    			Object[] x = (Object[]) iterator.next();
	    			row = sheet.createRow((int) i + 1);
	    			System.out.println(x[0].toString());
	    			row.createCell((short) 0).setCellValue((double)Integer.parseInt(x[0].toString()));//用户id
	    			row.createCell((short) 1).setCellValue(x[1].toString());//用户姓名
	    			Date date=(Date)x[2];//日期
	    			row.createCell((short) 2).setCellValue(date.getYear()+1900);//年
	    			row.createCell((short) 3).setCellValue(date.getMonth()+1);//月
	    			row.createCell((short) 4).setCellValue(date.getDate());//日
	    			row.createCell((short) 5).setCellValue((double)Integer.parseInt(x[4].toString()));//用户数量
	    			float f1=Float.parseFloat(x[5].toString());//住房消费
	    			float f2=Float.parseFloat(x[6].toString());//商品消费
	    			float num1=(float)(Math.round(f1*100)/100);//保留两位小数
	    			float num2=(float)(Math.round(f2*100)/100);
	    			row.createCell((short) 6).setCellValue((double) num1);//住房消费
	    			row.createCell((short) 7).setCellValue((double) num2-num1);//商品消费
	    			row.createCell((short) 8).setCellValue((double) num2);//商品消费
	    			i++;
	        	}
	        }
	        if(cutdateNum==2){//所有员工年月
	        	// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
	        	int i=0;
	        	for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
	    			Object[] x = (Object[]) iterator.next();
	    			row = sheet.createRow((int) i + 1);
	    			row.createCell((short) 0).setCellValue(x[0].toString());//用户id
	    			row.createCell((short) 1).setCellValue(x[1].toString());//用户姓名
	    			Date date=(Date)x[2];//日期
	    			row.createCell((short) 2).setCellValue(date.getYear()+1900);//年
	    			row.createCell((short) 3).setCellValue(date.getMonth()+1);//月
	    			row.createCell((short) 4).setCellValue(x[4].toString());//用户数量
	    			float f1=Float.parseFloat(x[5].toString());//住房消费
	    			float f2=Float.parseFloat(x[6].toString());//商品消费
	    			float num1=(float)(Math.round(f1*100)/100);//保留两位小数
	    			float num2=(float)(Math.round(f2*100)/100);
	    			row.createCell((short) 5).setCellValue((double) num1);//住房消费
	    			row.createCell((short) 6).setCellValue((double) num2-num1);//商品消费
	    			row.createCell((short) 7).setCellValue((double) num2);//商品消费
	    			i++;
	        	}
	        }
	        if(cutdateNum==1){//所有员工年
	        	// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
	        	System.out.println("cutdateNum==1");
	        	System.out.println(list.size());
	        	System.out.println(list.get(0));
	        	int i=0;
	        	for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
	    			Object[] x = (Object[]) iterator.next();
	    			row = sheet.createRow((int) i + 1);
	    			System.out.println(x[0]+""+x[1]+""+x[2]+""+x[3]);
	    			row.createCell((short) 0).setCellValue(x[0].toString());//用户id
	    			row.createCell((short) 1).setCellValue(x[1].toString());//用户姓名
	    			Date date=(Date)x[2];//日期
	    			row.createCell((short) 2).setCellValue(date.getYear()+1900);//年
	    			row.createCell((short) 3).setCellValue(x[4].toString());//用户数量
	    			float f1=Float.parseFloat(x[5].toString());//住房消费
	    			float f2=Float.parseFloat(x[6].toString());//商品消费
	    			float num1=(float)(Math.round(f1*100)/100);//保留两位小数
	    			float num2=(float)(Math.round(f2*100)/100);
	    			row.createCell((short) 4).setCellValue((double) num1);//住房消费
	    			row.createCell((short) 5).setCellValue((double) num2-num1);//商品消费
	    			row.createCell((short) 6).setCellValue((double) num2);//商品消费
	    			i++;
	        	}
	        }
	        if(cutdateNum==0){//所有员工zong
	        	// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
	        	int i=0;
	        	for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
	    			Object[] x = (Object[]) iterator.next();
	    			row = sheet.createRow((int) i + 1);
	    			row.createCell((short) 0).setCellValue(x[0].toString());//用户id
	    			row.createCell((short) 1).setCellValue(x[1].toString());//用户姓名
	    			row.createCell((short) 2).setCellValue(x[4].toString());//用户数量
	    			float f1=Float.parseFloat(x[5].toString());//住房消费
	    			float f2=Float.parseFloat(x[6].toString());//商品消费
	    			float num1=(float)(Math.round(f1*100)/100);//保留两位小数
	    			float num2=(float)(Math.round(f2*100)/100);
	    			row.createCell((short) 3).setCellValue((double) num1);//住房消费
	    			row.createCell((short) 4).setCellValue((double) num2-num1);//商品消费
	    			row.createCell((short) 5).setCellValue((double) num2);//商品消费
	    			i++;
	        	}
	        }
	        // 第六步，将文件存到指定位置  
	        try  
	        {  File f=new File(path+"excel");
	        	if(!f.exists()){
	        		f.mkdirs(); 
	        	}
	            FileOutputStream fout = new FileOutputStream(new File(path+"/excel","tongji.xls")); 
	            wb.write(fout);
	            fout.flush();
	            fout.close();
	            return true;
	        }  
	        catch (Exception e)  
	        {  
	            e.printStackTrace();  
	            return false;
	        }  
		}
		
		//清空某目录下的所有文件
		public static void deleteAll(File file) {

			if (file.isFile() || file.list().length == 0) {
				//file.delete();
			} else {
				File[] files = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					deleteAll(files[i]);
					files[i].delete();
				}

				if (file.exists()) {// 如果文件本身就是目录 ，就要删除目录
					//file.delete();
				}
			}
		}
	
}
