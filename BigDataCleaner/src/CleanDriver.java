
public class CleanDriver {
	public static void main(String[] args) {
		System.out.println("***Started Cleaning Main Process***");
		CleanPopulationCSV cleanPopCSV = new CleanPopulationCSV();
		CleanDentalPlanCSV cleanDentCSV = new CleanDentalPlanCSV();
		CleanTaxes cleanTax = new CleanTaxes();
		CleanIncome cleanIncome = new CleanIncome();
		cleanPopCSV.run(args[0],args[1]); // Population input file and output file directory
		cleanDentCSV.run(args[2],args[3],args[4]); // Dental plan input file, verification file and output directory
		ExcelToCsvConverter converter = new ExcelToCsvConverter();
		converter.convertFile(args[5], args[6]); //Taxes input file and converted file directory
		converter.convertFile(args[7], args[8]);// Income input file and converted file directory
		cleanTax.run(args[6],args[9]); // taxes Converted input and clean output paths
		cleanIncome.run(args[8],args[10]); //income  Converted input and clean output paths
		System.out.println("***Ended Cleaning Main Process***");
	  }
}
