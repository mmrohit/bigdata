package group05.bigdata.driver;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.ToolRunner;

public class PlanAvgPremDriver extends DiverBase {
	
	public static void main( String[] args ) throws Exception
    {
    	int code = ToolRunner.run(new PlanAvgPremDriver(), args);
        System.exit(code);
    }
	PlanAvgPremDriver() throws IOException{
		job = Job.getInstance(new Configuration());
		job.setJobName("PlanAvgPremiumJob");
		//Set the mapper class
		job.setMapperClass(group05.bigdata.mappers.PlanAvgPremMapper.class);
		job.setReducerClass(group05.bigdata.reducer.PlanAvgPremReducer.class);
		// Set the output path of file. Timestamp embedding to prevent write failures on HDFS
		outputFileName = "PlanAvgPrem";
		i=1;
	}
}
