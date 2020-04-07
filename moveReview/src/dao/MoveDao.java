package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.MoveBeans;

public class MoveDao {

	// おすすめ映画3件(ランダムで取得)
	public List<MoveBeans> recommend(int limit) {
		Connection conn = null;
		List<MoveBeans> recoMoveList = new ArrayList<MoveBeans>();
		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// SELECT文を準備
        	String sql = "SELECT * FROM move ORDER BY RAND() LIMIT ? ";

        	// SELECTを実行し、結果表を取得
        	PreparedStatement pStmt = conn.prepareStatement(sql);
        	pStmt.setInt(1, limit);
        	ResultSet rs = pStmt.executeQuery();

        	// 結果表をBeansに詰めてuserListで返す
        	while (rs.next()) {
        		MoveBeans recoMove = new MoveBeans();
        		recoMove.setId(rs.getInt("id"));
        		recoMove.setMoveName(rs.getString("move_name"));
        		recoMove.setFileName(rs.getString("file_name"));
        		recoMoveList.add(recoMove);
        	}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return recoMoveList;
	}


	// 上位3位までの最新映画ランキング情報
	public List<MoveBeans> newMoveRanking(int limit) {
		Connection conn = null;
		List<MoveBeans> newMoveList = new ArrayList<MoveBeans>();
		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// SELECT文を準備
        	String sql = "SELECT * FROM move WHERE production_date > (NOW() - INTERVAL 1 MONTH) ORDER BY box_office DESC LIMIT ?";

        	// SELECTを実行し、結果表を取得
        	PreparedStatement pStmt = conn.prepareStatement(sql);
        	pStmt.setInt(1, limit);
        	ResultSet rs = pStmt.executeQuery();

        	// 結果表をBeansに詰めてuserListで返す
        	while (rs.next()) {
        		MoveBeans move = new MoveBeans();
        		move.setId(rs.getInt("id"));
        		move.setMoveName(rs.getString("move_name"));
        		move.setFileName(rs.getString("file_name"));
        		move.setFileNameSub1(rs.getString("file_name_sub1"));
        		move.setFileNameSub2(rs.getString("file_name_sub2"));
        		newMoveList.add(move);
        	}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return newMoveList;
	}

	// 最近チェックした作品
	public List<MoveBeans> userCheckMove(int targetId) {
		Connection conn = null;
		List<MoveBeans> userCheckMoveList = new ArrayList<MoveBeans>();
		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// SELECT文を準備
        	String sql = "SELECT * FROM favorite INNER JOIN move on favorite.f_move_id = move.id WHERE f_user_id = ? ORDER BY RAND() LIMIT 3";

        	// SELECTを実行し、結果表を取得
        	PreparedStatement pStmt = conn.prepareStatement(sql);
        	pStmt.setInt(1, targetId);
        	ResultSet rs = pStmt.executeQuery();

        	// 結果表をBeansに詰めてuserListで返す
        	while (rs.next()) {
        		MoveBeans move = new MoveBeans();
        		move.setId(rs.getInt("id"));
        		move.setMoveName(rs.getString("move_name"));
        		move.setFileName(rs.getString("file_name"));
        		move.setFileNameSub1(rs.getString("file_name_sub1"));
        		move.setFileNameSub2(rs.getString("file_name_sub2"));
        		userCheckMoveList.add(move);
        	}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return userCheckMoveList;
	}

	// 映画名から検索
	public List<MoveBeans> moveSearch(String search) {
		Connection conn = null;
		List<MoveBeans> searchMoveList = new ArrayList<MoveBeans>();
		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// SELECT文を準備
        	String sql = "SELECT * FROM move WHERE move_name LIKE ?";

        	// SELECTを実行し、結果表を取得
        	PreparedStatement pStmt = conn.prepareStatement(sql);
        	pStmt.setString(1, "%" + search + "%");
        	ResultSet rs = pStmt.executeQuery();

        	// 結果表をBeansに詰めてuserListで返す
        	while (rs.next()) {
        		MoveBeans move = new MoveBeans();
        		move.setId(rs.getInt("id"));
        		move.setMoveName(rs.getString("move_name"));
        		move.setFileName(rs.getString("file_name"));
        		move.setFileNameSub1(rs.getString("file_name_sub1"));
        		move.setFileNameSub2(rs.getString("file_name_sub2"));
        		searchMoveList.add(move);
        	}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return searchMoveList;
	}

	// idに紐づく映画情報を取得
	public MoveBeans moveFindById(String targetId) {
		Connection conn = null;
		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// SELECT文を準備
			String sql = "SELECT * FROM move WHERE id = ?";

			// SELECTを実行し、結果表を取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, targetId);
			ResultSet rs = pStmt.executeQuery();

			if (!rs.next()) {
				return null;
			}

			int id = rs.getInt("id");
			String moveName = rs.getString("move_name");
			Date releaseDate = rs.getDate("release_date");
			String moveText = rs.getString("move_text");
			Date productionDate = rs.getDate("production_date");
			int runningTime = rs.getInt("running_time");
			String country = rs.getString("country");
			String company = rs.getString("company");
			String director = rs.getString("director");
			String cast = rs.getString("cast");
			String fileName = rs.getString("file_name");
			String fileNameSub1 = rs.getString("file_name_sub1");
			String fileNameSub2 = rs.getString("file_name_sub2");
			int boxOffice = rs.getInt("box_office");
			return new MoveBeans(id, moveName, releaseDate, moveText, productionDate, runningTime, country,  company, director, cast, fileName, fileNameSub1, fileNameSub2, boxOffice);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
	}

	// ユーザIDに紐づく映画情報とユーザのレビューをListで取得
	public List<MoveBeans> moveReview(String targetId) {
		Connection conn = null;
		List<MoveBeans> moveReviewList = new ArrayList<MoveBeans>();
		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// SELECT文を準備
			String sql = "SELECT * FROM move INNER JOIN review ON move.id = review.r_move_id WHERE r_user_id = ?";

			// SELECTを実行し、結果表を取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, targetId);
			ResultSet rs = pStmt.executeQuery();

			// 結果表をBeansに詰めてmoveReviewListで返す
        	while (rs.next()) {
        		MoveBeans move = new MoveBeans();
        		move.setFileName(rs.getString("file_name"));
        		move.setFileNameSub1(rs.getString("file_name_sub1"));
        		move.setFileNameSub2(rs.getString("file_name_sub2"));
        		move.setrMoveId(rs.getInt("r_move_id"));
        		move.setReviewScore(rs.getInt("review_score"));
        		moveReviewList.add(move);
        	}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return moveReviewList;
	}
}

