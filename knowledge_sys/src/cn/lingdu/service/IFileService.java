package cn.lingdu.service;

import cn.lingdu.entity.MyFile;

public interface IFileService {

	/**
	 * 添加文件
	 * @param myFile
	 */
	public void addFile(MyFile myFile);
	
	/**
	 * 删除文件
	 * @param myFile
	 */
	public void deleteFile(MyFile myFile);
	
	/**
	 * 重名称
	 * @param myFile
	 * @return 返回修改后的文件
	 */
	public void reName(MyFile myFile);
}
