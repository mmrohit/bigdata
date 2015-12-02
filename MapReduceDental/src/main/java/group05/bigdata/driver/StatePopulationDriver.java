package group05.bigdata.driver;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;

public class StatePopulationDriver extends DiverBase {
	 StatePopulationDriver() throws IOException{
		    job = Job.getInstance(new Configuration());
	    	job.setJobName("StatePopulationJob");
			job.setMapperClass(group05.bigdata.mappers.StatePopulationMapper.class);
			job.setReducerClass(group05.bigdata.reducer.StatePopulationReducer.class);
			//-------------
			outputFileName = "StatePopulation";
			i=2;
	    }
}
