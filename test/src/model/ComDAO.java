package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ComDAO {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	
	public void getCon() {
		try {
			Context initctx = new InitialContext();
			Context envctx = (Context)initctx.lookup("java:comp/env");
			DataSource ds = (DataSource)envctx.lookup("jdbc/MySQLDB");
			con = ds.getConnection();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Vector<ComphoneBean> getPhone(int comnum) {
		Vector<ComphoneBean> v = new Vector<>();
		getCon();
		
		try {
			String sql = "select * from comphone where com_comnum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, comnum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ComphoneBean cp = new ComphoneBean();
				cp.setComphone(rs.getString(1));
				cp.setCom_comnum(rs.getInt(2));
				v.add(cp);
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	public void AddCom(ComBean combean) {
		getCon();
		try {
			String sql = "insert into com (comname, address, fax, card_cnum) values (?, ?, ?, ?)";
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1, combean.getComname());
			pstmt.setString(2, combean.getAddress());
			pstmt.setString(3, combean.getFax());
			pstmt.setInt(4, combean.getCard_cnum());
			pstmt.executeUpdate();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int FindComNum(int num) {
		getCon();
		int comnum=0;
		try {
			String sql = "select comnum from com where card_cnum=?";
			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				comnum = rs.getInt(1);
			}
			con.close();
			return comnum;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public void deleteCom(int comnum) {
		getCon();
		try {
			String sql = "delete from com where comnum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, comnum);
			pstmt.executeUpdate();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ComBean DetailCom(int card_cnum) {
		getCon();
		ComBean combean = new ComBean();
		ComphoneDAO cpdao = new ComphoneDAO();
		Vector<ComphoneBean> vpbean = new Vector<>();
		try {
			String sql = "select * from com where card_cnum = ?";
			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, card_cnum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				combean.setComnum(rs.getInt(1));
				combean.setComname(rs.getString(2));
				combean.setAddress(rs.getString(3));
				combean.setFax(rs.getString(4));
				combean.setCard_cnum(rs.getInt(5));
				
				vpbean = cpdao.DetailPhone(rs.getInt(1));
				combean.setVcp(vpbean);
			}
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return combean;
	}
	public void UpdateCom(ComBean combean, int comnum) {
		getCon();
		try {
			String sql = "Update com set comname=?, address=?, fax=? where comnum=?";
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1, combean.getComname());
			pstmt.setString(2, combean.getAddress());
			pstmt.setString(3, combean.getFax());
			pstmt.setInt(4, comnum);
			pstmt.executeUpdate();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
