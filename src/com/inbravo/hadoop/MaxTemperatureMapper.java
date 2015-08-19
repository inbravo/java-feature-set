package com.inbravo.hadoop;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * 
 * @author amit.dixit
 *
 */
public final class MaxTemperatureMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	@Override
	public final void map(final LongWritable key, final Text value, final Context context) throws IOException, InterruptedException {

		System.out.println("---Mapper---");

		/* Split the line */
		final String[] data = value.toString().split(":");

		/* Get temperature */
		int airTemperature = Integer.parseInt(data[1]);

		/* Write year:temperature on context */
		context.write(new Text(data[0]), new IntWritable(airTemperature));
	}
}
