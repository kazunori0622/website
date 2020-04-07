package model;

import java.io.Serializable;

public class FavoriteBeans implements Serializable {
	private int id;
	private int fUserId;
	private int fMoveId;

	private String fileName;
	private String fileNameSub1;
	private String fileNameSub2;

	private int reviewCount;

	// 空のコンストラクタ
	public FavoriteBeans() {

	}

	// 全データ取得のコンストラクタ
	public FavoriteBeans(int id, int fUserId, int fMoveId) {
		this.id = id;
		this.fUserId = fUserId;
		this.fMoveId= fMoveId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getfUserId() {
		return fUserId;
	}

	public void setfUserId(int fUserId) {
		this.fUserId = fUserId;
	}

	public int getfMoveId() {
		return fMoveId;
	}

	public void setfMoveId(int fMoveId) {
		this.fMoveId = fMoveId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileNameSub1() {
		return fileNameSub1;
	}

	public void setFileNameSub1(String fileNameSub1) {
		this.fileNameSub1 = fileNameSub1;
	}

	public String getFileNameSub2() {
		return fileNameSub2;
	}

	public void setFileNameSub2(String fileNameSub2) {
		this.fileNameSub2 = fileNameSub2;
	}

	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

}
