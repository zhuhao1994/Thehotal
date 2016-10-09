package com.hotel.HButil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HButil {
	   private static SessionFactory factory;  
	        
	      static {  
	          try {  
	              //读取hibernate.cfg.xml文件  
	              Configuration cfg = new Configuration().configure();  
	                
	              //建立SessionFactory  
	              factory = cfg.buildSessionFactory();  
	          }catch(Exception e) {  
	              e.printStackTrace();  
	          }  
	      }  
	      /* 
	        *打开Session 
	      */  
	        
	      public static Session getSession() {  
	          return factory.openSession();  
	      }   
	      /* 
	        *关闭Session 
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
