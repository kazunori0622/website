package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.UserBeans;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// login.jspへフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// リクエストパラメータの文字コードを指定
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの入力項目を取得
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");

		// リクエストパラメータを引数に渡して、Daoのメソッドを実行
		UserDao userDao = new UserDao();
		UserBeans user = userDao.findByLoginInfo(loginId, password);

		// テーブルに該当データが見つからなかった場合(ログイン失敗時)
		if (user == null) {
			// リクエストスコープにエラーメッセージをセット
			request.setAttribute("errMsg", "ログインIDまたはパスワードが異なります");
			// login.jspへフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
			// 下の処理までいくのを防ぐためreturnで返す
			return;
		}

		// テーブルに該当データが見つかった場合(ログイン成功時)
		// セッションスコープにログインIDと名前をセット
		HttpSession session = request.getSession();
		session.setAttribute("userInfo", user);
		// ToppageServletのサーブレットにリダイレクト
		response.sendRedirect("ToppageServlet");

	}

}
