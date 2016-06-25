package cn.lingdu.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.zip.ZipFile;

import org.apache.struts2.ServletActionContext;
import org.aspectj.weaver.bcel.ClassPathManager.ZipFileEntry;

import cn.lingdu.Util.ZipFileUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DownAction extends ActionSupport{
	//获取session的map
	ActionContext ac = ActionContext.getContext();
	Map<String, Object> session = ac.getSession();
	
	String currentpath = (String) session.get("currentpath");
	
	//获取文件名
	private String fileName;
	public void setFileName(String fileName) {
		//get提交,处理中文问题
		try {
			fileName = new String(fileName.getBytes("ISO8859-1"),"UTF-8");
		} catch (Exception e) {
			throw new RuntimeException();
		}
		this.fileName = fileName.trim();
	}
	
	String[] fna;
	public void setFna(String[] fna) {
		//所有选中的文件名
		fna = fileName.substring(1, fileName.length()-1).split(",");
		for(int i=0; i<fna.length; i++){
			//遍历出所有文件名
			fna[i] = fna[i].substring(1, fna[i].length()-1);
		}
		this.fna = fna;
	}
	
	/**
	 * 单文件下载
	 * @return
	 */
	//在xml中返回stream
	public String down() throws Exception{
		setFna(fna);
		if(fna.length==1)
			return "download";
		else
			return "downloadMultiFile";
	}

	File filedl;
	//返回文件流方法
	public InputStream getAttrInputStream() {			
		//方法一
		//return ServletActionContext.getServletContext().getResourceAsStream("/upload/453342840@qq.com/"+file.trim());
		
		//方法二
		InputStream is = null;
		try {
			filedl = new File(currentpath+"/"+fna[0]);
			if(!filedl.isDirectory()){
			is = new FileInputStream(filedl);
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException();
		}
		return is;
	}
	
	public long getFileLength(){
		return filedl.length();
	}
	
	//下载显示的文件名
	public String getDownFileName(){
		//中文编码
		try {
			fna[0] = URLEncoder.encode(fna[0],"UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException();
		}
		return fna[0];
	}
	
	/**
	 * 多文件下载
	 * @throws FileNotFoundException 
	 */
	File zip;
	String formatDate;
	public InputStream getMultiInputStream() throws FileNotFoundException{
		 //使用当前时间生成文件名称 
        formatDate =new  SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date()); 
        String path = null;
		if(fna!=null){
			File file[] = new File[fna.length];
			for(int i=0; i<fna.length; i++){
				 File fi = new File(currentpath+"/"+fna[i]);
				 if(fi!=null&&fi.isFile()){
					 file[i]=fi;
				 }
			}
			path = ServletActionContext.getServletContext().getRealPath("/temp");
			File filePath = new File(path);
			if(!filePath.exists()){
				filePath.mkdir();
			}
			ZipFileUtil.compressFiles2Zip(file, path+"/"+formatDate+".zip");;
		}
		zip = new File(path+"/"+formatDate+".zip");
		InputStream is = new FileInputStream(zip);
		return is;
	}
	public String getDownMultiFileName(){
		String name;
		//中文编码
		try {
			name = URLEncoder.encode("零度压缩"+formatDate+".zip","utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException();
		}
		return name;
	}
	public long getZipLength(){
		return zip.length();
	}
}
