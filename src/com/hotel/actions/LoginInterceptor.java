package com.hotel.actions;
import java.util.Map;
import java.util.Set;

import com.hotel.pojo.Usertable;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.TextParseUtil;  
 
 
public class LoginInterceptor extends AbstractInterceptor{  
  
    private static final long serialVersionUID = 1L;  
    private String excludeActionName;//�޳������ط���  
    private String loginUser;//�û�����session�д�ŵ�keyֵ  
      
    @Override  
    public String intercept(ActionInvocation invocation) throws Exception {  
        String actionName = invocation.getProxy().getActionName();//��ȡ��ǰ���ʵ�action����  
          
        Set<String> set = TextParseUtil.commaDelimitedStringToSet(excludeActionName);  
          
        if(set.contains(actionName)){  
            return invocation.invoke();  
        }else{  
            Map<String, Object> session = invocation.getInvocationContext().getSession();  
            Usertable employee = (Usertable) session.get(loginUser);  
            if(employee == null){  
                return "login";//û�е�¼����ת����¼ҳ  
            }else{  
                return invocation.invoke();  
            }  
        }  
    }  
      
      
    //get set  
    public String getExcludeActionName() {  
        return excludeActionName;  
    }  
    public void setExcludeActionName(String excludeActionName) {  
        this.excludeActionName = excludeActionName;  
    }


	public String getLoginUser() {
		return loginUser;
	}


	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}  
    
      
      
      
}  
