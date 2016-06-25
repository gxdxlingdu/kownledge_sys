package cn.lingdu.action;

import java.io.File;
import java.util.Date;
import java.util.Map;

import javax.ws.rs.core.Application;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.lingdu.entity.MyFile;
import cn.lingdu.service.IFileService;
import cn.lingdu.service.IUserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FileUploadAction extends ActionSupport implements SessionAware{
	//�õ�session��map����
	private Map<String,Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}	
	// ��Ӧ����<input type="file" name="upload">
	private File[] upload; //�ļ�����
	// �ļ���
	private String[] uploadFileName;
	// �ļ�������(MIME)
	private String[] uploadContentType;
	public void setUpload(File[] upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContentType(String[] uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	
	//ע��Service һ��Ҫ�ýӿڽ���
	private IFileService myFileService;
	public void setMyFileService(IFileService myFileService) {
		this.myFileService = myFileService;
	}

	
	@Override
	public String execute() throws Exception {
		/******�õ��ϴ����ļ������д���******/
		// ���ļ��ϴ���uploadĿ¼
		// ��ȡ�ϴ���Ŀ¼·��
		String path=(String)session.get("currentpath");
		
		try {
			if(upload != null && upload.length>0){
				for(int i = 0; i<upload.length; i++){
					MyFile myFile = new MyFile();
					// ����Ŀ���ļ�����
					File destFile = new File(path,uploadFileName[i]);
					if(destFile.exists()){
						return SUCCESS;
					}else{
					File sourceFile = upload[i];
					// ���ϴ����ļ���������Ŀ���ļ���
					FileUtils.copyFile(sourceFile, destFile);
					//��װ����������ݿ�
					myFile.setFileName(uploadFileName[i]);
					myFile.setFilePath(path+"/"+uploadFileName[i]);
					myFile.setFileSize(upload[i].length());
					myFile.setFileType(uploadContentType[i]);
					myFile.setUser_id((Integer) session.get("id"));
					myFile.setTime(new Date());
					myFile.setVisits(0);
					myFileService.addFile(myFile);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
}

