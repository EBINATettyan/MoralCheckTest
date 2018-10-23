package beans;

public class Result {
	private int id = 0;
	private int userId = 0; // アカウントID
	private double initialAbility = 0;
	private double ability = 0;
	private String answerAllTime = null;

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

	public double getInitialAbility() {
		return initialAbility;
	}

	public void setInitialAbility(double initialAbility) {
		this.initialAbility = initialAbility;
	}

	public double getAbility() {
		return ability;
	}

	public void setAbility(double ability) {
		this.ability = ability;
	}

	public String getAnswerAllTime() {
		return answerAllTime;
	}

	public void setAnswerAllTime(String answerAllTime) {
		this.answerAllTime = answerAllTime;
	}

	public Result(int id, int userId, double initialAbility, double ability, String answerAllTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.initialAbility = initialAbility;
		this.ability = ability;
		this.answerAllTime = answerAllTime;
	}

}