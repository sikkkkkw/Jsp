package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ComphoneDAO {
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
	
	public void AddComphone(ComphoneBean cpbean) {
		getCon();
		try {
			String sql = "insert into comphone values (?, ?)";
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1, cpbean.getComphone());
			pstmt.setInt(2, cpbean.getCom_comnum());
			pstmt.executeUpdate();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteComphone(int com_comnum) {
		getCon();
		try {
			String sql = "delete from comphone where com_comnum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, com_comnum);
			pstmt.executeUpdate();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Vector<ComphoneBean> DetailPhone(int com_comnum) {
		getCon();
		Vector<ComphoneBean> vpbean = new Vector<>();
		
		try {
			String sql = "select * from comphone where com_comnum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, com_comnum);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				ComphoneBean cpbean = new ComphoneBean();
				cpbean.setComphone(rs.getString(1));
				cpbean.setCom_comnum(rs.getInt(2));
				vpbean.add(cpbean);
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return vpbean;
	}
}
