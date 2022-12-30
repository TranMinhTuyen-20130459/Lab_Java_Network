package ltm.socket.tuan2.loginTCP;

public class Student {

	int id;
	String name;
	int bYear;
	double score;
	public Student(int id, String name, int bYear, double score) {
		super();
		this.id = id;
		this.name = name;
		this.bYear = bYear;
		this.score = score;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getbYear() {
		return bYear;
	}
	public void setbYear(int bYear) {
		this.bYear = bYear;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "id= " + id + ", name= " + name + ", bYear= " + bYear + ", score= " + score +"\n";
	}
	
	
}
