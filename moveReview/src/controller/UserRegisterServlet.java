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
 * Servlet implementation class UserRegisterServlet
 */
@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 共通(トップページ画面以外へのアクセス制御)
		HttpSession session = request.getSession();
		UserBeans userInfo = (UserBeans)session.getAttribute("userInfo");
		if (userInfo == null) {
			// ToppageServletにリダイレクト
			response.sendRedirect("ToppageServlet");
			return;
		}

		// userRegister.jspへフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userRegister.jsp");
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
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String passwordCon = request.getParameter("passwordCon");

		// パスワードとパスワード(確認)の入力内容が異なる場合
		// 入力項目に1つでも未入力のものがある場合
		if (!password.equals(passwordCon) || loginId.equals("") || name.equals("")|| password.equals("") || passwordCon.equals("")) {
			// リクエストスコープにエラーメッセージをセット
			request.setAttribute("errMsg", "入力された内容は正しくありません");
			// userRegister.jspへフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userRegister.jsp");
			dispatcher.forward(request, response);
			return;
		}

		UserDao userDao = new UserDao();

		// 既に登録されているログインIDが入力された場合
		UserBeans user = userDao.findRegistered(loginId);
		if (!(user == null)) {
			// リクエストスコープにエラーメッセージをセット
			request.setAttribute("errMsg", "入力された内容は正しくありません");
			// userRegister.jspへフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userRegister.jsp");
			dispatcher.forward(request, response);
			return;
		}

		// ユーザデータ登録のDaoのメソッドを実行
		userDao.insert(loginId, name, password);

		// ToppageServletにリダイレクト
		response.sendRedirect("ToppageServlet");
	}

}
