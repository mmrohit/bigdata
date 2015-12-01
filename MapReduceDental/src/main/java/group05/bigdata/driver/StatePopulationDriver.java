package group05.bigdata.driver;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.util.ToolRunner;

public class StatePopulationDriver extends DiverBase {

	 public static void main( String[] args ) throws Exception
	    {
	    	int code = ToolRunner.run(new StatePopulationDriver(), args);
	        System.exit(code);
	    }
	 StatePopulationDriver(){
	    	job.setJobName("StatePopulationJob");
			job.setMapperClass(group05.bigdata.mappers.StatePopulationMapper.class);
			job.setReducerClass(group05.bigdata.reducer.StatePopulationReducer.class);
			//Different for this map-reduce
			job.setMapOutputValueClass(LongWritable.class);
			job.setOutputValueClass(LongWritable.class);
			//-------------
			outputFileName = "StatePopulation";
	    }
}
