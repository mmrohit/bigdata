package group05.bigdata.driver;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.ToolRunner;

public class StateTaxDriver extends DiverBase{

	public static void main( String[] args ) throws Exception
    {
    	int code = ToolRunner.run(new StateTaxDriver(), args);
        System.exit(code);
    }
	StateTaxDriver() throws IOException{
		job = Job.getInstance(new Configuration());
		job.setJobName("StateTaxJob");
		//Set the mapper class
		job.setMapperClass(group05.bigdata.mappers.StateTaxMapper.class);
		job.setReducerClass(group05.bigdata.reducer.StateTaxReducer.class);
		// Set the output path of file. Timestamp embedding to prevent write failures on HDFS
		outputFileName = "StateTax";
		i=3;
	}
}
