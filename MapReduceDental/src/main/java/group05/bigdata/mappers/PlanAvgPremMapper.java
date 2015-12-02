package group05.bigdata.mappers;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class PlanAvgPremMapper extends  Mapper<LongWritable, Text, Text, FloatWritable> {
	public void map(LongWritable lineOffSet, Text record, Context context)
			throws IOException, InterruptedException {
		
		
		String[] columnValues = record.toString().split(",");
		/*
		 * [0] State
		 * [1] County
		 * [2] Metal Level
		 * [3] Plan Name
		 * [4] Plan Type
		 * [5] Avg Premium	
		 */
		if(columnValues.length != 6){
			//Check for bad row
			return;
        }	
		String planNameKey = columnValues[3].trim();      
	    
		try{
			float avgPremiumValue = Float.parseFloat(columnValues[5].trim());
			context.write(new Text(planNameKey), new FloatWritable(avgPremiumValue));
		}
		catch(NumberFormatException e){
			return;
		}  
	}
}
