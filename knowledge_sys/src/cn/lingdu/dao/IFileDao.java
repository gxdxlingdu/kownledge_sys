package cn.lingdu.dao;

import cn.lingdu.entity.MyFile;

public interface IFileDao {
	
	/**
	 * ����ļ�
	 * @param file
	 */
	public void addFile(MyFile myFile);
	
	/**
	 * ɾ���ļ�
	 * @param file
	 */
	public void deleteFile(MyFile myFile);
	
	/**
	 * ������
	 * @param file
	 * @return �����޸ĺ���ļ�
	 */
	public void reName(MyFile myFile);
}
