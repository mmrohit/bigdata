package group05.bigdata.mappers;

import java.io.IOException;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StateTaxMapper  extends  Mapper<LongWritable, Text, Text, FloatWritable> {
	public void map(LongWritable lineOffSet, Text record, Context context)
			throws IOException, InterruptedException {
		
		String[] columnValues = record.toString().split(",");
		/*
		 * [0] State
		 * [1] Base Tax
		 * [2] Surplus Tax	
		 */
		if(columnValues.length != 3){
			//Check for bad row
			return;
        }	
		String stateKey = columnValues[0].trim();      
	    
		try{
			float taxValue = Float.parseFloat(columnValues[1].trim());
			context.write(new Text(stateKey), new FloatWritable(taxValue));
		}
		catch(NumberFormatException e){
			return;
		}  
	}
}
