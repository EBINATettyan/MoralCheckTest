package beans;

public class UserInformation {
	private int id = 0;
	private int userId = 0; // ユーザID
	private int sex = 0; // 性別
	private int age = 0; // 年齢
	private int purpose = 0; // 目的
	private String machine = null; // 使用する機器
	private int useInternetTime = 0; // インターネットを使用する時間
	private String judgeExplain = null; // 説明を受けたことがあるか
	private String judgeExperience = null; // 経験をしたことがあるか

	public UserInformation(int id, int userId, int sex, int age, int purpose, String machine, int useInternetTime,
			String judgeExplain, String judgeExperience) {
		super();
		this.id = id;
		this.userId = userId;
		this.sex = sex;
		this.age = age;
		this.purpose = purpose;
		this.machine = machine;
		this.useInternetTime = useInternetTime;
		this.judgeExplain = judgeExplain;
		this.judgeExperience = judgeExperience;
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

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getPurpose() {
		return purpose;
	}

	public void setPurpose(int purpose) {
		this.purpose = purpose;
	}

	public String getMachine() {
		return machine;
	}

	public void setMachine(String machine) {
		this.machine = machine;
	}

	public int getUseInternetTime() {
		return useInternetTime;
	}

	public void setUseInternetTime(int useInternetTime) {
		this.useInternetTime = useInternetTime;
	}

	public String getJudgeExplain() {
		return judgeExplain;
	}

	public void setJudgeExplain(String judgeExplain) {
		this.judgeExplain = judgeExplain;
	}

	public String getJudgeExperience() {
		return judgeExperience;
	}

	public void setJudgeExperience(String judgeExperience) {
		this.judgeExperience = judgeExperience;
	}
}
