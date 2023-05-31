package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserDAO {
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
   
   
   public void insertUser(UserBean ubean) {
      getCon();
      
      try {
         String sql ="insert into user (uname, id, password) values(?,?,?)";
         pstmt =con.prepareStatement(sql);
         pstmt.setString(1, ubean.getUname());
         pstmt.setString(2, ubean.getId());
         pstmt.setString(3, ubean.getPassword());
         pstmt.executeUpdate();
         con.close();
         
      }catch(Exception e) {
         e.printStackTrace();
      }
   }
   
   
   public boolean findId(UserBean bean) {
      getCon();
      try {
         String sql = "select id from user where id=?";
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, bean.getId());
         rs=pstmt.executeQuery();
         if(rs.next()) {
            con.close();
            return true;
         }
         con.close();   
      }catch(Exception e) {
         e.printStackTrace();
      }
      return false;
   }
   
   public boolean loginCheck(String id, String pw) {
      getCon();
      try {
         String sql = "select password from user where id=?";
         pstmt =con.prepareStatement(sql);
         pstmt.setString(1, id);
         rs=pstmt.executeQuery();
         if(rs.next()) {
            String dbpw = rs.getString(1);
            if(dbpw.equals(pw)) {
               con.close();
               return true;
            }
         }
         con.close();
         
      }catch(Exception e) {
         e.printStackTrace();
      }
      return false;
      
   }
   
   public String getUserName(String id) {
      getCon();
      String username = null;
      try {
         String sql = "select uname from user where id=?";
         pstmt =con.prepareStatement(sql);
         pstmt.setString(1, id);
         rs=pstmt.executeQuery();
         if(rs.next()) {
            username = rs.getString(1);
         }
         else {
            username = "error";
         }
         con.close();
         return username;
      }catch(Exception e) {
         e.printStackTrace();
      }
      return username;
   }
   
   public int getUserNum(String id) {
      getCon();
      int usernum = 0;
      try {
         String sql = "select unum from user where id=?";
         pstmt =con.prepareStatement(sql);
         pstmt.setString(1, id);
         rs=pstmt.executeQuery();
         if(rs.next()) {
            usernum = rs.getInt(1);
         }
         con.close();
         return usernum;
      }catch(Exception e) {
         e.printStackTrace();
      }
      return usernum;
   }
   
   // 전체 리스트 가져오기
   public Vector<CardBean> CardList(int unum) {
      getCon();
      Vector<CardBean> v = new Vector<>();
      
      try {
         String sql = "select * from card where user_unum=?";
         pstmt =con.prepareStatement(sql);
         pstmt.setInt(1, unum);
         rs=pstmt.executeQuery();
         while(rs.next()) {
            CardBean cbean = new CardBean();      
            cbean.setCnum(rs.getInt(1));
            cbean.setCname(rs.getString(2));
            cbean.setPhone(rs.getString(3));
            cbean.setDep(rs.getString(4));
            cbean.setPos(rs.getString(5));
            cbean.setEmail(rs.getString(6));
            cbean.setCreate_at(rs.getDate(7).toString());
            cbean.setUser_unum(rs.getInt(8));
         
            ComBean combean = new ComBean();
            CardDAO cdao = new CardDAO();
            combean = cdao.getCom(cbean.getCnum());
            cbean.setCb(combean);
            v.add(cbean);   
         }
         con.close();
      }catch(Exception e) {
         e.printStackTrace();
      }
      return v;
   }
   
   //검색 기능구현
   public Vector<CardBean> Search(int unum, String search) {
      getCon();
      Vector<CardBean> v = new Vector<>();
      try {
         String sql = "select * from (select * from card, com where card.user_unum = ? and card.cnum = com.card_cnum) as a where a.cname =? or comname=?";
         pstmt =con.prepareStatement(sql);
         pstmt.setInt(1, unum);
         pstmt.setString(2, search);
         pstmt.setString(3, search);
         rs=pstmt.executeQuery();
         
         while(rs.next()) {
            CardBean cbean = new CardBean();      
            cbean.setCnum(rs.getInt(1));
            cbean.setCname(rs.getString(2));
            cbean.setPhone(rs.getString(3));
            cbean.setDep(rs.getString(4));
            cbean.setPos(rs.getString(5));
            cbean.setEmail(rs.getString(6));
            cbean.setCreate_at(rs.getDate(7).toString());
            cbean.setUser_unum(rs.getInt(8));
            
            ComBean combean = new ComBean();
            ComDAO cdao = new ComDAO();
            combean.setComnum(rs.getInt(9));
            combean.setComname(rs.getString(10));
            combean.setAddress(rs.getString(11));
            combean.setFax(rs.getString(12));
            combean.setCard_cnum(rs.getInt(13));
            Vector<ComphoneBean> vec = new Vector<>();
            vec = cdao.getPhone(rs.getInt(9));
            combean.setVcp(vec);
            cbean.setCb(combean);
            v.add(cbean);   
         }
         con.close();
      }catch(Exception e) {
         e.printStackTrace();
      }
      return v;
   }
}