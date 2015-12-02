package group05.bigdata.mappers;

import java.io.IOException;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StatePopulationMapper extends  Mapper<LongWritable, Text, Text, FloatWritable> {
	
	public void map(LongWritable lineOffSet, Text record, Context context)
			throws IOException, InterruptedException {
		
		
		String[] columnValues = record.toString().split(",");
		/*
		 * [0] State
		 * [1] County
		 * [2] Population
		 */
		if(columnValues.length != 3){
			//Check for bad row
			return;
        }	
		String stateKey = columnValues[0].trim();      
		try{
			float population = Float.parseFloat(columnValues[2].trim());
			context.write(new Text(stateKey), new FloatWritable(population));
		}
		catch(NumberFormatException e){
			return;
		}
	}
}
