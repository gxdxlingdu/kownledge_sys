package cn.lingdu.service;

import cn.lingdu.entity.MyFile;

public interface IFileService {

	/**
	 * ����ļ�
	 * @param myFile
	 */
	public void addFile(MyFile myFile);
	
	/**
	 * ɾ���ļ�
	 * @param myFile
	 */
	public void deleteFile(MyFile myFile);
	
	/**
	 * ������
	 * @param myFile
	 * @return �����޸ĺ���ļ�
	 */
	public void reName(MyFile myFile);
}
