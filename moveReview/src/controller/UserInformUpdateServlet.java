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
 * Servlet implementation class UserInformUpdate
 */
@WebServlet("/UserInformUpdateServlet")
public class UserInformUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInformUpdateServlet() {
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

		// URLからGETパラメータとしてIDを受け取る
		String id = request.getParameter("id");

		// GETパラメータの入力項目を引数に渡して、Daoのメソッドを実行
		UserDao userDao = new UserDao();
		UserBeans user = userDao.findById(id);

		// リクエストスコープにユーザ情報をセット
		request.setAttribute("user", user);

		// userInformUpdate.jspへフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userInformUpdate.jsp");
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
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String passwordCon = request.getParameter("passwordCon");

		// パスワードとパスワード(確認)の入力内容が異なる場合
		// パスワード以外に未入力の項目がある場合
		if (!password.equals(passwordCon) || name.equals("")) {
			// リクエストスコープにエラーメッセージをセット
			request.setAttribute("errMsg", "入力された内容は正しくありません");
			// userInformUpdate.jspへフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userInformUpdate.jsp");
			dispatcher.forward(request, response);
			return;
		}

		// ユーザ情報更新のDAOメソッド
		UserDao userDao = new UserDao();

		// パスワードとパスワード(確認)がどちらも空欄の場合
		if (password.equals("") && passwordCon.equals("")) {
			// 実装予定(DAOを作る)
			userDao.updatePassBlank(id, name);
		} else {
			userDao.update(id, name, password);
		}

		// ToppageServletにリダイレクト
		response.sendRedirect("ToppageServlet");
	}

}
