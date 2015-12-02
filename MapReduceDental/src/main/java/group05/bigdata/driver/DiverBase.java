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

public abstract class DiverBase extends Configured implements Tool{

	Job job;
	
	String outputFileName;
	//File path Argument
	int i=0;
	@Override
	public int run(String[] arg0) throws Exception {
		//*********** Job Configuration here ********************************
		//job = Job.getInstance(getConf());
		job.setJarByClass(getClass()); // Current class has map reduce configuration
		//*******************************************************************
		//********** Input File Config *****************************
		//First set the path of input file which needs to be read
		FileInputFormat.setInputPaths(job, new Path(arg0[i]));	
		//Now set the input file format type
		job.setInputFormatClass(TextInputFormat.class); //DEFAULT NO NEED TO SPECIFY
		//***********************************************************
		//**************** MAPPER RELATED CODE ***************************
		//Set mapper  class output key type
		job.setMapOutputKeyClass(Text.class);
		//Set mapper  class output value type
		job.setMapOutputValueClass(FloatWritable.class);		
		//****************************************************************
		//*************** REDUCER CONFIGURATION *****************************
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FloatWritable.class);
		//*******************************************************************
		//************* OUTPUT FILE CONFIGURATION *******************************************
		job.setOutputFormatClass(TextOutputFormat.class); //DEFAULT NO NEED TO SPECIFY
		FileOutputFormat.setOutputPath(job,new Path(arg0[0]+outputFileName+new SimpleDateFormat("MMddhhmm'.txt'").format(new Date())));
		//***********************************************************************************
		// Return only after map-reduce completion
		return job.waitForCompletion(true) ? 0 : 1;
	}
	public String getOutputFileName() {
		return outputFileName;
	}
	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}
}
