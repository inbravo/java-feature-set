package com.inbravo.ds.file;

import java.io.File;

public class FileConv {

  private static final String mainLocation = "D:\\personal\\songs\\classical-songs";

  public static void main(String[] args) {

    final File folder = new File(mainLocation);

    final File[] listOfFiles = folder.listFiles();

    for (int i = 0; i < listOfFiles.length; i++) {

      /* If a file */
      if (listOfFiles[i].isFile()) {

        final File f = new File(mainLocation + "\\" + listOfFiles[i].getName());

        f.renameTo(new File(mainLocation + "\\" + f.getName().toLowerCase().replaceAll(" ", "")));
      }
    }

    System.out.println("conversion is done");
  }
}
