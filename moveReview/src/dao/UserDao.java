package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.UserBeans;

public class UserDao {

	// ログインIDとパスワードに紐づく「ログインIDと名前」情報を返す
	public UserBeans findByLoginInfo(String loginId, String password) {
		Connection conn = null;
		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// SELECT文を準備
			String sql = "SELECT * FROM user WHERE login_id = ? and password = ?";

			// 暗号化メソッド(※実装予定)

			// SELECTを実行し、結果表を取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, loginId);
			pStmt.setString(2, password);
			ResultSet rs = pStmt.executeQuery();

			// ログイン失敗時の処理
			if (!rs.next()) {
				return null;
			}

			// ログイン成功時の処理
			int idData = rs.getInt("id");
			String loginIdData = rs.getString("login_id");
			String nameData = rs.getString("name");
			// ログイン情報をnewしてBeansに詰めて返す
			return new UserBeans(idData, loginIdData, nameData);
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

	// 全てのユーザ情報を取得
	public List<UserBeans> findAll() {
		Connection conn = null;
		List<UserBeans> userList = new ArrayList<UserBeans>();
		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// SELECT文を準備
        	String sql = "SELECT * FROM user WHERE login_id <> 'admin'";

        	// SELECTを実行し、結果表を取得
        	PreparedStatement pStmt = conn.prepareStatement(sql);
        	ResultSet rs = pStmt.executeQuery();

        	// 結果表をBeansに詰めてuserListで返す
        	while (rs.next()) {
        		int id = rs.getInt("id");
        		String loginId = rs.getString("login_id");
        		String name = rs.getString("name");
        		String password = rs.getString("password");
        		String createDate = rs.getString("create_date");
        		String updateDate = rs.getString("update_date");
        		UserBeans user = new UserBeans(id, loginId, name, password, createDate, updateDate);
        		userList.add(user);
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
		return userList;
	}

	// ユーザ新規登録
	public void insert(String loginId, String name, String password) {
		Connection conn = null;
		try {
			// データベース接続
			conn = DBManager.getConnection();

			// INSERT文を準備
			String insertSQL = "INSERT INTO user(login_id, name, password, create_date, update_date) VALUES(?, ?, ?, now(), now())";

			// INSERTを実行
			PreparedStatement pStmt = conn.prepareStatement(insertSQL);
			pStmt.setString(1, loginId);
			pStmt.setString(2, name);
			pStmt.setString(3, password);
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

	// 既に登録されているログインIDが入力された場合
	public UserBeans findRegistered(String targetId) {
		Connection conn = null;
		UserBeans user = new UserBeans();
		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// SELECT文を準備
			String sql = "SELECT * FROM user WHERE login_id = ?";

			// SELECTを実行し、結果表を取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
        	pStmt.setString(1, targetId);
        	ResultSet rs = pStmt.executeQuery();

        	if (!rs.next()) {
        		return null;
        	}
        	user.setLoginId(rs.getString("login_id"));

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
		return user;
	}

	// 実装予定



	// idに紐づくユーザ情報を取得
	public UserBeans findById(String targetId) {
		Connection conn = null;
		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// SELECT文を準備
			String sql = "SELECT * FROM user WHERE id = ?";

			// SELECTを実行し、結果表を取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, targetId);
			ResultSet rs = pStmt.executeQuery();

			if (!rs.next()) {
				return null;
			}

			int id = rs.getInt("id");
    		String loginId = rs.getString("login_id");
    		String name = rs.getString("name");
    		String password = rs.getString("password");
    		String createDate = rs.getString("create_date");
    		String updateDate = rs.getString("update_date");
    		return new UserBeans(id, loginId, name, password, createDate, updateDate);

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

	// ユーザ情報更新
	public void update(String id, String name, String password) {
		Connection conn = null;
		try {
			// データベース接続
			conn = DBManager.getConnection();

			// UPDATE文を準備
        	String updateSQL = "UPDATE user SET name = ?, password = ?  WHERE id = ?";

        	// UPDATEを実行
        	PreparedStatement pStmt = conn.prepareStatement(updateSQL);
        	pStmt.setString(1,name);
        	pStmt.setString(2, password);
        	pStmt.setString(3, id);
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

	// ユーザ情報更新(パスワードとパスワード(確認)がどちらも空欄の場合)
	public void updatePassBlank(String id, String name) {
		Connection conn = null;
		try {
			// データベース接続
			conn = DBManager.getConnection();

			// UPDATE文を準備
        	String updateSQL = "UPDATE user SET name = ? WHERE id = ?";
        	// UPDATEを実行
        	PreparedStatement pStmt = conn.prepareStatement(updateSQL);
        	pStmt.setString(1,name);
        	pStmt.setString(2, id);
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


	// ユーザ情報削除
	public void delete(String id) {
		Connection conn = null;
		try {
			// データベース接続
			conn = DBManager.getConnection();

			// DELETE文を準備
			String deleteSQL = "DELETE FROM user WHERE id = ?";

			// UPDATEを実行
        	PreparedStatement pStmt = conn.prepareStatement(deleteSQL);
        	pStmt.setString(1, id);
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

	// ユーザ情報検索
	public List<UserBeans> UserSearch(String searchLoginId, String searchName) {
		Connection conn = null;
		List<UserBeans> userList = new ArrayList<UserBeans>();
		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// SELECT文を準備
        	String searchSQL = "SELECT * FROM user WHERE login_id <> 'admin'";

        	// ログインID(完全一致)
        	if (!searchLoginId.equals("")) {
        		searchSQL += " AND login_id = '" + searchLoginId + "'";
        	}

        	// ユーザ名(部分一致)
        	if (!searchName.equals("")) {
        		searchSQL += "AND name LIKE  '" + "%" + searchName + "%" + "'";
        	}

        	// SELECTを実行し、結果表を取得
        	Statement stmt = conn.createStatement();
        	ResultSet rs = stmt.executeQuery(searchSQL);

        	// 結果表をBeansに詰めてuserListで返す
        	while (rs.next()) {
        		int id = rs.getInt("id");
        		String loginId = rs.getString("login_id");
        		String name = rs.getString("name");
        		String password = rs.getString("password");
        		String createDate = rs.getString("create_date");
        		String updateDate = rs.getString("update_date");
        		UserBeans user = new UserBeans(id, loginId, name, password, createDate, updateDate);
        		userList.add(user);
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
		return userList;
	}

}
