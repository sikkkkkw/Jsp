package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserBean;
import model.UserDAO;


@WebServlet("/User.do")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User() {
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
		request.setCharacterEncoding("utf8");
		
		String actionName = request.getParameter("a");
		System.out.println(actionName);
		
		if("signup".equals(actionName)){
			UserBean ubean = new UserBean();
			
			ubean.setUname(request.getParameter("name"));
			ubean.setId(request.getParameter("id"));
			String password1 = request.getParameter("password1");
			String password2 = request.getParameter("password2");
			ubean.setPassword(password1);
			
			if (password1.equals(password2)) {
			    if (password1.length() >= 8) {
			        UserDAO udao = new UserDAO();
			        if (udao.findId(ubean)) {
			            request.setAttribute("msg", "2");
			            System.out.println("존재하는 아이디입니다.");
			            RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
			            rd.forward(request, response);
			        } else {
			            udao.insertUser(ubean);
			            request.setAttribute("msg", "3");
			            System.out.println("회원 등록이 되었습니다.");
			            RequestDispatcher rd = request.getRequestDispatcher("login_page.jsp");
			            rd.forward(request, response);
			        }
			    } else {
			        System.out.println("비밀번호는 최소 8자 이상이어야 합니다.");
			        request.setAttribute("msg", "1");
			        RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
			        rd.forward(request, response);
			    }
			} else {
			    System.out.println("비밀번호 재확인이 다릅니다.");
			    request.setAttribute("msg", "1");
			    RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
			    rd.forward(request, response);
			}
			
		}
		
		else if("login".equals(actionName)) {
			
			UserDAO udao = new UserDAO();
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			System.out.println(id);
			System.out.println(password);		
			
			if(udao.loginCheck(id,password)) {
				//로그인 성공
				System.out.println("로그인 성공");
				String username = udao.getUserName(id);
				HttpSession session = request.getSession();
				session.setAttribute("id", id);
				session.setAttribute("username", username);
				response.sendRedirect("Card.do");
			}
		
			else{
				//로그인 실패
				System.out.println("로그인 실패");
				request.setAttribute("msg","2");
				RequestDispatcher rd = request.getRequestDispatcher("login_page.jsp");
				rd.forward(request, response);
			}
				
		}
		
		else {
				System.out.println("else입니다");
				HttpSession session = request.getSession();
				session.invalidate();
				RequestDispatcher rd = request.getRequestDispatcher("login_page.jsp");
				rd.forward(request, response);
		}
	}
}
