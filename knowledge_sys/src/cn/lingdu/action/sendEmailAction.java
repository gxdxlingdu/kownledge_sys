package cn.lingdu.action;

import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;




import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class sendEmailAction extends ActionSupport implements SessionAware {
	//获得session的map对象
	private Map<String,Object> msession;
	@Override
	public void setSession(Map<String, Object> msession) {
		this.msession=msession;
	}
	//接收数据
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}



	public String sendEmail()  throws Exception{
		//0. 邮件参数
		Properties prop = new Properties();
		prop.put("mail.transport.protocol", "smtp");	// 指定协议
		prop.put("mail.smtp.host", "smtp.163.com");		// 主机   smtp.163.com
		prop.put("mail.smtp.port", 465);					// 端口
		prop.put("mail.smtp.auth", "true");				// 用户密码认证
		prop.put("mail.debug", "true");					// 调试模式
		
		//1.创建邮件会话
		Session session = Session.getDefaultInstance(prop);
		//2.创建邮件体对象
		MimeMessage message = new MimeMessage(session);
		//3.设置邮件体参数
		//3.1标题
		message.setSubject("知识库管理系统：");
		//3.2发送时间
		message.setSentDate(new Date());
		//3.3发送人
		message.setSender(new InternetAddress("gxu_lingdu@163.com"));
		//3.4接收人
		message.setRecipient(RecipientType.TO, new InternetAddress(this.getEmail()));
		//3.5内容
		Random r=new Random();
		int num=r.nextInt(8999)+1001;
		msession.put("vrify", num);
		System.out.println("验证码="+num);
		message.setText("验证码是："+num); //简单纯文本邮件
		message.saveChanges();//保持邮件（可选）
		
		//4.发送
		Transport trans = session.getTransport();
		System.out.println(trans);
		trans.connect("gxu_lingdu","a147852"); //账号 密码
		trans.sendMessage(message, message.getAllRecipients());
		trans.close();
		
		return null;
	}
}
