package cn.lingdu.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

//MethodFilterInterceptor（黑名单用）为 AbstractInterceptor的子类
public class UserCheckInterceptor extends MethodFilterInterceptor{


	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// 获取ActionContext对象
				ActionContext ac = invocation.getInvocationContext();
				
				// 获取action的代理对象
				ActionProxy proxy = invocation.getProxy();
				// 获取当前执行的方法名
				String methodName = proxy.getMethod();
				 // 判断
				 if (!"login".equals(methodName)) {
					 // 先获取当前登陆的用户
					 Object obj = ac.getSession().get("userInfo");
					 if (obj == null) {
						 // 没有登陆
						 return "input";
					 } else {
						 // 当前用户有登陆
						 return invocation.invoke();
					 }
				 } else {
					 // 说明当前用户正在登陆
					 return invocation.invoke();
				 }
	}

}
