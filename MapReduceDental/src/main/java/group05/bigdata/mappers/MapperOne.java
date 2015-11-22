package group05.bigdata.mappers;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
//                   Mapper<input key type, input value type, output key type, output value type>
public class MapperOne extends  Mapper<LongWritable, Text, Text, FloatWritable> {
	
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
		String stateKey = columnValues[0].trim();      
	    
		try{
		float avgPremiumValue = Float.parseFloat(columnValues[5].trim());
		context.write(new Text(stateKey), new FloatWritable(avgPremiumValue));
		}
		catch(NumberFormatException e){
			return;
		}
	   
		}
	}
