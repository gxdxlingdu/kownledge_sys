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
	//���session��map����
	private Map<String,Object> msession;
	@Override
	public void setSession(Map<String, Object> msession) {
		this.msession=msession;
	}
	//��������
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}



	public String sendEmail()  throws Exception{
		//0. �ʼ�����
		Properties prop = new Properties();
		prop.put("mail.transport.protocol", "smtp");	// ָ��Э��
		prop.put("mail.smtp.host", "smtp.163.com");		// ����   smtp.163.com
		prop.put("mail.smtp.port", 465);					// �˿�
		prop.put("mail.smtp.auth", "true");				// �û�������֤
		prop.put("mail.debug", "true");					// ����ģʽ
		
		//1.�����ʼ��Ự
		Session session = Session.getDefaultInstance(prop);
		//2.�����ʼ������
		MimeMessage message = new MimeMessage(session);
		//3.�����ʼ������
		//3.1����
		message.setSubject("֪ʶ�����ϵͳ��");
		//3.2����ʱ��
		message.setSentDate(new Date());
		//3.3������
		message.setSender(new InternetAddress("gxu_lingdu@163.com"));
		//3.4������
		message.setRecipient(RecipientType.TO, new InternetAddress(this.getEmail()));
		//3.5����
		Random r=new Random();
		int num=r.nextInt(8999)+1001;
		msession.put("vrify", num);
		System.out.println("��֤��="+num);
		message.setText("��֤���ǣ�"+num); //�򵥴��ı��ʼ�
		message.saveChanges();//�����ʼ�����ѡ��
		
		//4.����
		Transport trans = session.getTransport();
		System.out.println(trans);
		trans.connect("gxu_lingdu","a147852"); //�˺� ����
		trans.sendMessage(message, message.getAllRecipients());
		trans.close();
		
		return null;
	}
}
