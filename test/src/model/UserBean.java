package model;

import java.util.Vector;

public class UserBean {
	private Vector<CardBean> v;
	private int unum;
	private String uname;
	private String id;
	private String password;
	
	public int getUnum() {
		return unum;
	}
	public void setUnum(int unum) {
		this.unum = unum;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Vector<CardBean> getV() {
		return v;
	}
	public void setV(Vector<CardBean> v) {
		this.v = v;
	}
}
