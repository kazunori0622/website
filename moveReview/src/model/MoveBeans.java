package model;

import java.io.Serializable;
import java.util.Date;

import dao.ReviewDao;

public class MoveBeans implements Serializable {
	private int id;
	private String moveName;
	private Date releaseDate;
	private String moveText;
	private Date productionDate;
	private int runningTime;
	private String country;
	private String company;
	private String director;
	private String cast;
	private String fileName;
	private String fileNameSub1;
	private String fileNameSub2;
	private int boxOffice;

	private int rMoveId;
	private int reviewScore;

	// 空のコンストラクタ
	public MoveBeans() {

	}

	// 全データ取得のコンストラクタ
	public MoveBeans(int id, String moveName, Date releaseDate, String moveText, Date productionDate, int runningTime, String country, String company, String director, String cast, String fileName, String fileNameSub1, String fileNameSub2, int boxOffice) {
			this.id = id;
			this.moveName = moveName;
			this.releaseDate = releaseDate;
			this.moveText = moveText;
			this.productionDate = productionDate;
			this.runningTime = runningTime;
			this.country = country;
			this.company =  company;
			this.director = director;
			this.cast = cast;
			this.fileName = fileName;
			this.fileNameSub1 = fileNameSub1;
			this.fileNameSub2 = fileNameSub2;
			this.boxOffice = boxOffice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMoveName() {
		return moveName;
	}

	public void setMoveName(String moveName) {
		this.moveName = moveName;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getMoveText() {
		return moveText;
	}

	public void setMoveText(String moveText) {
		this.moveText = moveText;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	public int getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
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

	public int getBoxOffice() {
		return boxOffice;
	}

	public void setBoxOffice(int boxOffice) {
		this.boxOffice = boxOffice;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public int getrMoveId() {
		return rMoveId;
	}

	public void setrMoveId(int rMoveId) {
		this.rMoveId = rMoveId;
	}

	// 結合テーブル情報
	public int getReviewScoreAvg() {
		ReviewDao dao = new ReviewDao();
		return (int)(dao.reviewFindByIdAvg(this.id)/ 5 * 100);
	}

}
