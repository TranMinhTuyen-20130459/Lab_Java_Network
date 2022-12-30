package test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cau3_1.RAFLoadFile;
import cau3_1.RAFSaveFile;
import cau3_1.Student;

public class TestCau3 {

	public static void test_SaveInforStudent_ByIndex() throws IOException {

		String path = "C:\\Users\\tmt01\\OneDrive\\Máy tính\\Test\\test_cau3_index.txt";

		Student st1 = new Student(20130459, "Tran Minh Tuyen 1", 2002, 9.8);
		Student st2 = new Student(20130460, "Tran Minh Tuyen 2", 2002, 8.9);
		Student st3 = new Student(20130461, "Tran Minh Tuyen 3", 2002, 8.0);
		Student st4 = new Student(20130462, "Tran Minh Tuyen 4", 2003, 10.0);
		Student[] arrStudent = { st1, st2, st3, st4 };
		List<Student> listStudents = new ArrayList<>(Arrays.asList(arrStudent));

		RAFSaveFile.saveInforStudent_ByIndex(path, listStudents);

	}

	public static void test_loadInforStudent_ByIndex() throws IOException {

		String path = "C:\\Users\\tmt01\\OneDrive\\Máy tính\\Test\\test_cau3_index.txt";
		Student st = RAFLoadFile.loadInforStudent_ByIndex(path, 3);
		System.out.println(st);

	}

	public static void test_SaveInforStudentBy_Id() throws IOException {

		String path = "C:\\Users\\tmt01\\OneDrive\\Máy tính\\Test\\test_cau3_id.txt";
		Student st1 = new Student(20130459, "Tran Minh Tuyen 1", 2002, 9.8);
		Student st2 = new Student(20130460, "Tran Minh Tuyen 2", 2002, 8.9);
		Student st3 = new Student(20130461, "Tran Minh Tuyen 3", 2002, 10);
		Student[] arrStudent = { st1, st2, st3 };
		List<Student> students = new ArrayList<>(Arrays.asList(arrStudent));

		RAFSaveFile.saveInforStudent_ById(path, students);

	}

	public static void test_LoadInforStudentBy_Id() throws IOException {

		String path = "C:\\Users\\tmt01\\OneDrive\\Máy tính\\Test\\test_cau3_id.txt";

		System.out.println(RAFLoadFile.loadInforStudent_ById(path, 20130459));

	}

	public static void main(String[] args) throws IOException {

//		test_SaveInforStudent_ByIndex();
//		test_loadInforStudent_ByIndex();

		test_SaveInforStudentBy_Id();
		test_LoadInforStudentBy_Id();

	}

}
