package group05.bigdata.driver;

import org.apache.hadoop.util.ToolRunner;

public class PlanAvgPremDriver extends DiverBase {
	
	public static void main( String[] args ) throws Exception
    {
    	int code = ToolRunner.run(new PlanAvgPremDriver(), args);
        System.exit(code);
    }
	PlanAvgPremDriver(){
		job.setJobName("PlanAvgPremiumJob");
		//Set the mapper class
		job.setMapperClass(group05.bigdata.mappers.PlanAvgPremMapper.class);
		job.setReducerClass(group05.bigdata.reducer.PlanAvgPremReducer.class);
		// Set the output path of file. Timestamp embedding to prevent write failures on HDFS
		outputFileName = "PlanAvgPrem";
	}
}
