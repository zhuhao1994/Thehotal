package com.hotel.actions;
import java.util.Map;
import java.util.Set;

import com.hotel.pojo.Usertable;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.TextParseUtil;  
 
 
public class LoginInterceptor extends AbstractInterceptor{  
  
    private static final long serialVersionUID = 1L;  
    private String excludeActionName;//剔除的拦截方法  
    private String loginUser;//用户名在session中存放的key值  
      
    @Override  
    public String intercept(ActionInvocation invocation) throws Exception {  
        String actionName = invocation.getProxy().getActionName();//获取当前访问的action名字  
          
        Set<String> set = TextParseUtil.commaDelimitedStringToSet(excludeActionName);  
          
        if(set.contains(actionName)){  
            return invocation.invoke();  
        }else{  
            Map<String, Object> session = invocation.getInvocationContext().getSession();  
            Usertable employee = (Usertable) session.get(loginUser);  
            if(employee == null){  
                return "login";//没有登录，跳转到登录页  
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
