package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MoveDao;
import model.MoveBeans;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchResultServlet")
public class SearchResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchResultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// リクエストパラメータの文字コードを指定
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの入力項目を取得
		String search = request.getParameter("search");
		// リクエストパラメータの入力項目を引数に渡して、Daoの検索メソッドを実行
		MoveDao moveDao = new MoveDao();
		List<MoveBeans> searchMoveList = moveDao.moveSearch(search);
        // リクエストスコープにユーザ情報の検索結果をセット
        request.setAttribute("searchMoveList", searchMoveList);

     // toppage.jspへフォワード
     RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/searchResult.jsp");
     dispatcher.forward(request, response);

	}

}
