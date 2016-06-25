package cn.lingdu.dao;

import cn.lingdu.entity.User;

public interface IUserDao {
	/**
	 * 添加用户
	 * @param user
	 */
	public void addUser(User user);
	
	/**
	 * 通过email查找用户
	 * @param email
	 * @return
	 */
	public User findByEmail(String email);
	
	/**
	 * 通过账号密码查找用户
	 * @param user
	 * @return
	 */
	public User findByUser(User user);
	
	/**
	 * 修改用户
	 * @param email
	 * @return
	 */
	public void alertByUser(User user);

}
