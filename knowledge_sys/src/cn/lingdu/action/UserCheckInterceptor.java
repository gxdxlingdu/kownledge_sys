package cn.lingdu.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

//MethodFilterInterceptor���������ã�Ϊ AbstractInterceptor������
public class UserCheckInterceptor extends MethodFilterInterceptor{


	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// ��ȡActionContext����
				ActionContext ac = invocation.getInvocationContext();
				
				// ��ȡaction�Ĵ������
				ActionProxy proxy = invocation.getProxy();
				// ��ȡ��ǰִ�еķ�����
				String methodName = proxy.getMethod();
				 // �ж�
				 if (!"login".equals(methodName)) {
					 // �Ȼ�ȡ��ǰ��½���û�
					 Object obj = ac.getSession().get("userInfo");
					 if (obj == null) {
						 // û�е�½
						 return "input";
					 } else {
						 // ��ǰ�û��е�½
						 return invocation.invoke();
					 }
				 } else {
					 // ˵����ǰ�û����ڵ�½
					 return invocation.invoke();
				 }
	}

}
