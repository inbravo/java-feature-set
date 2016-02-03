package com.inbravo.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * 
 * @author amit.dixit
 *
 */
public final class LogFactory {

	private static AtomicBoolean initialized = new AtomicBoolean(false);
	private static final String logConfigFile = "inbravo-log4j.properties";
	private static final String versionConfigFile = "inbravo-version.txt";
	private static String releaseVersion;

	/**
	 * 
	 */
	private static final void init() {
		try {
			/* Get file locations */
			final URL loggingFileLocation = LogFactory.class.getClassLoader().getResource(logConfigFile);
			System.out.println("==========================================================================================");
			System.out.println("=== Release : '" + getReleaseVersion() + "'");
			System.out.println("=== Logging file : '" + loggingFileLocation + "'");
			System.out.println("==========================================================================================");

			/* Configure Log4j */
			PropertyConfigurator.configure(loggingFileLocation);

			/* Log4j is initialized */
			initialized.getAndSet(true);

		} catch (final Exception e) {
			e.printStackTrace();
			System.err.println("***Exception in initialization of PortPS System services " + e);
		}
	}

	/**
	 * 
	 * @param versionFileLocation
	 * @return
	 * @throws IOException
	 */
	private static final String getReleaseVersion() throws IOException {
		BufferedReader bReader = null;
		InputStreamReader inputStreamReader = null;
		try {
			/* Get byte code version information */
			inputStreamReader = new InputStreamReader(LogFactory.class.getResourceAsStream("/" + versionConfigFile));
			bReader = new BufferedReader(inputStreamReader);

			/* Read version */
			releaseVersion = bReader.readLine();

			return releaseVersion;

		} finally {
			if (bReader != null) {
				/* Close the reader */
				bReader.close();
			}

			if (inputStreamReader != null) {
				/* Close the reader */
				inputStreamReader.close();
			}
		}
	}

	/**
	 * 
	 * @param clazz
	 * @return
	 */
	public static final Logger getLog(@SuppressWarnings("rawtypes") final Class clazz) {

		if (!initialized.get()) {
			init();
		}

		return LogFactory.getLog(clazz);
	}
}
