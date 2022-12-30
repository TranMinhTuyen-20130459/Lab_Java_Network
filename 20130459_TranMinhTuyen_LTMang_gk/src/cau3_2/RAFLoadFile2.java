package cau3_2;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cau3_1.Student;

public class RAFLoadFile2 {

	public static Student loadInforStudent_ById2(String path, int id) throws IOException {

		Student st = null;
		try {

			RandomAccessFile raf = new RandomAccessFile(path, "r");
			int i = 4;
			while (true) {

				raf.seek(i);
				int value_id = raf.readInt();

				if (value_id == id) {

					int position_id = raf.readInt();
					raf.seek(position_id);
					st = new Student(raf.readInt(), raf.readUTF(), raf.readInt(), raf.readDouble());
					break;
				}

				i += 8;

			}

		} catch (Exception e) {
			// TODO: handle exception
			
			return null;
		}

		return st;

	}

	public static List<Student> loadStudents(String path) {

		List<Student> result = new ArrayList<>();
		return result;

	}

	public static void test_SaveInforStudentBy_Id2() throws IOException {

		String path = "C:\\Users\\tmt01\\OneDrive\\Máy tính\\Test\\test_cau3_id.txt";
		Student st1 = new Student(20130459, "Tran Minh Tuyen 1", 2002, 9.8);
		Student st2 = new Student(20130460, "Tran Minh Tuyen 2", 2002, 8.9);
		Student st3 = new Student(20130461, "Tran Minh Tuyen 3", 2002, 10);
		Student[] arrStudent = { st1, st2, st3 };
		List<Student> students = new ArrayList<>(Arrays.asList(arrStudent));

		RAFSaveFile2.saveInforStudent_ById2(path, students);

	}

	public static void test_LoadInforStudentBy_Id2() throws IOException {

		String path = "C:\\Users\\tmt01\\OneDrive\\Máy tính\\Test\\test_cau3_id.txt";

		System.out.println(RAFLoadFile2.loadInforStudent_ById2(path, 2013059));

	}

	public static void main(String[] args) throws IOException {

		test_SaveInforStudentBy_Id2();
		test_LoadInforStudentBy_Id2();
	}

}
