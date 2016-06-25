package cn.lingdu.dao.impl;

import org.hibernate.SessionFactory;

import cn.lingdu.dao.IFileDao;
import cn.lingdu.entity.MyFile;

public class MyFileDao implements IFileDao {

	// IOCÈÝÆ÷×¢ÈëSessionFactory
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addFile(MyFile myFile) {
		sessionFactory.getCurrentSession().save(myFile);
	}

	@Override
	public void deleteFile(MyFile myFile) {
		sessionFactory.getCurrentSession()
				.createQuery("delete from MyFile where filePath=?")//
				.setParameter(0, myFile.getFilePath())//
				.executeUpdate();
	}

	@Override
	public void reName(MyFile myFile) {
		sessionFactory.getCurrentSession().update(myFile);
	}

}
