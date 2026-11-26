package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.LoginLogic;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// セッション取得
		HttpSession session = request.getSession();
		
	    // リクエストの文字エンコーディングを設定
	    request.setCharacterEncoding("UTF-8");
	    
		// 入力されたログイン名を取得
		String name = request.getParameter("name");
		
		// ログイン名をセッションにセットする
		session.setAttribute("loginName", name);
		
		// LoginLogicを呼び出して、ログイン名を渡す
		LoginLogic loginService = new LoginLogic();
		int result = loginService.LoginLogic(name);
		
		// DBに存在するログイン名の場合trueとなってログインが成功する
		if(result != 0) {
			
			// リクエストに属性をセットする
			session.setAttribute("accountSeq", result);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
			dispatcher.forward(request, response);
		} else {
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/failure.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    // リクエストの文字エンコーディングを設定
	    request.setCharacterEncoding("UTF-8");
	    
		// 入力されたログイン名を取得
		String registerName = request.getParameter("registerName");
		
		LoginLogic loginService = new LoginLogic();
		// LoginLogicを呼び出して、ログイン名を渡す
		int result = loginService.LoginLogic(registerName);
		
		if(!registerName.trim().isEmpty() && registerName != null && result == 0) {
			// LoginLogicを呼び出して、ログイン名を渡す
			loginService.insertLogin(registerName);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.html");
			dispatcher.forward(request, response);	
			
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/failure.jsp");
			dispatcher.forward(request, response);	
		}
	}
}
