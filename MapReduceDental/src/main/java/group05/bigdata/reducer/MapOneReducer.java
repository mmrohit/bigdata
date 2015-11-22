package group05.bigdata.reducer;

import java.io.IOException;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MapOneReducer extends Reducer <Text, FloatWritable, Text, FloatWritable>{
	
		public void reduce(Text key, Iterable<FloatWritable> values,
                Context context) throws IOException, InterruptedException {
			
            float recomendationFactor = 0.0f;
          
            // HERE WE CALCULATE ACTUAL Recommendation Factor
      /*      for (FloatWritable value : values) {
            	//recomendationFactor += value.get();
            } */
            context.write(key, new FloatWritable(recomendationFactor));
            // Key is Plan name, Value is the recommendation factor  
        }		
}
