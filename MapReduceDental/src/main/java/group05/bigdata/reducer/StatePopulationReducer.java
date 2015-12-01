package group05.bigdata.reducer;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class StatePopulationReducer extends Reducer <Text, LongWritable, Text, LongWritable>{
	
	public void reduce(Text key, Iterable<LongWritable> values,
            Context context) throws IOException, InterruptedException {
		long population = 0;
        // Calculate the total population in case of multiple values
       for (LongWritable value : values) {
    	   population += value.get();
        } 
        context.write(key, new LongWritable(population));
        // Key is State, Value is the Population  
    }		
}