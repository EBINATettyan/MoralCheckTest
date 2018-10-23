package beans;

public class QuestionType {
	private int id = 0;
	private int type = 0;

	public QuestionType(int id, int type) {
		this.id = id;
		this.type = type;
	}

	public QuestionType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
