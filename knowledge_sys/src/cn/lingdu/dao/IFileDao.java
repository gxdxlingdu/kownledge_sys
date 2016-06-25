package cn.lingdu.dao;

import cn.lingdu.entity.MyFile;

public interface IFileDao {
	
	/**
	 * 添加文件
	 * @param file
	 */
	public void addFile(MyFile myFile);
	
	/**
	 * 删除文件
	 * @param file
	 */
	public void deleteFile(MyFile myFile);
	
	/**
	 * 重名称
	 * @param file
	 * @return 返回修改后的文件
	 */
	public void reName(MyFile myFile);
}
