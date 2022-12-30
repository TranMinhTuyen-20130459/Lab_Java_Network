package cau3_1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class RAFLoadFile {

	public static Student loadInforStudent_ByIndex(String path, int index) throws IOException {

		Student st = null;
		RandomAccessFile raf = new RandomAccessFile(path, "r");
		long position = 4 + (8 * index);
		raf.seek(position);
		long value_position = raf.readLong();
		raf.seek(value_position);
		st = new Student(raf.readInt(), raf.readUTF(), raf.readInt(), raf.readDouble());
		raf.close();
		return st;

	}

	public static Student loadInforStudent_ById(String path, int id) throws IOException {

		Student st = null;
		RandomAccessFile raf = new RandomAccessFile(path, "r");
		int i = 4;
		while (i < 999999) {

			raf.seek(i);
			int value_id = raf.readInt();

			if (value_id == id) {

				long position_id = raf.readLong();
				raf.seek(position_id);
				st = new Student(raf.readInt(), raf.readUTF(), raf.readInt(), raf.readDouble());
				break;
			}

			i += 12;

		}
		return st;

	}

	public static List<Student> loadStudents(String path) {

		List<Student> result = new ArrayList<>();
		return result;

	}

}
