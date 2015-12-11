package com.inbravo.hadoop;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * 
 * @author amit.dixit
 *
 */
public final class MaxTemperatureDriver extends Configured implements Tool {

	@Override
	public final int run(final String[] args) throws Exception {

		if (args.length != 2) {
			System.err.printf("Usage: %s [generic options] <input path> <output path>", getClass().getSimpleName());
			System.exit(-1);
		}

		/* Create new hadoop job instance */
		@SuppressWarnings("deprecation")
		final Job job = new Job(super.getConf(), "Max temperature");

		/* Set job class information */
		job.setJarByClass(getClass());

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

		return job.waitForCompletion(true) ? 0 : 1;
	}

	public static final void main(final String[] args) throws Exception {

		final int exitCode = ToolRunner.run(new MaxTemperatureDriver(), args);
		System.exit(exitCode);
	}
}
