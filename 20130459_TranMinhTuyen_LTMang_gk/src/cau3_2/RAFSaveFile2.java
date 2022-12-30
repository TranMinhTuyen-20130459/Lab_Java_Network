package cau3_2;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import cau3_1.Student;

public class RAFSaveFile2 {

	public static void saveInforStudent_ById2(String path, List<Student> listStudents) throws IOException {

		RandomAccessFile raf = new RandomAccessFile(path, "rw");
		int size = listStudents.size();
		raf.writeInt(size);
		for (int i = 0; i < (size * 2); i++) {

			raf.writeInt(0);

		}

		List<Integer> list_id = new ArrayList<>();
		List<Integer> position_id = new ArrayList<>();

		for (Student st : listStudents) {

			position_id.add((int) raf.getFilePointer());
			int id = st.getId();
			list_id.add(id);

			raf.writeInt(id);
			raf.writeUTF(st.getName());
			raf.writeInt(st.getYear());
			raf.writeDouble(st.getAvgMark());

		}
		raf.seek(4);
		for (int i = 0; i < list_id.size(); i++) {

			raf.writeInt(list_id.get(i)); // 4b
			raf.writeInt(position_id.get(i)); // 4sb

		}

		raf.close();

	}

	public static void main(String[] args) throws IOException {
		
		RandomAccessFile raf = new RandomAccessFile("C:\\Users\\tmt01\\OneDrive\\Máy tính\\Test\\text.txt", "rw");
		raf.writeInt(3);
		System.out.println(raf.getFilePointer());
		raf.writeLong(10000);
		System.out.println(raf.getFilePointer());
		raf.writeDouble(9.8);
		System.out.println(raf.getFilePointer());
		raf.seek(4);
		raf.writeInt(123);
		raf.seek(4);
		System.out.println(raf.readInt());
		raf.seek(12);
		System.out.println(raf.readDouble());

	}

}
