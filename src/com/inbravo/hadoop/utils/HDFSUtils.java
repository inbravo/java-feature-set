package com.inbravo.hadoop.utils;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Logger;

import com.inbravo.log.LogFactory;

/**
 * 
 * @author amit.dixit
 *
 */
public final class HDFSUtils {

	/** The Constant LOG. */
	private static final Logger logger = LogFactory.getLog(HDFSUtils.class);

	/** The hdfs file system class */
	private static FileSystem fs = null;

	static {
		try {
			initialize();
		} catch (final Exception e) {

			logger.error("=*=Error in HDFSUtils.initialize", e);
		}
	}

	/**
	 * Initialize.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws URISyntaxException
	 */
	public static final void initialize() throws IOException, URISyntaxException {

		final Configuration conf = new Configuration();
		conf.addResource("core-site.xml");
		conf.addResource("hdfs-site.xml");
		conf.addResource("mapred-site.xml");

		fs = FileSystem.get(new java.net.URI("hdfs://localhost:9000"), conf);
	}

	/**
	 * Read file from hdfs.
	 * 
	 * @param inFilePath
	 *            the in file path
	 * @return the string
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static final String readFileFromHDFS(final String inFilePath) throws IOException {

		final StringBuffer data = new StringBuffer();
		String strLine = "";
		final Path inFile = new Path(inFilePath);
		final FSDataInputStream in = fs.open(inFile);

		if (!fs.exists(inFile)) {
			return strLine;
		}

		final BufferedReader br1 = new BufferedReader(new InputStreamReader(in));

		/* Read File Line By Line */
		while ((strLine = br1.readLine()) != null) {
			data.append(strLine);
		}

		in.close();
		return data.toString();
	}

	/**
	 * Write local file to hdfs.
	 * 
	 * @param inputLocalFilePath
	 *            the input local file path
	 * @param outFilePath
	 *            the out file path
	 * @return the string
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static String writeLocalFileToHDFS(String inputLocalFilePath, String outFilePath) throws IOException {

		Path outFile = null;
		String strLine = null;
		BufferedWriter br = null;
		BufferedReader br1 = null;
		DataInputStream in = null;
		FSDataOutputStream out = null;
		FileInputStream fstream = null;

		if (inputLocalFilePath == null || outFilePath == null || inputLocalFilePath.trim().length() < 1 || outFilePath.trim().length() < 1) {
			return null;
		}

		outFile = new Path(outFilePath);
		out = fs.create(outFile);
		br = new BufferedWriter(new OutputStreamWriter(fs.create(outFile, true)));

		// To append data to a file, use fs.append(Path f)
		try {
			fstream = new FileInputStream(inputLocalFilePath);
			in = new DataInputStream(fstream);
			br1 = new BufferedReader(new InputStreamReader(in));
			while ((strLine = br1.readLine()) != null) {
				br.write(strLine + "\n");
			}
		} catch (final Exception e) {// Catch exception if any
			logger.error(e);
		} finally {
			fstream.close();
			br1.close();
			in.close();
			br.close();
			out.close();
		}
		return outFilePath;
	}

	/**
	 * 
	 * @param paramFilePath
	 * @return
	 * @throws IOException
	 */
	public static final boolean removeFileStructureFromHDFS(final String paramFilePath) throws IOException {

		final Path pathToBeRemoved = new Path(paramFilePath);
		return fs.delete(pathToBeRemoved, true);
	}

	/**
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static final void main(final String args) throws IOException {

		System.out.println(removeFileStructureFromHDFS("/users/output-spark"));
	}
}
