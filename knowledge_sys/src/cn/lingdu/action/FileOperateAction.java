package cn.lingdu.action;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;











import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JSONString;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;

import cn.lingdu.entity.MyFile;
import cn.lingdu.service.IFileService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FileOperateAction extends ActionSupport {
	//获取session的map
	ActionContext ac = ActionContext.getContext();
	Map<String, Object> session = ac.getSession();
		
	//注入Service 一定要用接口接收
	private IFileService myFileService;
	public void setMyFileService(IFileService myFileService) {
		this.myFileService = myFileService;
	}
	
	//获取文件名
	private String fileName;	
	public void setFileName(String fileName) {
//		//get提交,处理中文问题
//		try {
//			fileName = new String(fileName.getBytes("ISO8859-1"),"UTF-8");
//		} catch (Exception e) {
//			throw new RuntimeException();
//		}
		this.fileName = fileName;
	}

	//得到当前路径
	String currentpath = (String) session.get("currentpath");
	
	
	
	/**
	 * 删除文件
	 */
	public String deleteFile(){	
		String[] fna = fileName.substring(1, fileName.length()-1).split(",");
		for(String fn: fna){
			//批量文件删除
			fn = fn.substring(1, fn.length()-1);
			String filePath = currentpath+"/"+fn;
			File file = new File(filePath);
			file.delete();
			if(!file.isDirectory()){
			//数据库删除
			MyFile myFile = new MyFile();
			myFile.setFilePath(filePath);
			myFileService.deleteFile(myFile);
			}
		}
				
		return "delete";
	}
	
	/**
	 * 重命名文件
	 * @return
	 */
	//获取新名字
	private String newName;
	public void setNewName(String newName) {
		try {
			newName = new String(newName.getBytes("ISO8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException();
		}
		this.newName = newName.trim();
	}
	private List<String> flag;
	public List<String> getFlag() {
		return flag;
	}
	public String reName(){
		String filePath = currentpath+"/"+fileName;
		if(!fileName.isEmpty()&&!newName.isEmpty()){
			File file = new File(filePath);
			file.renameTo(new File(currentpath+"/"+newName));
			flag= new ArrayList<String>();
			flag.add("success");
		}
		return "reName";
	}
}
