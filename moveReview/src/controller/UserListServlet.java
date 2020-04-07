package controller;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class UserListServlet
 */
@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserListServlet() {
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

		// 全データ取得のDaoのメソッドを実行
		UserDao userDao = new UserDao();
		List<UserBeans> userList = userDao.findAll();

		// リクエストスコープにユーザ情報をセット
		request.setAttribute("userList", userList);

		// userList.jspへフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userList.jsp");
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

        // リクエストパラメータの入力項目を引数に渡して、Daoの検索メソッドを実行
        UserDao userDao = new UserDao();
        List<UserBeans> userList = userDao.UserSearch(loginId, name);

        // リクエストスコープにユーザ情報の検索結果をセット
        request.setAttribute("userList", userList);

        // フォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userList.jsp");
        dispatcher.forward(request, response);
	}

}
