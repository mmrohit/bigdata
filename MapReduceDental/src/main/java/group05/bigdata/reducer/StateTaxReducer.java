package group05.bigdata.reducer;

import java.io.IOException;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class StateTaxReducer extends Reducer <Text, FloatWritable, Text, FloatWritable>{

	public void reduce(Text key, Iterable<FloatWritable> values,
            Context context) throws IOException, InterruptedException {
		
	   float tax = 0.0f;
       for (FloatWritable value : values) {
    	   tax += value.get();        	
        } 
        context.write(key, new FloatWritable(tax));
        // Key is State, Value is the tax in %  
    }		
}
