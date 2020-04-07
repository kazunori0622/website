package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.FavoriteBeans;

public class FavoriteDao {

	// お気に入り登録
	public void insert(String fUserId, String fMoveId) {
		Connection conn = null;
		try {
			// データベース接続
			conn = DBManager.getConnection();

			// INSERT文を準備
			String insertSQL = "INSERT INTO favorite(f_user_id, f_move_id) VALUES(?, ?)";

			// INSERTを実行
			PreparedStatement pStmt = conn.prepareStatement(insertSQL);
			pStmt.setString(1, fUserId);
			pStmt.setString(2, fMoveId);
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

	// 既にユーザが同じ映画情報を登録済みの場合
	public FavoriteBeans registered(String targetId, String targetId2) {
		Connection conn = null;
		FavoriteBeans favorite = new FavoriteBeans();
		try {
			// データベース接続
			conn = DBManager.getConnection();


			// SELECT文を準備
			String sql = "SELECT * FROM favorite INNER JOIN move ON favorite.f_move_id = move.id WHERE f_user_id = ? AND f_move_id = ?";

			// SELECTを実行し、結果表を取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, targetId);
			pStmt.setString(2, targetId2);
			ResultSet rs = pStmt.executeQuery();

			if (!rs.next()) {
        		return null;
        	}
			favorite.setId(rs.getInt("id"));

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
		return favorite;
	}

	// お気に入り削除
	public void delete(String targetId) {
		Connection conn = null;
		try {
			// データベース接続
			conn = DBManager.getConnection();

			// DELETE文を準備
			String deleteSQL = "DELETE FROM favorite WHERE id = ?";

			// UPDATEを実行
        	PreparedStatement pStmt = conn.prepareStatement(deleteSQL);
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

	// 気になる作品をListで取得
	public List<FavoriteBeans> mypageFavorite(String targetId) {
		Connection conn = null;
		List<FavoriteBeans> favoriteList = new ArrayList<FavoriteBeans>();
		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// SELECT文を準備
        	String sql = "SELECT * FROM favorite INNER JOIN move ON favorite.f_move_id = move.id WHERE f_user_id = ?";

        	// SELECTを実行し、結果表を取得
        	PreparedStatement pStmt = conn.prepareStatement(sql);
        	pStmt.setString(1, targetId);
        	ResultSet rs = pStmt.executeQuery();

        	// 結果表をBeansに詰めてfavoriteListで返す
        	while (rs.next()) {
        		FavoriteBeans favorite = new FavoriteBeans();
        		favorite.setfUserId(rs.getInt("f_user_id"));
        		favorite.setfMoveId(rs.getInt("f_move_id"));
        		favorite.setFileName(rs.getString("file_name"));
        		favorite.setFileNameSub1(rs.getString("file_name_sub1"));
        		favorite.setFileNameSub2(rs.getString("file_name_sub2"));
        		favoriteList.add(favorite);
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
		return favoriteList;
	}

	// 気になる作品の件数取得
	public FavoriteBeans favoriteCount(String targetId) {
		Connection conn = null;
		try {
			// データベース接続
			conn = DBManager.getConnection();

			// SELECT文を準備
			String sql = "SELECT COUNT(f_user_id) AS favoriteCount  FROM favorite WHERE f_user_id = ?";

			// SELECTを実行し、結果表を取得
	        PreparedStatement pStmt = conn.prepareStatement(sql);
	        pStmt.setString(1, targetId);
	        ResultSet rs = pStmt.executeQuery();

	        if (!rs.next()) {
	        	return null;
	        }

	        FavoriteBeans favoriteCount = new FavoriteBeans();
	        favoriteCount.setReviewCount(rs.getInt("favoriteCount"));
	    	return favoriteCount;

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
