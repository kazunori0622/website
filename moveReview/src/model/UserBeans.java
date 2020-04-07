package model;

import java.io.Serializable;

public class UserBeans implements Serializable {
	private int id;
	private String loginId;
	private String name;
	private String password;
	private String createDate;
	private String updateDate;

	// 空のコンストラクタ
	public UserBeans() {

	}

	// ログインセッションを保存するためのコンストラクタ
	public UserBeans(int id, String loginId, String name) {
		this.id = id;
		this.loginId = loginId;
		this.name = name;
	}


	// 全てのデータをセットするコンストラクタ
	public UserBeans(int id, String loginId, String name, String password, String createDate,String updateDate) {
			this.id = id;
			this.loginId = loginId;
			this.name = name;
			this.password = password;
			this.createDate = createDate;
			this.updateDate = updateDate;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getLoginId() {
		return loginId;
	}


	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getCreateDate() {
		return createDate;
	}


	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}


	public String getUpdateDate() {
		return updateDate;
	}


	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

}
