package control;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CardBean;
import model.CardDAO;
import model.ComBean;
import model.ComDAO;
import model.ComphoneBean;
import model.ComphoneDAO;
import model.UserDAO;

/**
 * Servlet implementation class Card
 */
@WebServlet("/Card.do")
public class Card extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Card() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      rePro(request, response);
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      rePro(request, response);
   }
   
   
   protected void rePro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      request.setCharacterEncoding("utf8");
      System.out.println("Card.do 서블릿입니다.");      
      String actionName = request.getParameter("a");
      System.out.println(actionName);
      
      if("Add".equals(actionName)){
         System.out.println("추가하겠습니다.");
         String cname = request.getParameter("name");
         String phone = request.getParameter("phone");
         String dep = request.getParameter("dep");
         String pos = request.getParameter("pos");
         String email = request.getParameter("email");
         String comname = request.getParameter("comname");
         String address = request.getParameter("address");
         String fax = request.getParameter("fax");
         String[] comphone = request.getParameterValues("comphone");
         Integer user_unum = Integer.parseInt(request.getParameter("user_unum"));
         
         CardBean cbean = new CardBean();
         CardDAO cdao = new CardDAO();
         cbean.setCname(cname);
         cbean.setPhone(phone);
         cbean.setDep(dep);
         cbean.setPos(pos);
         cbean.setEmail(email);
         cbean.setUser_unum(user_unum);
         cdao.AddCard(cbean);//card테이블 데이터 넣는 함수
         int cnum = cdao.FindCardNum(user_unum); //카드 pk 찾는 함수(int)
         
         ComBean combean = new ComBean();
         ComDAO comdao = new ComDAO();
         combean.setComname(comname);
         combean.setAddress(address);
         combean.setFax(fax);
         combean.setCard_cnum(cnum); //card_cnum에 외래키 관련 데이터 넣으면됨
         comdao.AddCom(combean);//com테이블 데이터 넣는 함수
         int comnum = comdao.FindComNum(cnum);//comnum 회사 pk 찾는 함수
         
         ComphoneBean cpbean = new ComphoneBean();
         ComphoneDAO cpdao = new ComphoneDAO();
         Vector<String> vs = new Vector<>();
         for(int i=0; i<comphone.length; i++) {
            if(comphone[i] !=""){
               vs.add(comphone[i]);
            }
         }
         for(int i=0; i<vs.size(); i++) {
            cpbean.setComphone(vs.get(i));
            cpbean.setCom_comnum(comnum); //카드 pk 외래키 연결
            cpdao.AddComphone(cpbean);//comphone 테이블에 데이터 넣기
         }
      
         System.out.println("추가끝났습니다..");
         response.sendRedirect("Card.do");
      }
      else if("Delete".equals(actionName)) {
         System.out.println("삭제하겠습니다.");
         Integer cnum = Integer.parseInt(request.getParameter("cnum"));
         ComDAO comdao = new ComDAO();
         int comnum = comdao.FindComNum(cnum);
         
         ComphoneDAO cpdao = new ComphoneDAO();
         CardDAO cdao = new CardDAO();
         cpdao.deleteComphone(comnum);
         comdao.deleteCom(comnum);
         cdao.deleteCard(cnum);
         System.out.println("삭제끝났습니다.");
         response.sendRedirect("Card.do");
      }
      else if("Detail".equals(actionName)) {
         System.out.println("상세페이지입니다.");
         Integer cnum = Integer.parseInt(request.getParameter("cnum"));
         CardDAO cdao = new CardDAO();
         CardBean cbean = new CardBean();
         cbean = cdao.Detail(cnum);
         request.setAttribute("cbean", cbean);
         RequestDispatcher rd = request.getRequestDispatcher("new_detail.jsp");
         rd.forward(request, response);   
      }
      else if("Update".equals(actionName)) {
         String actionName2 = request.getParameter("b");
         if("Execute".equals(actionName2)) {
            //데이터베이스에 접근하여 실제 수정하기
            System.out.println("수정시작했습니다.");
            Integer cnum = Integer.parseInt(request.getParameter("cnum"));
            String cname = request.getParameter("name");
            String phone = request.getParameter("phone");
            String dep = request.getParameter("dep");
            String pos = request.getParameter("pos");
            String email = request.getParameter("email");
            String comname = request.getParameter("comname");
            String address = request.getParameter("address");
            String fax = request.getParameter("fax");
            String[] comphone = request.getParameterValues("comphone");
            
            CardBean cbean = new CardBean();
            CardDAO cdao = new CardDAO();
            cbean.setCname(cname);
            cbean.setPhone(phone);
            cbean.setDep(dep);
            cbean.setPos(pos);
            cbean.setEmail(email);
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat datetime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String create_at = datetime.format(cal.getTime());
            System.out.println(create_at);
            cbean.setCreate_at(create_at);
            // cnum인자로 보내서 수정하기
            cdao.UpdateCard(cbean,cnum); //card테이블 변경
            
            ComBean combean = new ComBean();
            ComDAO comdao = new ComDAO();
            combean.setComname(comname);
            combean.setAddress(address);
            combean.setFax(fax);
            int comnum = comdao.FindComNum(cnum);//comnum 회사 pk 찾는 함수
            comdao.UpdateCom(combean,comnum);
            // where cnum으로 comnum를 찾은후 comnum기준으로 com 테이블 수정

            
            ComphoneBean cpbean = new ComphoneBean();
            ComphoneDAO cpdao = new ComphoneDAO();
            Vector<String> vs = new Vector<>();
            for(int i=0; i<comphone.length; i++) {
               if(comphone[i] !=""){
                  vs.add(comphone[i]);
               }
            }
            cpdao.deleteComphone(comnum);
            for(int i=0; i<vs.size(); i++) {
               cpbean.setComphone(vs.get(i));
               cpbean.setCom_comnum(comnum); //카드 pk 외래키 연결
               cpdao.AddComphone(cpbean);//comphone 테이블에 데이터 넣기
            }
            System.out.println("수정끝났습니다..");
            response.sendRedirect("Card.do");
         }
         else { // 수정 페이지에만 입장하기
            System.out.println("수정페이지입니다.");
            Integer cnum = Integer.parseInt(request.getParameter("cnum"));
            CardDAO cdao = new CardDAO();
            CardBean cbean = new CardBean();
            cbean = cdao.Detail(cnum);
            request.setAttribute("cbean", cbean);
            RequestDispatcher rd = request.getRequestDispatcher("new_update.jsp");
            rd.forward(request, response);   
         }
      }
      
      else if("Search".equals(actionName)) {
         System.out.println("검색하고있습니다.");
         String search = (String) request.getParameter("search");
         System.out.println(search);
         
         HttpSession session = request.getSession();
         String id = (String) session.getAttribute("id");
         String username = (String) session.getAttribute("username");
         System.out.println(id);
         System.out.println(username);
         
         UserDAO udao = new UserDAO();
         int unum = udao.getUserNum(id);
         Vector<CardBean> v = new Vector<>();
         v = udao.Search(unum, search);
         request.setAttribute("user_unum", unum);
         request.setAttribute("v", v);
      
         RequestDispatcher rd = request.getRequestDispatcher("new_list.jsp");
         rd.forward(request, response);   
      }
      else {
         System.out.println("리스트입니다.");
         HttpSession session = request.getSession();
         String id = (String) session.getAttribute("id");
         String username = (String) session.getAttribute("username");
         System.out.println(id);
         System.out.println(username);
         
         UserDAO udao = new UserDAO();
         int unum = udao.getUserNum(id);
         //유저가 등록한 명함의 개인정보가져오기
         Vector<CardBean> v = new Vector<>();
         v = udao.CardList(unum);
         //사용자가 등록한 명함들을 벡터로 가져옴
         request.setAttribute("user_unum", unum);
         request.setAttribute("v", v);
         System.out.println(v.size());
         RequestDispatcher rd = request.getRequestDispatcher("new_list.jsp");
         rd.forward(request, response);   
      }
      
      
      
   }
}

