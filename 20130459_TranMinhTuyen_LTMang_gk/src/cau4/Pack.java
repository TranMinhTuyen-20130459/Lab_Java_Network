package cau4;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Pack {

	public static boolean pack(String sFolder, String dFile) throws IOException {

		File srcFolder = new File(sFolder);
		try {
			if (!srcFolder.exists())
				return false;
			else if (srcFolder.isDirectory()) {

				if (srcFolder.length() > 0) {
					RandomAccessFile raf = new RandomAccessFile(dFile, "rw");
					File[] listFiles = srcFolder.listFiles();

					for (int i = 1; i <= listFiles.length; i++) {

						File f = listFiles[i - 1];
						RandomAccessFile raf_2 = new RandomAccessFile(f, "rw");

						long nextEntry = (i == listFiles.length) ? 0 : i + 1;
						long fileSize = f.length();
						String fileName = f.getName();

						raf.writeLong(nextEntry);
						raf.writeLong(fileSize);
						raf.writeUTF(fileName);

						int byteRead;
						while ((byteRead = raf_2.read()) != -1) {

							raf.write(byteRead);

						}
						raf_2.close();

					}
					raf.close();
					return true;
					
				}

			}

		} catch (Exception e) {
			// TODO: handle exception

			System.out.println("Kiem tra lai folder nguon");
		}
		return false;
	}
	
	public static boolean packPro(String sFolder, String dFile) throws IOException {

		File srcFolder = new File(sFolder);
		try {
			if (!srcFolder.exists())
				return false;
			else if (srcFolder.isDirectory()) {

				if (srcFolder.length() > 0) {
					RandomAccessFile raf = new RandomAccessFile(dFile, "rw");
					File[] listFiles = srcFolder.listFiles();

					for (int i = 1; i <= listFiles.length; i++) {

						File f = listFiles[i - 1];
						RandomAccessFile raf_2 = new RandomAccessFile(f, "rw");

						long nextEntry = (i == listFiles.length) ? 0 : i + 1;
						long fileSize = f.length();
						String fileName = f.getName();

						raf.writeLong(nextEntry);
						raf.writeLong(fileSize);
						raf.writeUTF(fileName);

						int byteRead;
						byte[] arrByte= new byte[1024];
						while ((byteRead = raf_2.read(arrByte)) > 0 ) {

							raf.write(arrByte,0,byteRead);

						}
						raf_2.close();

					}
					raf.close();
					return true;
					
				}

			}

		} catch (Exception e) {
			// TODO: handle exception

			System.out.println("Kiem tra lai folder nguon");
		}
		return false;
	}

}
