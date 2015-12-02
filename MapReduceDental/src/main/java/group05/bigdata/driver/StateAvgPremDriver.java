package group05.bigdata.driver;

public class StateAvgPremDriver extends DiverBase
{
    
    StateAvgPremDriver(){
    	job.setJobName("StateAvgPremiumJob");
		job.setMapperClass(group05.bigdata.mappers.StateAvgPremMapper.class);
		job.setReducerClass(group05.bigdata.reducer.StateAvgPremReducer.class);
		outputFileName = "StateAvgPrem";
    }

/*	public int run(String[] arg0) throws Exception {
		
		//*********** Job Configuration here ********************************
		Job job = Job.getInstance(getConf());
		job.setJarByClass(getClass()); // Current class has map reduce configuration
		
		//*******************************************************************
		
		//********** Input File Config *****************************
		//First set the path of input file which needs to be read
		FileInputFormat.setInputPaths(job, new Path(arg0[0]));	
		//Now set the input file format type
		job.setInputFormatClass(TextInputFormat.class); //DEFAULT NO NEED TO SPECIFY
		//***********************************************************
		
		//**************** MAPPER RELATED CODE ***************************
		//Set the mapper class
		job.setMapperClass(group05.bigdata.mappers.StateAvgPremMapper.class);
		//Set mapper  class output key type
		job.setMapOutputKeyClass(Text.class);
		//Set mapper  class output value type
		job.setMapOutputValueClass(FloatWritable.class);		
		//****************************************************************
		
		//*************** REDUCER CONFIGURATION *****************************
		job.setReducerClass(group05.bigdata.reducer.StateAvgPremReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FloatWritable.class);
		//*******************************************************************
		
		//************* OUTPUT FILE CONFIGURATION *******************************************
		job.setOutputFormatClass(TextOutputFormat.class); //DEFAULT NO NEED TO SPECIFY
		// Set the output path of file. Timestamp embedding to prevent write failures on HDFS
		FileOutputFormat.setOutputPath(job,new Path(arg0[1]+"StateAveragePremium"+new SimpleDateFormat("MMddhhmm'.txt'").format(new Date())));
		
		//***********************************************************************************
		
		// Return only after map-reduce completion
		return job.waitForCompletion(true) ? 0 : 1;
	}*/

}
