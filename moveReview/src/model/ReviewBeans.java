package model;

import java.io.Serializable;

public class ReviewBeans implements Serializable {
	private int id;
	private int rUserId;
	private int rMoveId;
	private int reviewScore;
	private String reviewName;
	private String reviewContent;
	private String reviewDate;
	private String reviewUpdateDate;

	private String name;

	private int reviewCount;

	// 空のコンストラクタ
	public ReviewBeans() {

	}

	// 全データ取得のコンストラクタ
	public ReviewBeans(int id, int rUserId, int rMoveId, int reviewScore, String reviewName, String reviewContent, String reviewDate, String reviewUpdateDate) {
		this.id = id;
		this.rUserId = rUserId;
		this.rMoveId = rMoveId;
		this.reviewScore = reviewScore;
		this.reviewName = reviewName;
		this.reviewContent = reviewContent;
		this.reviewDate = reviewDate;
		this.reviewUpdateDate = reviewUpdateDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getrUserId() {
		return rUserId;
	}

	public void setrUserId(int rUserId) {
		this.rUserId = rUserId;
	}

	public int getrMoveId() {
		return rMoveId;
	}

	public void setrMoveId(int rMoveId) {
		this.rMoveId = rMoveId;
	}

	public int getReviewScorePercent() {
		return reviewScore *= 20;
	}

	public int getReviewScore() {
		return reviewScore;
	}

	public void setReviewScore(int reviewScore) {
		this.reviewScore = reviewScore;
	}

	public String getReviewName() {
		return reviewName;
	}

	public void setReviewName(String reviewName) {
		this.reviewName = reviewName;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getReviewUpdateDate() {
		return reviewUpdateDate;
	}

	public void setReviewUpdateDate(String reviewUpdateDate) {
		this.reviewUpdateDate = reviewUpdateDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

}
