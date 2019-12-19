package com.inbravo.ds.file;

import java.io.File;

/**
 * This class will print file names in a folder
 * 
 * @author amit.dixit
 *
 */
public final class FileNamePrinter {

  private static final String mainLocation = "D:\\personal\\mygithub\\travel\\march-2018\\images\\t";

  public static void main(final String... args) {

    final File folder = new File(mainLocation);

    final File[] listOfFiles = folder.listFiles();

    for (int i = 0; i < listOfFiles.length; i++) {

      /* If a file */
      if (listOfFiles[i].isFile()) {

        System.out.println(listOfFiles[i].getName());
      }
    }
  }
}
