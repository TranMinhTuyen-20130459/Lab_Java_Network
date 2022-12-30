package cau2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileJoiner {

	public static void joinFile(String pathFolder, String ext) throws IOException {

		File folder = new File(pathFolder);
		if (!folder.exists())
			return;

		if (folder.isDirectory()) {

			FileOutputStream fos = new FileOutputStream(folder.getParent() + "\\joinFile" + ext);

			File[] listFiles = folder.listFiles();

			for (int i = 0; i < listFiles.length; i++) {

				File file = listFiles[i];
				FileInputStream fis = new FileInputStream(file);

				int byteRead;
				while ((byteRead = fis.read()) != -1) {

					fos.write(byteRead);

				}

				fis.close();

			}
			fos.close();
		}

	}

	public static void joinFilePro(String pathFolder, String ext) throws IOException {

		File folder = new File(pathFolder);
		if (!folder.exists())
			return;
		if (folder.isDirectory()) {

			FileOutputStream fos = new FileOutputStream(folder.getParent() + "\\joinFilePro" + ext);
			BufferedOutputStream bos = new BufferedOutputStream(fos);

			File[] listFiles = folder.listFiles();

			FileInputStream fis = null;
			for (File f : listFiles) {

				fis = new FileInputStream(f);
				BufferedInputStream bis = new BufferedInputStream(fis, 1024);
				int byteRead;
				byte[] arrbyte = new byte[1024];

				while ((byteRead = bis.read(arrbyte)) >= 0) {

					bos.write(arrbyte, 0, byteRead);
					byteRead = 0;
				}

				bis.close();
				fis.close();

			}

			bos.close();
			fos.close();

		}
	}

	public static void main(String[] args) throws IOException {

		String pathFolder = "C:\\Users\\tmt01\\OneDrive\\Máy tính\\Test\\slpit";
		// FileJoiner.joinFile(pathFolder, ".pdf");
		FileJoiner.joinFilePro(pathFolder, ".pdf");
	}

}
