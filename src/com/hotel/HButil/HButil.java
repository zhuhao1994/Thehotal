package com.hotel.HButil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HButil {
	   private static SessionFactory factory;  
	        
	      static {  
	          try {  
	              //��ȡhibernate.cfg.xml�ļ�  
	              Configuration cfg = new Configuration().configure();  
	                
	              //����SessionFactory  
	              factory = cfg.buildSessionFactory();  
	          }catch(Exception e) {  
	              e.printStackTrace();  
	          }  
	      }  
	      /* 
	        *��Session 
	      */  
	        
	      public static Session getSession() {  
	          return factory.openSession();  
	      }   
	      /* 
	        *�ر�Session 
	      */  
	        
	      public static void closeSession(Session session) {  
	          if (session != null) {  
	              if (session.isOpen()) {
	            	  session.clear();
	                  session.close();  
	              }  
	          }  
	      }  
	        
	      public static SessionFactory getSessionFactory() {  
	          return factory;  
	      }

}
