package cau2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileSpliter {

	public static void splitFile(String sFile, long size) throws IOException {

		File fileSource = new File(sFile);
		if (!fileSource.exists())
			return;

		if (fileSource.isFile()) {

			FileInputStream fis = new FileInputStream(fileSource);

			long totalSize = fileSource.length();
			int countFile = (int) (totalSize / size);
			long remainder = totalSize % size;
			
			
			System.out.println(totalSize);
			System.out.println(countFile);
			System.out.println(remainder);
			

			File new_folder = new File(fileSource.getParent(), "\\slpit");
			new_folder.mkdir();

			for (int i = 0; i < countFile; i++) {

				FileOutputStream fos = new FileOutputStream(new_folder.getAbsolutePath() + "\\split_file_" + (i + 1));
				for (int j = 0; j < size; j++) {

					fos.write(fis.read());
				}
				fos.close();

			}

			if (remainder > 0) {

				FileOutputStream fos = new FileOutputStream(new_folder.getAbsolutePath() + "\\split_file_end");
				int byteRead;
				while ((byteRead = fis.read()) != -1) {

					fos.write(byteRead);

				}
				fos.close();
			}
			fis.close();

		}

	}

	public static void main(String[] args) throws IOException {

		String path = "C:\\Users\\tmt01\\OneDrive\\Máy tính\\Test\\Artificial Intelligence A Modern Approach (3rd Edition).pdf";
		FileSpliter.splitFile(path, 1024 * 1000);
		
	}

}
