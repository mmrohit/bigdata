package group05.bigdata.reducer;

import java.io.IOException;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class StatePopulationReducer extends Reducer <Text, FloatWritable, Text, FloatWritable>{
	
	public void reduce(Text key, Iterable<FloatWritable> values,
            Context context) throws IOException, InterruptedException {
		long population = 0;
        // Calculate the total population in case of multiple values
       for (FloatWritable value : values) {
    	   population += value.get();
        } 
        context.write(key, new FloatWritable(population));
        // Key is State, Value is the Population  
    }		
}