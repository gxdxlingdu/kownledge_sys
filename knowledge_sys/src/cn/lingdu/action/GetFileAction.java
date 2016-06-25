package cn.lingdu.action;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import com.mysql.jdbc.Util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

import cn.lingdu.entity.MyFile;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
public class GetFileAction extends ActionSupport implements SessionAware,ServletResponseAware,ServletRequestAware{
	private Map<String,Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}	
	private String filepath;
	private String foldername;
	private String fileName;
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void setFoldername(String foldername) {
		this.foldername = foldername;
	}
	private List<MyFile> myfiles;
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}	
	public List<MyFile> getmyfiles() {
		return myfiles;
	}
	
	HttpServletResponse response;
	@Override
	public void setServletResponse(HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		this.response = response;
		
	}
	HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request=request;
	}
	/**
	 * 显示文件
	 * @return
	 */	
	public String getHomeFiles() throws Exception{	
		
		String path=(String)session.get("currentpath");
		String projectName=(String)session.get("projectName");
		String str=projectName+"\\upload\\"+(String)session.get("email");
		str=str.substring(1);		
		if(fileName!=null){	
			path+=("/"+fileName);
			if((new File(path)).isDirectory()){				
				session.put("currentpath", path);
			}else{
				PrintWriter out=response.getWriter();
				out.print("这是一个文件");
				return null;
			}
			fileName=null;
		}	
		
		File file=new File(path);
		if(file.isDirectory()){
			try {
		    	File[] files=file.listFiles();
		    	this.myfiles=new ArrayList<MyFile>();
		        for(int i=0;i<files.length;i++){
		       		MyFile myfile=new MyFile();
		        		String fname=files[i].getName();
		        		String ftype;
		        		if(files[i].isDirectory()){
		        			 ftype="knowledge-sys-floder";
		        		}else{
		        			 ftype = fname.substring(fname.lastIndexOf(".")+1,fname.length());
		        		}		        		
		        		myfile.setFileName(fname);
		        		int num=files[i].getPath().indexOf(str);		        		
		        		myfile.setFilePath(files[i].getPath().substring(num+projectName.length()).replace("\\","/"));
		        				        		
		        		myfile.setFileType(ftype);		        		
		        		myfiles.add(myfile);
		        }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		return "homefiles";
	}
	
			
	/**
	 * 新建文件夹
	 */
	private List<Boolean> flag;
	public String createfolder(){	
		
		String currentpath=(String)session.get("currentpath");
		String path=currentpath+"/"+foldername;
		try {
			File file=new File(path);
			boolean bol=file.mkdir();
			flag= new ArrayList<Boolean>();
			flag.add(bol);		
		} catch (Exception e) {
			e.printStackTrace();			
		}		
		
		return "returnfolder";	
	}
	public List<Boolean> getFlag() {
		return flag;
	}
	
	
	/**
	 * 文件夹跳转   更新currentpath   
	 * @return
	 */
	private String clickname;	
	public void setClickname(String clickname) {
		this.clickname = clickname;
	}
	public String updateSession() throws IOException{
		if(clickname==null||clickname==""){
			clickname=(String)session.get("email");
		}
		String currentpath=(String)session.get("currentpath");
		return "upsession";
	}
	public String updateCpath(){
		String projectName=(String)session.get("projectName");
		String str=projectName+"\\upload\\"+(String)session.get("email");
		str=str.substring(1);
		String cpath=(String)session.get("currentpath");
		session.put("currentpath",cpath.substring(0, cpath.indexOf(str)+str.length()));
		return null;
	}	
}
