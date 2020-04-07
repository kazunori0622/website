package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FavoriteDao;
import model.UserBeans;

/**
 * Servlet implementation class FavoriteServlet
 */
@WebServlet("/FavoriteServlet")
public class FavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FavoriteServlet() {
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
		String fid = request.getParameter("fid");
		String fUserId = request.getParameter("fUserId");
		FavoriteDao favoriteDao = new FavoriteDao();
		// お気に入り登録削除のDaoのメソッドを実行
		favoriteDao.delete(fid);

		// MypageServletにリダイレクト
		response.sendRedirect("MypageServlet?id=" + fUserId);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// リクエストパラメータの文字コードを指定
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの入力項目を取得
		String fUserId = request.getParameter("fUserId");
		String fMoveId = request.getParameter("fMoveId");

		FavoriteDao favoriteDao = new FavoriteDao();

		// お気に入り登録のDaoのメソッドを実行
		favoriteDao.insert(fUserId, fMoveId);

		// MypageServletにリダイレクト
		response.sendRedirect("MypageServlet?id=" + fUserId);


	}

}
