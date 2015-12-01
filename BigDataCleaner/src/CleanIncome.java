import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CleanIncome {
	 
		 public void run(String fileName, String outputPath) {

				File outputFile = null;
				FileWriter fw = null;
				BufferedReader br = null;
				String line = "";
				String cvsSplitBy = ",";
				try {
					int lineIndex = 0;
					br = new BufferedReader(new FileReader(fileName));
					outputFile = new File(outputPath);
					fw = new FileWriter(outputFile);
					while ((line = br.readLine()) != null) {
						if(lineIndex != 0){
						String[] rowValues = line.split(cvsSplitBy);  // use comma as separator				
						fw.append(rowValues[1].trim().toUpperCase()+","+ //STATE
								  (rowValues[2].trim().toUpperCase()+rowValues[3].trim().toUpperCase()).substring(1)+"\n");  //2014 Cleaned INCOME Values 
											//(Since Income has ',' in the value, append the next column for complete value and using substring to remove $ sign at the start
					}
						else{
									
							fw.append("STATE, INCOME(2014)\n");  // 2014 Income heading Column
							lineIndex++; // So that we do not work append unwanted values in column headings
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
							System.out.println("*** 4) Clean Income File Created Successfully***");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}			 
		 }
}