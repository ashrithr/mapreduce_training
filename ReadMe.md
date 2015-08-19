# MapReduce Training

This repository is collection of MapReduce and Hadoop eco-system related programs:

1. MapReduce WordCount application with MRUnit tests
2. MapReduce custom Writable implementation for String pair's
3. MapReduce custom InputFormat and RecordReader implementation
4. MapReduce custom OutputFormat and RecordWriter implementation
5. Pig custom LoadFunc to load and parse apache http log events
6. Pig custom EvalFunc to transform IP addresses to location using MaxMind GEO API

Also, look into [MapReduce Joins](https://github.com/ashrithr/mapreduce_joins) on how to implement
MapReduce joins.
 
### Packaging repository

From root of this project run:

```
mvn package
```

### Running

**Executing WordCount**

```
hadoop jar mapreduce_cwt-1.0-SNAPSHOT.jar com.cloudwick.mapreduce.wordcount.WordCountDriver \
  input_path \
  output_path
```

**Executing Custom InputFormat**

1. Copy test data from `src/main/resources/columntext-testdata` to hadoop cluster
2. Move the file to HDFS

  ```
  hadoop fs -mkdir fw_input
  hadoop fs -put columntext-testdata fw_input
  ```

3. Execute the MapReduce program to parse the fixed width records

  ```
  hadoop jar mapreduce_cwt-1.0-SNAPSHOT.jar com.cloudwick.mapreduce.inputformat.FixedWidthColumnTextDriver \
    fw_input \
    fw_output
  ```

**Executing Custom OutputFormat**

  ```
  hadoop jar mapreduce_cwt-1.0-SNAPSHOT.jar com.cloudwick.mapreduce.outputformat.FixedWidthColumnTextDriver \
    fw_output \
    fw_output2
  ```