import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class CleanDentalPlanCSV {

	File outputFile = null;
	FileWriter fw = null;
	BufferedReader br = null;
	String line = "";
	String cvsSplitBy = ",";
	String[] rowValues;
	HashMap<String,String> statesMap = null; 	
	
	CleanDentalPlanCSV(){
		//Initialize the State mapping for clean data
		statesMap = new HashMap<String,String>();
		
		statesMap.put("AK", "ALASKA");
		statesMap.put("AL", "ALABAMA");
		statesMap.put("AR", "ARKANSAS");
		statesMap.put("AZ", "ARIZONA");
		statesMap.put("DE", "DELAWARE");
		statesMap.put("FL", "FLORIDA");
		statesMap.put("GA", "GEORGIA");
		statesMap.put("IA", "IDAHO");
		statesMap.put("IL", "ILLINOIS");
		statesMap.put("IN", "INDIANA");
		statesMap.put("KS", "KANSAS");
		statesMap.put("LA", "LOUISIANA");
		statesMap.put("ME", "MAINE");
		statesMap.put("MI", "MICHIGAN");
		statesMap.put("MO", "MISSOURI");
		statesMap.put("MT", "MONTANA");
		statesMap.put("NC", "NORTH CAROLINA");
		statesMap.put("ND", "NORTH DAKOTA");
		statesMap.put("NE", "NEBRASKA");
		statesMap.put("NH", "NEW HAMPSHIRE");
		statesMap.put("NJ", "NEW JERSEY");
		statesMap.put("OH", "OHIO");
		statesMap.put("OK", "OKLAHOMA");
		statesMap.put("SC", "SOUTH CAROLINA");
		statesMap.put("SD", "SOUTH DAKOTA");
		statesMap.put("TN", "TENNESSEE");
		statesMap.put("TX", "TEXAS");
		statesMap.put("VA", "VERMONT");
		statesMap.put("WI", "WISCONSIN");
		statesMap.put("WV", "WEST VIRGINIA");
		statesMap.put("WY", "WYOMING");
		statesMap.put("PA", "PENNSYLVANIA");
	}
	
	  public void run(String inputFilePath,String virifiedFilePath,String outputFilePath ) {
		try {
				br = new BufferedReader(new FileReader(inputFilePath));
				outputFile = new File (virifiedFilePath);
				fw = new FileWriter(outputFile);
				while ((line = br.readLine()) != null) {
					//******LINE CLEANING PROCESS************
					if(line.split(cvsSplitBy).length == 7) // THE 7,1,71 PATTERN FIX (BAD End Of LINEs in Values)
						line+= " " + br.readLine() + " " + br.readLine();			
				    rowValues = line.split(cvsSplitBy);  // use comma as separator
				    	//System.out.print("LENGTH OF ROW IS: "+rowValues.length+ "---");
					for (int i = 0; i< rowValues.length ; i++){		
								if (i < rowValues.length-1){		
									//*********Cleaning Values that Contains ',' as values************
									if(rowValues[i].startsWith("\"")){           
										while(!rowValues[i].endsWith("\"")){
										fw.append(rowValues[i++]);
										}
										if(i == rowValues.length-1){  //In case it is the last column
										fw.append(rowValues[i]+"\n");
										}else
											fw.append(rowValues[i]+",");	
									}
									else{
										fw.append(rowValues[i]+",");
									}
								}	
								else
									//System.out.println(rowValues[i]);
										fw.append(rowValues[i]+"\n");
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
					System.out.println("*** 2) Verified DentalShop File Created***");
					startClean(virifiedFilePath,outputFilePath);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	  }
	  
	  public void startClean(String fileName, String outPutPath){
				try {
					System.out.println("*** STARTING FINAL CLEAN ***");
					br = new BufferedReader(new FileReader(fileName));
		  			outputFile = new File(outPutPath);
		  			fw = new FileWriter(outputFile);
		  			int lineIndex = 0;
		  			while ((line = br.readLine()) != null) {
						rowValues = line.split(cvsSplitBy);
						//SANITY CHECK
			  			if(rowValues.length != 75)   // If there is any issue in the verified File we can check command line
						System.out.println("BAD ROW DETECTED WITH NUMBER OF COLUMNS AS: "+rowValues.length);	
			  			
			  			/*   INDEXING REFERENCE OF THE COLUMNS
			  			 
			  			 	--[0]State
							--[1]County
							--[2]Metal Level
							[3]Issuer Name
							[4]Plan ID (standard component)
							--[5]Plan Marketing Name
							--[6]Plan Type
							--[7]Rating Area
							[8]Child Only Offering
							[9]Source
							[10]Customer Service Phone Number Local
							[11]Customer Service Phone Number Toll Free
							[12]Customer Service Phone Number TTY
							[13]Network URL
							[14]Plan Brochure URL
							[15]Summary of Benefits URL
							[16]Drug Formulary URL
							[17]Routine Dental Services - Adult
							[18]Basic Dental Care - Adult
							[19]Major Dental Care - Adult
							[20]Orthodontia - Adult
							[21]Dental Check-Up for Children
							[22]Basic Dental Care - Child
							[23]Major Dental Care - Child
							[24] Orthodontia - Child
							[25]Premium Scenarios
							--[26]Premium Child
							--[27]Premium Adult Individual Age 21
							--[28]Premium Adult Individual Age 27
							--[29]Premium Adult Individual Age 30
							--[30]Premium Adult Individual Age 40
							--[31]Premium Adult Individual Age 50
							--[32]Premium Adult Individual Age 60
							--[33]Premium Couple 21  
							--[34]Premium Couple 30
							--[35]Premium Couple 40
							--[36]Premium Couple 50
							--[37]Premium Couple 60
							--[38]Couple+1 child Age 21
							--[39]Couple+1 child Age 30
							--[40]Couple+1 child Age 40
							--[41]Couple+1 child Age 50
							--[42]Couple+2 children Age 21
							--[43]Couple+2 children Age 30
							--[44]Couple+2 children Age 40
							--[45]Couple+2 children Age 50
							--[46]Couple+3 or more Children Age 21
							--[47]Couple+3 or more Children Age 30
							--[48]Couple+3 or more Children Age 40
							--[49]Couple+3 or more Children Age 50
							--[50]Individual+1 child Age 21
							--[51]Individual+1 child Age 30
							--[52]Individual+1 child Age 40
							--[53]Individual+1 child Age 50
							--[54]Individual+2 children Age 21
							--[55]Individual+2 children Age 30
							--[56]Individual+2 children Age 40
							--[57]Individual+2 children Age 50
							--[58]Individual+3 or more children Age 21
							--[59]Individual+3 or more children Age 30
							--[60]Individual+3 or more children Age 40
							--[61]Individual+3 or more children Age 50
							[62]Standard Plan Cost Sharing
							[63]Dental Deductible - individual
							[64]Dental Deductible -family
							[65]Maximum Out Of Pocket - individual
							[66]Maximum Out of Pocket -family
							[67]Routine Dental Services Adult
							[68]Basic Dental Adult
							[69]Major Dental Adult
							[70]Orthodontia Adult
							[71]Dental Check-Up Child
							[72]Basic Dental Child
							[73]Major Dental Child
							[74]Orthodontia Child			  			 
			  			 */
			  			
			  			if(lineIndex != 0){
			  			fw.append(
			  					  statesMap.get(rowValues[0].trim().toUpperCase())+","+     // STATE
								  rowValues[1].trim().toUpperCase()+","+ // County
								  rowValues[2].trim().toUpperCase()+","+ //Metal Level
								  rowValues[5].trim().toUpperCase()+","+ //Plan Marketting name
								  rowValues[6].trim().toUpperCase()+","+ //Plan type
//								  rowValues[8].trim().toUpperCase()+","+ //Child Only?
//								  rowValues[17].trim().toUpperCase()+","+ //Routine dental adult
//								  rowValues[18].trim().toUpperCase()+","+ //basic dental adult
//								  rowValues[19].trim().toUpperCase()+","+ //major dental adult
//								  rowValues[20].trim().toUpperCase()+","+ //Orthodontia adult
//								  rowValues[21].trim().toUpperCase()+","+ //Routine dental Child
//								  rowValues[22].trim().toUpperCase()+","+ //basic dental Child
//								  rowValues[23].trim().toUpperCase()+","+ //major dental Child
//								  rowValues[24].trim().toUpperCase()+","+ //Orthodontia  Child								 
//								  rowValues[26].trim().toUpperCase()+","+
//								  rowValues[27].trim().toUpperCase()+","+
//								  rowValues[28].trim().toUpperCase()+","+
//								  rowValues[29].trim().toUpperCase()+","+
//								  rowValues[30].trim().toUpperCase()+","+
//								  rowValues[31].trim().toUpperCase()+","+
//								  rowValues[32].trim().toUpperCase()+","+
//								  rowValues[33].trim().toUpperCase()+","+
//								  rowValues[34].trim().toUpperCase()+","+
//								  rowValues[35].trim().toUpperCase()+","+
//								  rowValues[36].trim().toUpperCase()+","+
//								  rowValues[37].trim().toUpperCase()+","+
//								  rowValues[38].trim().toUpperCase()+","+
//								  rowValues[39].trim().toUpperCase()+","+
//								  rowValues[40].trim().toUpperCase()+","+
//								  rowValues[41].trim().toUpperCase()+","+
//								  rowValues[42].trim().toUpperCase()+","+
//								  rowValues[43].trim().toUpperCase()+","+
//								  rowValues[44].trim().toUpperCase()+","+
//								  rowValues[45].trim().toUpperCase()+","+
//								  rowValues[46].trim().toUpperCase()+","+
//								  rowValues[47].trim().toUpperCase()+","+
//								  rowValues[48].trim().toUpperCase()+","+
//								  rowValues[49].trim().toUpperCase()+","+
//								  rowValues[50].trim().toUpperCase()+","+
//								  rowValues[51].trim().toUpperCase()+","+
//								  rowValues[52].trim().toUpperCase()+","+
//								  rowValues[53].trim().toUpperCase()+","+
//								  rowValues[54].trim().toUpperCase()+","+
//								  rowValues[55].trim().toUpperCase()+","+
//								  rowValues[56].trim().toUpperCase()+","+
//								  rowValues[57].trim().toUpperCase()+","+
//								  rowValues[58].trim().toUpperCase()+","+
//								  rowValues[59].trim().toUpperCase()+","+
//								  rowValues[60].trim().toUpperCase()+","+
//								  rowValues[61].trim().toUpperCase()+","+
					//			  rowValues[63].trim().toUpperCase()+","+
					//			  rowValues[64].trim().toUpperCase()+","+
					//			  rowValues[65].trim().toUpperCase()+","+
					//			  rowValues[66].trim().toUpperCase()+","+
					//			  rowValues[67].trim().toUpperCase()+","+
					//			  rowValues[68].trim().toUpperCase()+","+
					//			  rowValues[69].trim().toUpperCase()+","+
					//			  rowValues[70].trim().toUpperCase()+","+
					//			  rowValues[71].trim().toUpperCase()+","+
					//			  rowValues[72].trim().toUpperCase()+","+
					//			  rowValues[73].trim().toUpperCase()+","+
					//			  rowValues[74].trim().toUpperCase()+","+
								  calculateAvgPremium(rowValues)+
			  					   "\n");  //Row end
			  			}else{
			  				//Headers Handle
			  				
			  				fw.append("STATE,COUNTY,METAL LEVEL,PLAN NAME, PLAN TYPE, AVG PREMIUM\n");
			  				lineIndex++;
			  				
			  			}
		  			} //While Completed
			} catch (IOException e){
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
						fw.close();
						System.out.println("*** 3) CleanDentalShop File Created Successfully***");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
	  }
	  
	  float calculateAvgPremium(String[] rowValues){
		  float result = 0;
		for (int i = 26; i <= 61; i++){	
			//Do not aggregate empty values, ignore the empty premium and move on to next values
			if(!rowValues[i].trim().replace("$", "").isEmpty()){
			 // System.out.println(rowValues[i].trim().replace("$", ""));
			  result += Float.valueOf(rowValues[i].trim().replace("$", "")); // $ is removed while creating the float variable else it throws exception
			  if (i !=  0)
				  result = result/2;
			}
		}
		//System.out.println("AVG: "+result);
		  return result; 
	  }
}