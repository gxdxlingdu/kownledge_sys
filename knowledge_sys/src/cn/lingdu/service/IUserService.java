package cn.lingdu.service;

import cn.lingdu.entity.User;

public interface IUserService {
	/**
	 * ��½
	 * @param user
	 * @return
	 */
	public User login(User user);
	
	/**
	 * ע��
	 * @param user
	 */
	public void register(User user);
	
	/**
	 * ������������û�
	 * @param user
	 * @return
	 */
	public User findByEmail(String email);
}
