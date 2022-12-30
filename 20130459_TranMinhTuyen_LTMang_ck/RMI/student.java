package Cau_25;

import java.io.Serializable;

public class student implements Serializable{
	int id_student;
	String full_name;
	double score;
	int age;
	public student(int id_student, String full_name, double score, int age) {
		this.id_student = id_student;
		this.full_name = full_name;
		this.score = score;
		this.age = age;
	}
	public int getId_student() {
		return id_student;
	}
	public void setId_student(int id_student) {
		this.id_student = id_student;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Students [id_student=" + id_student + ", full_name=" + full_name + ", score=" + score + ", age="
				+ age + "]";
	}
}
