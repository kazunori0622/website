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
import model.UserBeans;

/**
 * Servlet implementation class PostReviewServlet
 */
@WebServlet("/PostReviewServlet")
public class PostReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostReviewServlet() {
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
		MoveDao moveDao = new MoveDao();
		MoveBeans move = moveDao.moveFindById(id);

		// リクエストスコープにユーザ情報をセット
		request.setAttribute("move", move);

		// postReview.jspへフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/postReview.jsp");
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
		String rUserId = request.getParameter("rUserId");
		String rMoveId = request.getParameter("rMoveId");
		String reviewScore = request.getParameter("star");
		String reviewName = request.getParameter("reviewName");
		String reviewContent = request.getParameter("reviewContent");

		// 入力項目に1つでも未入力のものがある場合
		if (reviewScore == null || reviewName.equals("") || reviewContent.equals("")) {
			// リクエストスコープにエラーメッセージをセット
			request.setAttribute("errMsg", "入力された内容は正しくありません");

			// GETパラメータの入力項目を引数に渡して、Daoのメソッドを実行
			MoveDao moveDao = new MoveDao();
			MoveBeans move = moveDao.moveFindById(rMoveId);
			// リクエストスコープにユーザ情報をセット
			request.setAttribute("move", move);

			// postReview.jspへフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/postReview.jsp");
			dispatcher.forward(request, response);
			return;
		}

		ReviewDao reviewDao = new ReviewDao();
		reviewDao.insert(rUserId, rMoveId, reviewScore, reviewName, reviewContent);

		// ReviewListServletへリダイレクト
		response.sendRedirect("ReviewListServlet?id=" + rMoveId);

	}

}
