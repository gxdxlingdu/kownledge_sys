package cn.lingdu.entity;

import java.util.Set;

public class User {
	private int id;
	private String email;
	private String userName;
	private String passWord;
	private Set<MyFile> Myfiles;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public Set<MyFile> getMyfiles() {
		return Myfiles;
	}
	public void setMyfiles(Set<MyFile> myfiles) {
		Myfiles = myfiles;
	}
	public User() {
		super();
	}
	
}
