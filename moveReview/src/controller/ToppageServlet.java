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
import model.MoveBeans;
import model.UserBeans;

/**
 * Servlet implementation class ToppageServlet
 */
@WebServlet("/ToppageServlet")
public class ToppageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToppageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		MoveDao moveDao = new MoveDao();



		// おすすめ映画3件(ランダムで取得)の情報のDAOメソッド
		List<MoveBeans> recoMoveList = moveDao.recommend(3);
		// リクエストスコープにユーザ情報をセット
		request.setAttribute("recoMoveList", recoMoveList);

		// 上位3位までの最新映画ランキング情報のDAOメソッド
		List<MoveBeans> newMoveList = moveDao.newMoveRanking(3);
		// リクエストスコープにユーザ情報をセット
		request.setAttribute("newMoveList", newMoveList);



		// 最近チェックした作品
		UserBeans user = (UserBeans)session.getAttribute("userInfo");
		if (user != null) {
			int id = user.getId();
			List<MoveBeans> userCheckMoveList = moveDao.userCheckMove(id);
			// リクエストスコープにユーザ情報をセット
			request.setAttribute("userCheckMoveList", userCheckMoveList);
		}

		// toppage.jspへフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/toppage.jsp");
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
