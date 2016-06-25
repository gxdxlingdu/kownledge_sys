package cn.lingdu.action;
import java.io.File;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import cn.lingdu.dao.impl.UserDao;
import cn.lingdu.entity.User;
import cn.lingdu.service.IUserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
public class UserAction extends ActionSupport implements ModelDriven<User>,SessionAware{ 
	private Map<String,Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}	
	private String vrify;	
	public void setVrify(String vrify) {
		this.vrify = vrify;
	}
	private User user = new User();
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}	
	@Override
	public User getModel() {
		return user;
	}
	//注入Service 一定要用接口接收
	private IUserService userService;
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public String register(){
		User u=userService.findByEmail(user.getEmail());		
		if(u==null){
			if(session.containsValue(Integer.parseInt(vrify))){
				userService.register(user);
				user.setEmail("2");//注册成功
			}else{
				user.setEmail("1");//验证码错误
			}			
		}else{
			user.setEmail("0");//email已注册过
		}		
		return "register";
	}		
	public String login(){		
		User userInfo = userService.login(user);
		if(userInfo!=null){
			session.put("userInfo", userInfo);
			session.put("email", userInfo.getEmail());
			session.put("id",userInfo.getId());
			String path = ServletActionContext.getServletContext().getRealPath("/upload/"+userInfo.getEmail());
			File file=new File(path);
			if(!file.exists()){
				file.mkdirs();
			}
			session.put("currentpath", path);		
			user.setEmail("1");
		}else{
			user.setEmail("0");
		}
		return "login";
	}	
	
	public String upload_page(){
		return "ok";
	}
	public String login_home(){
		return "ok";
	}
}
