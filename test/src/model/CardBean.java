package model;

import java.sql.Date;

public class CardBean {
   private ComBean cb;
   public ComBean getCb() {
      return cb;
   }
   public void setCb(ComBean cb) {
      this.cb = cb;
   }
   private int cnum;
   private String cname;
   private String phone;
   private String dep;
   private String pos;
   private String email;
   private String create_at;
   private int user_unum;
   public int getCnum() {
      return cnum;
   }
   public void setCnum(int cnum) {
      this.cnum = cnum;
   }
   public String getCname() {
      return cname;
   }
   public void setCname(String cname) {
      this.cname = cname;
   }
   public String getPhone() {
      return phone;
   }
   public void setPhone(String phone) {
      this.phone = phone;
   }
   public String getDep() {
      return dep;
   }
   public void setDep(String dep) {
      this.dep = dep;
   }
   public String getPos() {
      return pos;
   }
   public void setPos(String pos) {
      this.pos = pos;
   }
   public String getEmail() {
      return email;
   }
   public void setEmail(String email) {

      this.email = email;
   }
   public String getCreate_at() {

      return create_at;
   }
   public void setCreate_at(String create_at) {
      this.create_at = create_at;
   }
   public int getUser_unum() {
      return user_unum;
   }
   public void setUser_unum(int user_unum) {
      this.user_unum = user_unum;
   }
  
}