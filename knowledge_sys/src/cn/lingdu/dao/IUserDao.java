package cn.lingdu.dao;

import cn.lingdu.entity.User;

public interface IUserDao {
	/**
	 * ����û�
	 * @param user
	 */
	public void addUser(User user);
	
	/**
	 * ͨ��email�����û�
	 * @param email
	 * @return
	 */
	public User findByEmail(String email);
	
	/**
	 * ͨ���˺���������û�
	 * @param user
	 * @return
	 */
	public User findByUser(User user);
	
	/**
	 * �޸��û�
	 * @param email
	 * @return
	 */
	public void alertByUser(User user);

}
