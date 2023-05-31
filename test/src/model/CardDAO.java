package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CardDAO {
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
   
   public ComBean getCom(int cnum) {
      getCon();
      ComBean cbean = new ComBean();
      try {
         String sql = "select * from com where card_cnum = ?";
         pstmt =con.prepareStatement(sql);
         pstmt.setInt(1, cnum);
         rs=pstmt.executeQuery();
         if(rs.next()) {
            cbean.setComnum(rs.getInt(1));
            cbean.setComname(rs.getString(2));
            cbean.setAddress(rs.getString(3));
            cbean.setFax(rs.getString(4));
            cbean.setCard_cnum(rs.getInt(5));
            
            Vector<ComphoneBean> cp = new Vector<>();
            ComDAO comdao = new ComDAO();
            cp = comdao.getPhone(cbean.getComnum());
            
            cbean.setVcp(cp);
         }
         con.close();
         
      }catch(Exception e) {
         e.printStackTrace();
      }
      return cbean;
   }
   
   public void AddCard(CardBean cbean) {
      getCon();
      try {
         String sql = "insert into card (cname, phone, dep, pos, email, user_unum ) values (?, ?, ?, ?, ?, ? )";
         pstmt =con.prepareStatement(sql);
         pstmt.setString(1, cbean.getCname());
         pstmt.setString(2, cbean.getPhone());
         pstmt.setString(3, cbean.getDep());
         pstmt.setString(4, cbean.getPos());
         pstmt.setString(5, cbean.getEmail());
         pstmt.setInt(6, cbean.getUser_unum());
         pstmt.executeUpdate();
         con.close();
      }catch(Exception e) {
         e.printStackTrace();
      }
   }
   
   public int FindCardNum(int num) {
      getCon();
      int cnum=0;
      try {
         String sql = "select cnum from card order by cnum desc limit 1";
         pstmt =con.prepareStatement(sql);
         rs=pstmt.executeQuery();
         if(rs.next()) {
            cnum = rs.getInt(1);
         }
         con.close();
         return cnum;
      }catch(Exception e) {
         e.printStackTrace();
      }
      return -1;
   }
   
   public void deleteCard(int cnum) {
      getCon();
      try {
         String sql = "delete from card where cnum = ?";
         pstmt = con.prepareStatement(sql);
         pstmt.setInt(1, cnum);
         pstmt.executeUpdate();
         con.close();
      }catch(Exception e) {
         e.printStackTrace();
      }
   }
   
   public CardBean Detail(int cnum) {
      getCon();
      CardBean cbean = new CardBean();
      ComBean combean = new ComBean();
      try {
         String sql = "select * from card where cnum = ?";
         pstmt =con.prepareStatement(sql);
         pstmt.setInt(1, cnum);
         rs=pstmt.executeQuery();
         if(rs.next()) {
            cbean.setCnum(rs.getInt(1));
            cbean.setCname(rs.getString(2));
            cbean.setPhone(rs.getString(3));
            cbean.setDep(rs.getString(4));
            cbean.setPos(rs.getString(5));
            cbean.setEmail(rs.getString(6));
            cbean.setCreate_at(rs.getString(7));
            cbean.setUser_unum(rs.getInt(8));
            
            ComDAO comdao = new ComDAO();
            combean = comdao.DetailCom(cbean.getCnum());
            cbean.setCb(combean);
         }
         con.close();
      }catch(Exception e) {
         e.printStackTrace();
      }
      
      return cbean;
   }
   
   public void UpdateCard(CardBean cbean, int cnum) {
      getCon();
      try {
         String sql = "Update card set cname=?, phone=?, dep=?, pos=?, email=?, create_at=? where cnum=?";
         pstmt =con.prepareStatement(sql);
         pstmt.setString(1, cbean.getCname());
         pstmt.setString(2, cbean.getPhone());
         pstmt.setString(3, cbean.getDep());
         pstmt.setString(4, cbean.getPos());
         pstmt.setString(5, cbean.getEmail());
         pstmt.setString(6, cbean.getCreate_at());
         pstmt.setInt(7, cnum);
         pstmt.executeUpdate();
         con.close();
      }catch(Exception e) {
         e.printStackTrace();
      }
   }
}