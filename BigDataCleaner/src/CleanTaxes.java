import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CleanTaxes {
	 public void run(String fileName, String outputPath) {

			File outputFile = null;
			FileWriter fw = null;
			BufferedReader br = null;
			String line = "";
			String cvsSplitBy = ",";
			try {

				br = new BufferedReader(new FileReader(fileName));
				outputFile = new File(outputPath);
				fw = new FileWriter(outputFile);
				int lineIndex = 0;
				while ((line = br.readLine()) != null) {
					if(lineIndex != 0){
					String[] rowValues = line.split(cvsSplitBy);  // use comma as separator				
					fw.append(
							
							(rowValues[0].trim().indexOf('[') != -1 ?
							rowValues[0].toUpperCase().subSequence(0, rowValues[0].trim().indexOf('[')).toString().trim()+"," :
							rowValues[0].trim().toUpperCase()+",")+ //STate
							
							rowValues[1].trim().toUpperCase().replace("%", "")+","+ // Base Tax
							rowValues[2].trim().toUpperCase().replace("%", "")+"\n");  //max Total Tax
				}else{
					//handle Headers
					fw.append("STATE,BASE TAX(in %), TOTAL SURPLUS TAX(in %)\n");
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
						System.out.println("*** 3) Clean Taxes File Created Successfully***");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}		 
	 }
}