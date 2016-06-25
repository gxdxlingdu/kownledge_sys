package cn.lingdu.service.impl;

import cn.lingdu.dao.IUserDao;
import cn.lingdu.entity.User;
import cn.lingdu.service.IUserService;

public class UserService implements IUserService {

	//ע��dao һ��Ҫ�ýӿڽ���
	private IUserDao userDao;
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	public User login(User user) {
		return userDao.findByUser(user);
	}

	@Override
	public void register(User user) {
		userDao.addUser(user);
	}
	@Override
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

}
