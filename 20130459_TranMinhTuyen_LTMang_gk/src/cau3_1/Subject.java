package cau3_1;

public class Subject {

	private String id;
	private String name;
	private int credit;
	private double score;

	public Subject(String id, String name, int credit, double score) {
		super();
		this.id = id;
		this.name = name;
		this.credit = credit;
		this.score = score;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getCredit() {
		return credit;
	}

	public double getScore() {
		return score;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public void setScore(double score) {
		this.score = score;
	}

}
