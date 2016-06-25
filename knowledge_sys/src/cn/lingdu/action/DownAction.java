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
	//��ȡsession��map
	ActionContext ac = ActionContext.getContext();
	Map<String, Object> session = ac.getSession();
	
	String currentpath = (String) session.get("currentpath");
	
	//��ȡ�ļ���
	private String fileName;
	public void setFileName(String fileName) {
		//get�ύ,������������
		try {
			fileName = new String(fileName.getBytes("ISO8859-1"),"UTF-8");
		} catch (Exception e) {
			throw new RuntimeException();
		}
		this.fileName = fileName.trim();
	}
	
	String[] fna;
	public void setFna(String[] fna) {
		//����ѡ�е��ļ���
		fna = fileName.substring(1, fileName.length()-1).split(",");
		for(int i=0; i<fna.length; i++){
			//�����������ļ���
			fna[i] = fna[i].substring(1, fna[i].length()-1);
		}
		this.fna = fna;
	}
	
	/**
	 * ���ļ�����
	 * @return
	 */
	//��xml�з���stream
	public String down() throws Exception{
		setFna(fna);
		if(fna.length==1)
			return "download";
		else
			return "downloadMultiFile";
	}

	File filedl;
	//�����ļ�������
	public InputStream getAttrInputStream() {			
		//����һ
		//return ServletActionContext.getServletContext().getResourceAsStream("/upload/453342840@qq.com/"+file.trim());
		
		//������
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
	
	//������ʾ���ļ���
	public String getDownFileName(){
		//���ı���
		try {
			fna[0] = URLEncoder.encode(fna[0],"UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException();
		}
		return fna[0];
	}
	
	/**
	 * ���ļ�����
	 * @throws FileNotFoundException 
	 */
	File zip;
	String formatDate;
	public InputStream getMultiInputStream() throws FileNotFoundException{
		 //ʹ�õ�ǰʱ�������ļ����� 
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
		//���ı���
		try {
			name = URLEncoder.encode("���ѹ��"+formatDate+".zip","utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException();
		}
		return name;
	}
	public long getZipLength(){
		return zip.length();
	}
}
