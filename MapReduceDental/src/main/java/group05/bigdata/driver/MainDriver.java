package group05.bigdata.driver;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.ToolRunner;

public class MainDriver extends Configured{
	public static void main( String[] args ) throws Exception
    {
    	int code = ToolRunner.run(new PlanAvgPremDriver(), args);
    	if(code == 0)
    		code = ToolRunner.run(new StateAvgPremDriver(), args);
    	if(code == 0)
    		code = ToolRunner.run(new StatePopulationDriver(), args);
    	if(code == 0)
    		code = ToolRunner.run(new StateTaxDriver(), args);
        System.exit(code);
    }
}
