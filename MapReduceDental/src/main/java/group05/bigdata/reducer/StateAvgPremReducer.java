package group05.bigdata.reducer;

import java.io.IOException;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class StateAvgPremReducer extends Reducer <Text, FloatWritable, Text, FloatWritable>{
	
		public void reduce(Text key, Iterable<FloatWritable> values,
                Context context) throws IOException, InterruptedException {
			
			float avgStatePremium = 0.0f;
            int flag = 0;
          
            // Calculate the avg premium of all the premiums
           for (FloatWritable value : values) {
            	if(flag == 0){
            		avgStatePremium += value.get();
            		flag++;
            	}
            	else{
            		avgStatePremium += value.get();
            		avgStatePremium /= 2;
            	}
            } 
            context.write(key, new FloatWritable(avgStatePremium));
            // Key is Plan name, Value is the recommendation factor  
        }		
}
