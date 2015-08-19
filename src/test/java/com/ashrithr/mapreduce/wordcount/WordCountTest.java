package com.ashrithr.mapreduce.wordcount;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

public class WordCountTest {

    MapDriver<Object, Text, Text, IntWritable> mapDriver;
    ReduceDriver<Text,IntWritable,Text,IntWritable> reduceDriver;
    MapReduceDriver<Object, Text, Text, IntWritable, Text, IntWritable> mapReduceDriver;

    @Before
    public void setUp() {
        TokenizerMapper mapper = new TokenizerMapper();
        IntSumReducer reducer = new IntSumReducer();
        mapDriver = MapDriver.newMapDriver(mapper);
        reduceDriver = ReduceDriver.newReduceDriver(reducer);
        mapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer);
    }

    @Test
    public void testMapper() throws IOException {
        mapDriver.withInput(new LongWritable(), new Text("abc\tdef"))
              .withOutput(new Text("abc"), new IntWritable(1))
              .withOutput(new Text("def"), new IntWritable(1))
              .runTest();
    }

    @Test
    public void testReducer() throws IOException {
        List<IntWritable> values = new ArrayList<IntWritable>();
        values.add(new IntWritable(1));
        values.add(new IntWritable(1));

        reduceDriver.withInput(new Text("abc"), values)
              .withOutput(new Text("abc"), new IntWritable(2))
              .runTest();
    }

    @Test
    public void testMapReduce() throws IOException {
        mapReduceDriver.withInput(new LongWritable(), new Text("abc\tdef"))
              .withInput(new LongWritable(), new Text("def\tghi"))
              .withInput(new LongWritable(), new Text("ghi\tjkl"))
              .withInput(new LongWritable(), new Text("jkl\tabc"))
              .withOutput(new Text("abc"), new IntWritable(2))
              .withOutput(new Text("def"), new IntWritable(2))
              .withOutput(new Text("ghi"), new IntWritable(2))
              .withOutput(new Text("jkl"), new IntWritable(2))
              .runTest();

    }

}
