package cn.lingdu.dao.impl;

import org.hibernate.SessionFactory;

import cn.lingdu.dao.IUserDao;
import cn.lingdu.entity.User;

public class UserDao implements IUserDao {

	// IOCÈÝÆ÷×¢ÈëSessionFactory
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	@Override
	public void addUser(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public User findByUser(User user) {
		return (User) sessionFactory.getCurrentSession()//
				.createQuery("from User where email=? and passWord=?")//
				.setString(0, user.getEmail())//
				.setString(1, user.getPassWord())//
				.uniqueResult();
	}

	@Override
	public void alertByUser(User user) {
		sessionFactory.getCurrentSession().update(user);
	}

	@Override
	public User findByEmail(String email) {
		return (User) sessionFactory.getCurrentSession()//
				.createQuery("from User where email=?")//
				.setString(0, email)//
				.uniqueResult();
	}


}
