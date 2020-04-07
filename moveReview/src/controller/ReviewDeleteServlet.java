package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MoveDao;
import dao.ReviewDao;
import model.MoveBeans;
import model.ReviewBeans;
import model.UserBeans;

/**
 * Servlet implementation class ReviewDeleteServlet
 */
@WebServlet("/ReviewDeleteServlet")
public class ReviewDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewDeleteServlet() {
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
		String moveId = request.getParameter("moveId");
		// GETパラメータの入力項目を引数に渡して、Daoのメソッドを実行
		MoveDao moveDao = new MoveDao();
		MoveBeans move = moveDao.moveFindById(moveId);
		// リクエストスコープにユーザ情報をセット
		request.setAttribute("move", move);

		// URLからGETパラメータとしてIDを受け取る
		String reviewId = request.getParameter("reviewId");
		// GETパラメータの入力項目を引数に渡して、Daoのメソッドを実行
		ReviewDao reviewDao = new ReviewDao();
		ReviewBeans review = reviewDao.reviewFindById(reviewId);
		// リクエストスコープにユーザ情報をセット
		request.setAttribute("review", review);

		// reviewDelete.jspへフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/reviewDelete.jsp");
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
		String rMoveId = request.getParameter("rMoveId");
		String reviewId = request.getParameter("reviewId");

		ReviewDao reviewDao = new ReviewDao();
		reviewDao.delete(reviewId);

		// ReviewListServletへリダイレクト
		response.sendRedirect("ReviewListServlet?id=" + rMoveId);
	}

}
