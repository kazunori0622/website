package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ReviewBeans;

public class ReviewDao {

	// レビュー新規登録
	public void insert(String rUserId, String rMoveId, String reviewScore, String reviewName, String reviewContent) {
		Connection conn = null;
		try {
			// データベース接続
			conn = DBManager.getConnection();

			// INSERT文を準備
			String insertSQL = "INSERT INTO review(r_user_id, r_move_id, review_score, review_name, review_content, review_date, review_update_date) VALUES(?, ?, ?, ?, ?, now(), now())";

			// INSERTを実行
			PreparedStatement pStmt = conn.prepareStatement(insertSQL);
			pStmt.setString(1, rUserId);
			pStmt.setString(2, rMoveId);
			pStmt.setString(3, reviewScore);
			pStmt.setString(4, reviewName);
			pStmt.setString(5, reviewContent);
			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// レビュー更新
	public void update(String id, String reviewScore, String reviewName, String reviewContent) {
		Connection conn = null;
		try {
			// データベース接続
			conn = DBManager.getConnection();

			// UPDATE文を準備
			String SQL = "UPDATE review SET review_score = ?, review_name = ?, review_content = ?, review_update_date = now() WHERE id = ?";

			// INSERTを実行
			PreparedStatement pStmt = conn.prepareStatement(SQL);
			pStmt.setString(1, reviewScore);
			pStmt.setString(2, reviewName);
			pStmt.setString(3, reviewContent);
			pStmt.setString(4, id);
			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// レビュー削除
	public void delete(String targetId) {
		Connection conn = null;
		try {
			// データベース接続
			conn = DBManager.getConnection();

			// DELETE文を準備
			String SQL = "DELETE FROM review WHERE id = ?";

			// INSERTを実行
			PreparedStatement pStmt = conn.prepareStatement(SQL);
			pStmt.setString(1, targetId);
			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 映画idに紐づくレビュー一覧をListで取得
	public List<ReviewBeans> reviewFindByIdAll(String targetId) {
		Connection conn = null;
		List<ReviewBeans> reviewList = new ArrayList<ReviewBeans>();
		try {
			// データベース接続
			conn = DBManager.getConnection();

			// SELECT文を準備
			String sql = "SELECT * from review INNER JOIN user ON review.r_user_id=user.id WHERE r_move_id= ?";

			// SELECTを実行し、結果表を取得
        	PreparedStatement pStmt = conn.prepareStatement(sql);
        	pStmt.setString(1, targetId);
        	ResultSet rs = pStmt.executeQuery();

        	// 結果表をBeansに詰めてreviewListで返す
        	while (rs.next()) {
        		ReviewBeans review = new ReviewBeans();
        		review.setId(rs.getInt("id"));
        		review.setrUserId(rs.getInt("r_user_id"));
        		review.setrMoveId(rs.getInt("r_move_id"));
        		review.setReviewScore(rs.getInt("review_score"));
        		review.setReviewName(rs.getString("review_name"));
        		review.setReviewContent(rs.getString("review_content"));
        		review.setReviewDate(rs.getString("review_date"));
        		review.setReviewUpdateDate(rs.getString("review_update_date"));
        		review.setName(rs.getString("name"));
        		reviewList.add(review);

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
		return reviewList;
	}

	// 平均評価を取得
	public double reviewFindByIdAvg(int targetId) {
		Connection conn = null;
		try {
			// データベース接続
			conn = DBManager.getConnection();

			// SELECT文を準備
			String sql = "SELECT AVG(review_score) AS score FROM review WHERE r_move_id = ? GROUP BY r_move_id";

			// SELECTを実行し、結果表を取得
        	PreparedStatement pStmt = conn.prepareStatement(sql);
        	pStmt.setInt(1, targetId);
        	ResultSet rs = pStmt.executeQuery();

        	if (!rs.next()) {
        		return 0;
        	}

        	return rs.getDouble("score");

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return 0;
				}
			}
		}
	}

	// レビューidと映画idに紐づくレビューを取得
	public ReviewBeans reviewFindById(String reviewId) {
		Connection conn = null;
		try {
			// データベース接続
			conn = DBManager.getConnection();

			// SELECT文を準備
			String sql = "SELECT * from review WHERE id= ?";

			// SELECTを実行し、結果表を取得
        	PreparedStatement pStmt = conn.prepareStatement(sql);
        	pStmt.setString(1, reviewId);
        	ResultSet rs = pStmt.executeQuery();

        	if (!rs.next()) {
        		return null;
        	}

        	ReviewBeans review = new ReviewBeans();
        	review.setId(rs.getInt("id"));
        	review.setrUserId(rs.getInt("r_user_id"));
        	review.setrMoveId(rs.getInt("r_move_id"));
        	review.setReviewScore(rs.getInt("review_score"));
        	review.setReviewName(rs.getString("review_name"));
        	review.setReviewContent(rs.getString("review_content"));
    		return review;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
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

	// レビュー件数取得
	public ReviewBeans reviewCount(String targetId) {
		Connection conn = null;
		try {
			// データベース接続
			conn = DBManager.getConnection();

			// SELECT文を準備
			String sql = "SELECT COUNT(r_user_id) AS reviewCount FROM review WHERE r_user_id = ?";

			// SELECTを実行し、結果表を取得
        	PreparedStatement pStmt = conn.prepareStatement(sql);
        	pStmt.setString(1, targetId);
        	ResultSet rs = pStmt.executeQuery();

        	if (!rs.next()) {
        		return null;
        	}

        	ReviewBeans reviewCount = new ReviewBeans();
        	reviewCount.setReviewCount(rs.getInt("reviewCount"));
    		return reviewCount;

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
}
