package com.inbravo.hadoop;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * 
 * @author amit.dixit
 *
 */
public final class MaxTemperatureReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

  @Override
  public final void reduce(final Text key, final Iterable<IntWritable> values, final Context context) throws IOException, InterruptedException {

    System.out.println("---Reducer---");

    int maxValue = Integer.MIN_VALUE;

    for (final IntWritable value : values) {

      /* Find max temperature */
      maxValue = Math.max(maxValue, value.get());
    }

    /* Write year:temperature on context */
    context.write(key, new IntWritable(maxValue));
  }
}
