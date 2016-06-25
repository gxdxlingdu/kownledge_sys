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
	//得到session的map对象
	private Map<String,Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}	
	// 对应表单：<input type="file" name="upload">
	private File[] upload; //文件对象
	// 文件名
	private String[] uploadFileName;
	// 文件的类型(MIME)
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
	
	//注入Service 一定要用接口接收
	private IFileService myFileService;
	public void setMyFileService(IFileService myFileService) {
		this.myFileService = myFileService;
	}

	
	@Override
	public String execute() throws Exception {
		/******拿到上传的文件，进行处理******/
		// 把文件上传到upload目录
		// 获取上传的目录路径
		String path=(String)session.get("currentpath");
		
		try {
			if(upload != null && upload.length>0){
				for(int i = 0; i<upload.length; i++){
					MyFile myFile = new MyFile();
					// 创建目标文件对象
					File destFile = new File(path,uploadFileName[i]);
					if(destFile.exists()){
						return SUCCESS;
					}else{
					File sourceFile = upload[i];
					// 把上传的文件，拷贝到目标文件中
					FileUtils.copyFile(sourceFile, destFile);
					//封装对象存入数据库
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

