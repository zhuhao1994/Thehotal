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
	//title[]{"Ա��id","Ա��",��","��","��","�ͻ�����","ס������","��Ʒ����","�ϼ�"};//cutdateNum=1 ÿ�� 2����ÿ�� 3���������ÿ��
		public static  boolean  listToExcel(List<?> list,String title[],String excelname ,int cutdateNum,String path){

			//ɾ���ļ���Ŀ¼
			//deleteAll(new File(path));
			// ��һ��������һ��webbook����Ӧһ��Excel�ļ�  
			System.out.println("1111111111");
	        HSSFWorkbook wb = new HSSFWorkbook();  
	        // �ڶ�������webbook�����һ��sheet,��ӦExcel�ļ��е�sheet  
	        HSSFSheet sheet = wb.createSheet(excelname);  
	        // ����������sheet����ӱ�ͷ��0��,ע���ϰ汾poi��Excel����������������short  
	        HSSFRow row = sheet.createRow((int) 0);  
	        // ���Ĳ���������Ԫ�񣬲�����ֵ��ͷ ���ñ�ͷ����  
	        HSSFCellStyle style = wb.createCellStyle();  
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ����һ�����и�ʽ  
	        int titlelength=title.length;//���titlelengthδ
	        HSSFCell cell = row.createCell((short) 0); 
	        for (int i = 0; i < title.length; i++) {
	        	cell = row.createCell((short)i); 
	        	cell.setCellValue(title[i]);  
	        	cell.setCellStyle(style);  
	        }
	        if(cutdateNum==3){//����Ա��������
	        	// ���岽��д��ʵ������ ʵ��Ӧ������Щ���ݴ����ݿ�õ���
	        	int i=0;
	        	for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
	    			Object[] x = (Object[]) iterator.next();
	    			row = sheet.createRow((int) i + 1);
	    			System.out.println(x[0].toString());
	    			row.createCell((short) 0).setCellValue((double)Integer.parseInt(x[0].toString()));//�û�id
	    			row.createCell((short) 1).setCellValue(x[1].toString());//�û�����
	    			Date date=(Date)x[2];//����
	    			row.createCell((short) 2).setCellValue(date.getYear()+1900);//��
	    			row.createCell((short) 3).setCellValue(date.getMonth()+1);//��
	    			row.createCell((short) 4).setCellValue(date.getDate());//��
	    			row.createCell((short) 5).setCellValue((double)Integer.parseInt(x[4].toString()));//�û�����
	    			float f1=Float.parseFloat(x[5].toString());//ס������
	    			float f2=Float.parseFloat(x[6].toString());//��Ʒ����
	    			float num1=(float)(Math.round(f1*100)/100);//������λС��
	    			float num2=(float)(Math.round(f2*100)/100);
	    			row.createCell((short) 6).setCellValue((double) num1);//ס������
	    			row.createCell((short) 7).setCellValue((double) num2-num1);//��Ʒ����
	    			row.createCell((short) 8).setCellValue((double) num2);//��Ʒ����
	    			i++;
	        	}
	        }
	        if(cutdateNum==2){//����Ա������
	        	// ���岽��д��ʵ������ ʵ��Ӧ������Щ���ݴ����ݿ�õ���
	        	int i=0;
	        	for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
	    			Object[] x = (Object[]) iterator.next();
	    			row = sheet.createRow((int) i + 1);
	    			row.createCell((short) 0).setCellValue(x[0].toString());//�û�id
	    			row.createCell((short) 1).setCellValue(x[1].toString());//�û�����
	    			Date date=(Date)x[2];//����
	    			row.createCell((short) 2).setCellValue(date.getYear()+1900);//��
	    			row.createCell((short) 3).setCellValue(date.getMonth()+1);//��
	    			row.createCell((short) 4).setCellValue(x[4].toString());//�û�����
	    			float f1=Float.parseFloat(x[5].toString());//ס������
	    			float f2=Float.parseFloat(x[6].toString());//��Ʒ����
	    			float num1=(float)(Math.round(f1*100)/100);//������λС��
	    			float num2=(float)(Math.round(f2*100)/100);
	    			row.createCell((short) 5).setCellValue((double) num1);//ס������
	    			row.createCell((short) 6).setCellValue((double) num2-num1);//��Ʒ����
	    			row.createCell((short) 7).setCellValue((double) num2);//��Ʒ����
	    			i++;
	        	}
	        }
	        if(cutdateNum==1){//����Ա����
	        	// ���岽��д��ʵ������ ʵ��Ӧ������Щ���ݴ����ݿ�õ���
	        	System.out.println("cutdateNum==1");
	        	System.out.println(list.size());
	        	System.out.println(list.get(0));
	        	int i=0;
	        	for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
	    			Object[] x = (Object[]) iterator.next();
	    			row = sheet.createRow((int) i + 1);
	    			System.out.println(x[0]+""+x[1]+""+x[2]+""+x[3]);
	    			row.createCell((short) 0).setCellValue(x[0].toString());//�û�id
	    			row.createCell((short) 1).setCellValue(x[1].toString());//�û�����
	    			Date date=(Date)x[2];//����
	    			row.createCell((short) 2).setCellValue(date.getYear()+1900);//��
	    			row.createCell((short) 3).setCellValue(x[4].toString());//�û�����
	    			float f1=Float.parseFloat(x[5].toString());//ס������
	    			float f2=Float.parseFloat(x[6].toString());//��Ʒ����
	    			float num1=(float)(Math.round(f1*100)/100);//������λС��
	    			float num2=(float)(Math.round(f2*100)/100);
	    			row.createCell((short) 4).setCellValue((double) num1);//ס������
	    			row.createCell((short) 5).setCellValue((double) num2-num1);//��Ʒ����
	    			row.createCell((short) 6).setCellValue((double) num2);//��Ʒ����
	    			i++;
	        	}
	        }
	        if(cutdateNum==0){//����Ա��zong
	        	// ���岽��д��ʵ������ ʵ��Ӧ������Щ���ݴ����ݿ�õ���
	        	int i=0;
	        	for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
	    			Object[] x = (Object[]) iterator.next();
	    			row = sheet.createRow((int) i + 1);
	    			row.createCell((short) 0).setCellValue(x[0].toString());//�û�id
	    			row.createCell((short) 1).setCellValue(x[1].toString());//�û�����
	    			row.createCell((short) 2).setCellValue(x[4].toString());//�û�����
	    			float f1=Float.parseFloat(x[5].toString());//ס������
	    			float f2=Float.parseFloat(x[6].toString());//��Ʒ����
	    			float num1=(float)(Math.round(f1*100)/100);//������λС��
	    			float num2=(float)(Math.round(f2*100)/100);
	    			row.createCell((short) 3).setCellValue((double) num1);//ס������
	    			row.createCell((short) 4).setCellValue((double) num2-num1);//��Ʒ����
	    			row.createCell((short) 5).setCellValue((double) num2);//��Ʒ����
	    			i++;
	        	}
	        }
	        // �����������ļ��浽ָ��λ��  
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
		
		//���ĳĿ¼�µ������ļ�
		public static void deleteAll(File file) {

			if (file.isFile() || file.list().length == 0) {
				//file.delete();
			} else {
				File[] files = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					deleteAll(files[i]);
					files[i].delete();
				}

				if (file.exists()) {// ����ļ��������Ŀ¼ ����Ҫɾ��Ŀ¼
					//file.delete();
				}
			}
		}
	
}
