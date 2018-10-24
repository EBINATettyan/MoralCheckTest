package beans;

public class AccessLog {
	private int id = 0;
	private int userId = 0; // ユーザID
	private String startTime = null; // 開始時間
	private String endTime = null; // 終了時間
	private double answerAllTime = 0; // 合計時間
	private String itemList = null; // 問いた項目のリスト

	public AccessLog(int id, int userId, String startTime, String endTime, double answerAllTime, String itemList) {
		super();
		this.id = id;
		this.userId = userId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.answerAllTime = answerAllTime;
		this.itemList = itemList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public double getAnswerAllTime() {
		return answerAllTime;
	}

	public void setAnswerAllTime(double answerAllTime) {
		this.answerAllTime = answerAllTime;
	}

	public String getItemList() {
		return itemList;
	}

	public void setItemList(String itemList) {
		this.itemList = itemList;
	}

}
