package cau3_1;

public class Student {

	private int id;
	private String name;
	private int year;
	private double avgMark;

	public Student(int id, String name, int year, double avgMark) {
		super();
		this.id = id;
		this.name = name;
		this.year = year;
		this.avgMark = avgMark;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getYear() {
		return year;
	}

	public double getAvgMark() {
		return avgMark;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setAvgMark(double avgMark) {
		this.avgMark = avgMark;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", year=" + year + ", avgMark=" + avgMark + "]";
	}
	

}
