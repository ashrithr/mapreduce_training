MapReduce Word Count Example with MRUnit Tests

**Packaing** (from root of project)

```
mvn package
```

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

**Execution Custom OutputFormat**

  ```
  hadoop jar mapreduce_cwt-1.0-SNAPSHOT.jar com.cloudwick.mapreduce.outputformat.FixedWidthColumnTextDriver \
    fw_output \
    fw_output2
  ```