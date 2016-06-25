package cn.lingdu.service.impl;

import cn.lingdu.dao.IFileDao;
import cn.lingdu.entity.MyFile;
import cn.lingdu.service.IFileService;

public class MyFileService implements IFileService {

	//注入dao 一定要用接口接收
	private IFileDao myFileDao;
	public void setMyFileDao(IFileDao myFileDao) {
		this.myFileDao = myFileDao;
	}
	
	@Override
	public void addFile(MyFile myFile) {
		myFileDao.addFile(myFile);
	}

	@Override
	public void deleteFile(MyFile myFile) {
		myFileDao.deleteFile(myFile);
	}

	@Override
	public void reName(MyFile myFile) {
		myFileDao.reName(myFile);
	}

}
