package com.hotel.HButil;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.hotel.pojo.HcRecord;

public class ExcelHP {
	//title[]{"Ա��",��","��","��","�ͻ�����","ס������","��Ʒ����","�ϼ�"};//cutdateNum=1 ÿ�� 2����ÿ�� 3���������ÿ��
	public static  boolean  listToExcel(List<HcRecord> list,String title[],String excelname ,int cutdateNum,String username,String path){

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
        if(cutdateNum==3){
        	// ���岽��д��ʵ������ ʵ��Ӧ������Щ���ݴ����ݿ�õ���
        	for (int i = 0; i < list.size(); i++) {
        		row = sheet.createRow((int) i + 1);
        		row.createCell((short) 0).setCellValue(username);
        		row.createCell((short) 1).setCellValue((double) list.get(i).getDate().getYear()+1900);
        		row.createCell((short) 2).setCellValue((double) list.get(i).getDate().getMonth()+1);
        		row.createCell((short) 3).setCellValue((double) list.get(i).getDate().getDate());
        		row.createCell((short) 4).setCellValue((double) list.get(i).getCusNum());
        		row.createCell((short) 5).setCellValue((double) list.get(i).getConsum());
        		row.createCell((short) 6).setCellValue((double) list.get(i).getAllconsum()-list.get(i).getConsum());
        		row.createCell((short) 7).setCellValue((double) list.get(i).getAllconsum());
        	}
        }else if(cutdateNum==2){
        	for (int i = 0; i < list.size(); i++) {
        		row = sheet.createRow((int) i + 1);
        		row.createCell((short) 0).setCellValue(username);
        		row.createCell((short) 1).setCellValue((double) list.get(i).getDate().getYear()+1900);
        		row.createCell((short) 2).setCellValue((double) list.get(i).getDate().getMonth()+1);
        		row.createCell((short) 3).setCellValue((double) list.get(i).getCusNum());
        		row.createCell((short) 4).setCellValue((double) list.get(i).getConsum());
        		row.createCell((short) 5).setCellValue((double) list.get(i).getAllconsum()-list.get(i).getConsum());
        		row.createCell((short) 6).setCellValue((double) list.get(i).getAllconsum());
        	}
        }else if(cutdateNum==1){
        	for (int i = 0; i < list.size(); i++) {
        		row = sheet.createRow((int) i + 1);
        		row.createCell((short) 0).setCellValue(username);
        		row.createCell((short) 1).setCellValue((double) list.get(i).getDate().getYear()+1900);
        		row.createCell((short) 2).setCellValue((double) list.get(i).getCusNum());
        		row.createCell((short) 3).setCellValue((double) list.get(i).getConsum());
        		row.createCell((short) 4).setCellValue((double) list.get(i).getAllconsum()-list.get(i).getConsum());
        		row.createCell((short) 5).setCellValue((double) list.get(i).getAllconsum());
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
	public static void main(String[] args) {
		String title[]={"Ա��","��","��","��","�ͻ�����","ס������","��Ʒ����","�ϼ�"};
		ArrayList<HcRecord> list=new ArrayList<HcRecord>();
		HcRecord h=new HcRecord();
		h.setDate(new Date());
		h.setItem(10);
		h.setCusNum(190);
		h.setConsum((float) 1.0);
		h.setAllconsum((float) 2.0);
		list.add(h);
		listToExcel(list, title, "����", 3, "qwe","");
	}
}
