package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FavoriteDao;
import dao.MoveDao;
import model.FavoriteBeans;
import model.MoveBeans;
import model.UserBeans;

/**
 * Servlet implementation class MoveDetailsServlet
 */
@WebServlet("/MoveDetailsServlet")
public class MoveDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoveDetailsServlet() {
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
		String fUserId = request.getParameter("fUserId");

		FavoriteDao favoriteDao = new FavoriteDao();
		FavoriteBeans favorite = favoriteDao.registered(fUserId, id);
		request.setAttribute("favorite", favorite);



		// GETパラメータの入力項目を引数に渡して、Daoのメソッドを実行
		MoveDao moveDao = new MoveDao();
		MoveBeans move = moveDao.moveFindById(id);
		// リクエストスコープにユーザ情報をセット
		request.setAttribute("move", move);
		// moveDetails.jspへフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/moveDetails.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
