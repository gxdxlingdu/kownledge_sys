package cn.lingdu.service;

import cn.lingdu.entity.User;

public interface IUserService {
	/**
	 * 登陆
	 * @param user
	 * @return
	 */
	public User login(User user);
	
	/**
	 * 注册
	 * @param user
	 */
	public void register(User user);
	
	/**
	 * 根据邮箱查找用户
	 * @param user
	 * @return
	 */
	public User findByEmail(String email);
}
