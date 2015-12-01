package group05.bigdata.mappers;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StatePopulationMapper extends  Mapper<LongWritable, Text, Text, LongWritable> {
	
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
			long population = Long.parseLong(columnValues[2].trim());
			context.write(new Text(stateKey), new LongWritable(population));
		}
		catch(NumberFormatException e){
			return;
		}
	}
}
