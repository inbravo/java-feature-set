package com.inbravo.ds.file;

import java.io.File;

public class FileConv {

	private static final String mainLocation = "E:\\my_personal\\songs\\english\\new-age";

	public static void main(String[] args) {

		final File folder = new File(mainLocation);

		final File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {

			/* If a directory */
			if (listOfFiles[i].isDirectory()) {

				final File f = new File(mainLocation + "\\"
						+ listOfFiles[i].getName());

				f.renameTo(new File(mainLocation + "\\"
						+ f.getName().toLowerCase()));
			}
		}

		System.out.println("conversion is done");
	}
}
