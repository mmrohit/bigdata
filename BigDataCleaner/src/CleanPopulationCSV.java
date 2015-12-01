import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CleanPopulationCSV {

	CleanPopulationCSV(){
		// Nothing to initialize
	}
	
		  public void run(String fileName, String outPutPath) {

		File outputFile = null;
		FileWriter fw = null;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		try {

			br = new BufferedReader(new FileReader(fileName));
			outputFile = new File(outPutPath);
			fw = new FileWriter(outputFile);
			int lineIndex = 0;
			while ((line = br.readLine()) != null) {
				if(lineIndex != 0){
				String[] rowValues = line.split(cvsSplitBy);  // use comma as separator				
				fw.append(rowValues[5].trim().toUpperCase()+","+ //STate
						  rowValues[6].replace("County", "").trim().toUpperCase()+","+ // County
						  rowValues[13].trim()+"\n");  //POP EST 2014
			}else{
				//Handle for Headers
				fw.append("STATE,COUNTY,POPULATION\n");
				lineIndex++;
			}
		}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
					fw.close();
					System.out.println("*** 1) Clean Population File Created Successfully***");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	  }
}