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

import dao.FavoriteDao;
import dao.MoveDao;
import dao.ReviewDao;
import dao.UserDao;
import model.FavoriteBeans;
import model.MoveBeans;
import model.ReviewBeans;
import model.UserBeans;

/**
 * Servlet implementation class MypageServlet
 */
@WebServlet("/MypageServlet")
public class MypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MypageServlet() {
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

		// GETパラメータの入力項目を引数に渡して、Daoのメソッドを実行
		MoveDao moveDao = new MoveDao();
		List<MoveBeans> moveReviewList = moveDao.moveReview(id);
		// リクエストスコープにユーザ情報をセット
		request.setAttribute("moveReviewList", moveReviewList);

		// GETパラメータの入力項目を引数に渡して、Daoのメソッドを実行
		FavoriteDao favoriteDao = new FavoriteDao();
		List<FavoriteBeans> favoriteList = favoriteDao.mypageFavorite(id);
		// リクエストスコープにユーザ情報をセット
		request.setAttribute("favoriteList", favoriteList);

		// レビュー件数取得
		ReviewDao reviewDao = new ReviewDao();
		ReviewBeans reviewCount = reviewDao.reviewCount(id);
		// リクエストスコープにレビュー件数情報をセット
		request.setAttribute("reviewCount", reviewCount);

		// 気になる作品の件数取得
		FavoriteBeans favoriteCount = favoriteDao.favoriteCount(id);
		// リクエストスコープにレビュー件数情報をセット
		request.setAttribute("favoriteCount", favoriteCount);


		// mypage.jspへフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
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
