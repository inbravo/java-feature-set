package com.inbravo.ds.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LicenceWriter {

  /**
   * File name prefix for temporary file.
   */
  public static String TEMP_FILE_PREFIX = "TEMP_";

  /**
   * File with license statement.
   */
  private File licenseFile;

  /**
   * String to add after every line. i.e. \r\n or just \n.
   */
  private String newLine = "\r\n";

  /**
   * License statement in license file.
   */
  private String _licenseStatement;

  /**
   * Constructor.
   */
  public LicenceWriter() {

  }

  /**
   * Constructor.
   * 
   * @param licenseFile File with license statement.
   */
  public LicenceWriter(File licenseFile) {
    this.licenseFile = licenseFile;
  }

  public void write(File file) throws Exception {
    BufferedWriter writer = null;
    BufferedReader reader = null;

    try {
      // get the new line string
      String newLine = getNewLine();

      // get the temporary file that we will write to
      File tempFile = getTemporaryFile(file);
      // create the writer that will write to the temporary file
      writer = new BufferedWriter(new FileWriter(tempFile));

      // create the reader that will read from the file
      reader = new BufferedReader(new FileReader(file));

      // write the license statement to the temporary file
      String licenseStatement = getLicenseStatement();
      writer.write(licenseStatement);
      writer.write(newLine);

      // now read in the contents of the file, and write it to the temp file
      String line = null;
      while (null != (line = reader.readLine())) {
        writer.write(line);
        writer.write(newLine);
      }

      // close the writer and reader
      writer.close();
      reader.close();

      // now read from the temp file and copy the contents to the original file
      writer = new BufferedWriter(new FileWriter(file));
      reader = new BufferedReader(new FileReader(tempFile));

      while (null != (line = reader.readLine())) {
        writer.write(line);
        writer.write(newLine);
      }

      // close the writer and reader
      writer.close();
      reader.close();

      // delete the temporary file
      tempFile.delete();
    } catch (Exception ex) {
      throw ex;
    } finally {
      if (null != writer) {
        try {
          writer.close();
          writer = null;
        } catch (Exception ex) {
        }
      }

      if (null != reader) {
        try {
          reader.close();
          reader = null;
        } catch (Exception ex) {
        }
      }
      
      System.out.println("Done writing: " + file.getName());
    }
  }

  /**
   * Get a temporary file from a file.
   * 
   * @param file File.
   * @return Temporary file.
   * @throws IOException
   */
  public File getTemporaryFile(File file) throws IOException {
    String temporaryFileName = getTemporaryFileName(file);
    File tempFile = new File(file.getParent(), temporaryFileName);
    return tempFile;
  }

  /**
   * Get file name for temporary file.
   * 
   * @param file File.
   * @return File name for temporary file.
   */
  public String getTemporaryFileName(File file) {
    StringBuffer sb = new StringBuffer();
    sb.append(TEMP_FILE_PREFIX);
    sb.append(file.getName());
    return sb.toString();
  }

  /**
   * Get license statement from license.
   * 
   * @return License statement.
   * @throws Exception
   */
  public String getLicenseStatement() throws Exception {

    StringBuffer sb = new StringBuffer();

    if (null == _licenseStatement) {
      BufferedReader reader = null;
      try {
        File licenseFile = getLicenseFile();
        reader = new BufferedReader(new FileReader(licenseFile));
        String line = null;
        while (null != (line = reader.readLine())) {
          sb.append(line);
          sb.append(getNewLine());
        }
      } catch (Exception ex) {
        throw ex;
      } finally {
        if (null != reader) {
          try {
            reader.close();
            reader = null;
          } catch (Exception ex) {
          }
        }
      }
    }
    return sb.toString();
  }

  /**
   * Get file with license statement.
   * 
   * @return the licenseFile File.
   */
  public File getLicenseFile() {
    return licenseFile;
  }

  /**
   * Set file with license statement.
   * 
   * @param licenseFile File.
   */
  public void setLicenseFile(File licenseFile) {
    this.licenseFile = licenseFile;
  }

  /**
   * Get the new line string.
   * 
   * @return New line string.
   */
  public String getNewLine() {
    return newLine;
  }

  /**
   * Set the new line string.
   * 
   * @param newLine New line string.
   */
  public void setNewLine(String newLine) {
    this.newLine = newLine;
  }

  /**
   * Main method.
   * 
   * @param args Expects 2 arguments. 1) java source directory and 2) file with license statement.
   */
  public static void main(String[] args) {

    // expects two arguments
    if (args.length != 2) {
      throw new IllegalArgumentException("Need to supply 2 arguments: " + "1) java source directory and " + "2) file with license statement.");
    }

    // java source directory is first parameter
    String directory = args[0];
    File dir = new File(directory);

    // name of file with license statement is second parameter
    String licenseFileName = args[1];
    File licenseFile = new File(licenseFileName);

    // construct the license writer
    LicenceWriter licenseWriter = new LicenceWriter(licenseFile);

    try {
      // prepend license statement to files in directory
      prependLicenseStatement(dir, licenseWriter);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Prepend license statement to file. If the file is a directory, then recurses into the
   * directory.
   * 
   * @param file File or directory which to prepend license statement.
   * @param licenseWriter License writer.
   * @throws Exception
   */
  public static void prependLicenseStatement(File file, LicenceWriter licenseWriter) throws Exception {
    // if file is a directory
    if (file.isDirectory()) {
      // get the children of this directory
      String[] fileNames = file.list();

      for (int i = 0; i < fileNames.length; i++) {
        String fileName = fileNames[i];
        File f = new File(file, fileName); // attempt to prepend license statement to child
        prependLicenseStatement(f, licenseWriter);
      }
    } else {
      // base case, if file represents a file //if file does not have java extension then return
      if (!file.getName().endsWith("java")) {
        return;
      }
      // else write license to file
      licenseWriter.write(file);
    }
  }
}
