package com.inbravo.hadoop;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 
 * @author amit.dixit
 *
 */
public final class MaxTemperature {

	@SuppressWarnings("deprecation")
	public final static void main(final String[] args) throws Exception {

		if (args.length != 2) {
			System.err.println("Usage: MaxTemperature <input path> <output path>");
			System.exit(-1);
		}

		/* Create new hadoop job instance */
		final Job job = new Job();

		/* Set job class information */
		job.setJarByClass(MaxTemperature.class);
		job.setJobName("Max temperature");

		/* Add file type input/output format */
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		/* Set mapper */
		job.setMapperClass(MaxTemperatureMapper.class);

		/* Set combiner */
		job.setCombinerClass(MaxTemperatureReducer.class);

		/* Set reducer */
		job.setReducerClass(MaxTemperatureReducer.class);

		/* Set output information */
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
