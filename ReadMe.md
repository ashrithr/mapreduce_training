MapReduce Word Count Example with MRUnit Tests

**Packaing** (from root of project)

```
mvn package
```

**Executing**

```
hadoop jar wordcount-1.0-SNAPSHOT.jar com.cloudwick.mapreduce.WordCountDriver \
  input_path \
  output_path
```
