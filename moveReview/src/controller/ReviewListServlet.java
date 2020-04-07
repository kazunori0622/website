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

import dao.MoveDao;
import dao.ReviewDao;
import model.MoveBeans;
import model.ReviewBeans;
import model.UserBeans;

/**
 * Servlet implementation class ReviewListServlet
 */
@WebServlet("/ReviewListServlet")
public class ReviewListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewListServlet() {
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

		// 全データ取得のDaoのメソッドを実行
		ReviewDao reviewDao = new ReviewDao();
		List<ReviewBeans> reviewList = reviewDao.reviewFindByIdAll(id);
		// リクエストスコープにユーザ情報をセット
		request.setAttribute("reviewList", reviewList);

		// reviewList.jspへフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/reviewList.jsp");
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
