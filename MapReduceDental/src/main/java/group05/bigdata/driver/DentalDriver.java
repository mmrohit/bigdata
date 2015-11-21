package group05.bigdata.driver;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class DentalDriver extends Configured implements Tool
{
    public static void main( String[] args ) throws Exception
    {
    	int code = ToolRunner.run(new DentalDriver(), args);
        System.exit(code);
    }

	public int run(String[] arg0) throws Exception {
		
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
		job.setMapperClass(group05.bigdata.mappers.DentalPlanMapper.class);
		//Set mapper  class output key type
		job.setMapOutputKeyClass(Text.class);
		//Set mapper  class output value type
		job.setMapOutputValueClass(FloatWritable.class);		
		//****************************************************************
		
		//*************** REDUCER CONFIGURATION *****************************
		job.setReducerClass(group05.bigdata.reducer.RecommendReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FloatWritable.class);
		//*******************************************************************
		
		//************* OUTPUT FILE CONFIGURATION *******************************************
		job.setOutputFormatClass(TextOutputFormat.class); //DEFAULT NO NEED TO SPECIFY
		// Set the output path of file. Timestamp embedding to prevent write failures on HDFS
		FileOutputFormat.setOutputPath(job,new Path(arg0[1]+new SimpleDateFormat("yyyyMMddhhmm'.txt'").format(new Date())));
		
		//***********************************************************************************
		
		// Return only after map-reduce completion
		return job.waitForCompletion(true) ? 0 : 1;
	}

}
