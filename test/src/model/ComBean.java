package model;

import java.util.Vector;

public class ComBean {
	private Vector<ComphoneBean> vcp;
	private int comnum;
	private String comname;
	private String address;
	private String fax;
	private int card_cnum;
	public int getComnum() {
		return comnum;
	}
	public void setComnum(int comnum) {
		this.comnum = comnum;
	}
	public String getComname() {
		return comname;
	}
	public void setComname(String comname) {
		this.comname = comname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public int getCard_cnum() {
		return card_cnum;
	}
	public void setCard_cnum(int card_cnum) {
		this.card_cnum = card_cnum;
	}
	public Vector<ComphoneBean> getVcp() {
		return vcp;
	}
	public void setVcp(Vector<ComphoneBean> vcp) {
		this.vcp = vcp;
	}
}
