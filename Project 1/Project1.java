import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.lang.Integer;
import java.lang.Math;

/**
 * COP 3530: Project 1 – Array Searches and Sorts
 * <p>
 * This Project1 class will prompt the user to input the name of a CSV file, 
 * parse that CSV file and create an array containing the data in the file. 
 * With this data, an options menu will be created allowing the user to choose
 * how they would like to interact with the data. The output is dependent on
 * what option the user chooses from the menu and that information is
 * provided in the documentation of each method in this class.
 * 
 * @author Dawid Klejc
 * @version 2/5/2021
*/

public class Project1 {
	/**
	 * This is the main method.
	 * <p>
	 * It obtains a file name from the user then reads and displays the data within it in various ways. This method will display a option menu to the user
	 * and perform various actions depending on the users input. 
	 * 
	 * @param args an array of String objects
	*/
	public static void main(String[] args) {
		
		String stateName;
		String capitol;
		String region;
		int houseOfReps;
		int population;
		int covid19Cases;
		int covid19Deaths;
		int medianHouseholdIncome;
		double violentCrimeRate;
		String line;
		State[] stateObjectArray = new State[50];
		
		Scanner in = new Scanner(System.in);
		System.out.println("COP3530 Project 1");
		System.out.println("Instructor: Xudong Liu");
		System.out.println("Student: Klejc Dawid");
		System.out.println("\nArray Searches and Sorts");
		System.out.print("Enter the file name: ");
		String fileName = in.next();
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileName));
		} catch(FileNotFoundException e) {
			System.out.println("Could not find file!");
			System.exit(1);
		}
		
		try {
			br.readLine();
			int i = 0;
			int recordCount = 0;
			while((line = br.readLine()) != null) {
				String[] fileData = line.split(",|\n");
				stateName = fileData[0];
				capitol = fileData[1];
				region = fileData[2];
				houseOfReps = Integer.parseInt(fileData[3]);
				population = Integer.parseInt(fileData[4]);
				covid19Cases = Integer.parseInt(fileData[5]);
				covid19Deaths = Integer.parseInt(fileData[6]);
				medianHouseholdIncome = Integer.parseInt(fileData[7]);
				violentCrimeRate = Double.parseDouble(fileData[8]);
				State state = new State(stateName, capitol, region, houseOfReps, population, covid19Cases, covid19Deaths, medianHouseholdIncome, violentCrimeRate);
				stateObjectArray[i++] = state;
				recordCount++;
			}
			System.out.printf("\nThere were %d records read.\n", recordCount);
			printMenu();
		} catch(IOException e) {
			e.printStackTrace();
		}

		String menuSelection = in.next();
		int inputVal = 1;

		do {
			try {
				inputVal = Integer.parseInt(menuSelection);
				if(inputVal > 0 && inputVal < 8) {
						System.out.println();
						switch(inputVal) {
						case 1:
							printStateReport(stateObjectArray);
							printMenu();
							menuSelection = in.next();
							break;
						case 2:
							sortByName(stateObjectArray);
							printMenu();
							menuSelection = in.next();
							break;
						case 3:
							sortByCFR(stateObjectArray);
							printMenu();
							menuSelection = in.next();
							break;
						case 4:
							sortByMHI(stateObjectArray);
							printMenu();
							menuSelection = in.next();
							break;
						case 5:
							System.out.print("Enter State name: ");
							in.nextLine();
							String nameOfState = in.nextLine();
							findState(stateObjectArray, nameOfState);
							printMenu();
							menuSelection = in.next();
							break;
						case 6:
							spearmansCorrelation(stateObjectArray);
							printMenu();
							menuSelection = in.next();
							break;
						case 7:
							break;
						}	
					} 
					else {
						System.out.print("Invalid choice enter 1-7: ");
						menuSelection = in.next();
					}
				} catch (NumberFormatException e) {
					System.out.print("Invalid choice enter 1-7: ");
					menuSelection = in.next();
				}
		} while(inputVal != 7);
		
		if(Integer.parseInt(menuSelection) == 7) {
			System.out.print("Have a good day!");
		}
		in.close();
	}
	
	/**
	 * This method prints an option menu for the user to choose from. There are 7 options numbered 1-7. Number 1 offers the user to print a state report. Number 2 offers the user
	 * to sort the states by name. Number 3 offers the user to sort the states by case fatality rate. Number 4 will sort the states by median household income. Number 5 will 
	 * find and print a given state in a certain format. Number 6 will print a spearman's rho matrix and number 7 will let the user quit the program.
	 */
	public static void printMenu() {
		System.out.println("\n1. Print a States report");
		System.out.println("2. Sort by Name");
		System.out.println("3. Sort by Case Fatality Rate");
		System.out.println("4. Sort by Median Household Income");
		System.out.println("5. Find and print a given State");
		System.out.println("6. Print Spearman's rho matrix");
		System.out.println("7. Quit");
		System.out.print("Enter your choice: ");
	}
	
	/**
	 * This method will print the report of each state in the state object array one by one. This report will include the states' name, median household income, violent crime rate,
	 * covid-19 case fatality rate, covid-19 case rate, and covid-19 death rate.
	 * @param arr an array of State objects
	 */
	public static void printStateReport(State[] arr) {
		
		System.out.printf("%s %18s %14s %14s %23s %20s", "Name", "MHI", "VCR", "CFR", "Case Rate", "Death Rate\n");
		System.out.println("-------------------------------------------------------------------------------------------------");
		for(int i = 0; i < arr.length; i++) {
			System.out.printf("%-20s", arr[i].getStateName());
			System.out.printf("%-15d", arr[i].getMedianHouseholdIncome());
			System.out.printf("%-15.1f", arr[i].getViolentCrimeRate());
			System.out.printf("%-18f", ((double)arr[i].getCovid19Deaths() / (double)arr[i].getCovid19Cases()));
			System.out.printf("%-19.2f", ((double)arr[i].getCovid19Cases() / (double)arr[i].getPopulation()) * 100000);
			System.out.printf("%.2f\n", ((double)arr[i].getCovid19Deaths() / (double)arr[i].getPopulation()) * 100000);
		}
	}
	
	/**
	 * This method will sort the state object array by state name using a bubble sort algorithm.
	 * @param arr an array of State objects
	 */
	public static void sortByName(State[] arr) {
		
		int num = arr.length;
		
		for(int outer = 0; outer < num - 1; outer++) {
			for(int inner = num - 1; inner > outer; inner--) {
				if(arr[inner].getStateName().compareTo(arr[inner - 1].getStateName()) < 0) {
					State temp = arr[inner];
					arr[inner] = arr[inner - 1];	
					arr[inner - 1] = temp;
				}
			}
		}
		System.out.println("States sorted by name.");
	}
	
	/**
	 * This method will sort the state object array by covid-19 case fatality rate ascendingly using a selection sort algorithm. 
	 * @param arr an array of State objects
	 */
	public static void sortByCFR(State[] arr) {
		
		int num = arr.length;
		
		for(int outer = 0; outer < num; outer++) {
			int lowest = outer;
			for(int inner = outer + 1; inner < num; inner++) {
				if(((double)arr[inner].getCovid19Deaths() / (double)arr[inner].getCovid19Cases()) < ((double)arr[lowest].getCovid19Deaths() / (double)arr[lowest].getCovid19Cases())) {
					lowest = inner;
				}
			}
			if(lowest != outer) {
				State temp = arr[lowest];
				arr[lowest] = arr[outer];
				arr[outer] = temp;
			}
		}
		System.out.println("States sorted by COVID-19 Case Fatality Rate.");
	}
	
	/**
	 * This method will sort the state object array by median household income ascendingly using an insertion sort algorithm. 
	 * @param arr an array of State objects
	 */
	public static void sortByMHI(State[] arr) {
		
		int num = arr.length;
		int inner;
		int outer;
		
		for(outer = 1; outer < num; outer++) {
			State temp = arr[outer];
			inner = outer - 1;
			while(inner >= 0 && arr[inner].getMedianHouseholdIncome() > temp.getMedianHouseholdIncome()) {
				arr[inner + 1] = arr[inner];
				inner--;
			}
			arr[inner + 1] = temp;
		}
		System.out.println("States sorted by Median Household Income.");
	}
	
	/**
	 * This method will first check if the state object array is sorted by name. If it is, then it will search for a state that the user enters and find it using binary search. 
	 * It will display this states' name, median household income, violent crime rate, covid-19 case fatality rate, covid-19 case rate, and covid-19 death rate. If the state object
	 * array is not sorted by name, then this method will search for the state using sequential search and display the same information. 
	 * @param arr an array of State objects
	 * @param nameOfState the name of the state you want to find
	 */
	public static void findState(State[] arr, String nameOfState) {
		
		String currentState = "";
		String isSorted = "Alabama";
		for(int i = 0; i < 1; i++) {
			currentState = arr[i].getStateName();
		}
		
		if(currentState.compareTo(isSorted) == 0) {
			int lowerBound = 0;
			int upperBound = arr.length - 1;
			int mid;
			
			while(lowerBound <= upperBound) {
				mid = (lowerBound + upperBound) / 2;
				if(arr[mid].getStateName().compareTo(nameOfState) == 0) {
					System.out.println("Binary Search");
					System.out.println("\nName:       " + arr[mid].getStateName());
					System.out.println("MHI:        " + arr[mid].getMedianHouseholdIncome());
					System.out.println("VCR:        " + arr[mid].getViolentCrimeRate());
					System.out.printf("CFR:        %.6f", ((double)arr[mid].getCovid19Deaths() / (double)arr[mid].getCovid19Cases()));
					System.out.printf("\nCase Rate:  %.2f", ((double)arr[mid].getCovid19Cases() / (double)arr[mid].getPopulation()) * 100000);
					System.out.printf("\nDeath Rate: %.2f", ((double)arr[mid].getCovid19Deaths() / (double)arr[mid].getPopulation()) * 100000);
					System.out.println();
					break;
				} else if(arr[mid].getStateName().compareTo(nameOfState) < 0) {
					lowerBound = mid + 1;
				} else if(arr[mid].getStateName().compareTo(nameOfState) > 0) {
					upperBound = mid - 1;
				} else {
					System.out.printf("Error: %s not found\n", nameOfState);
				}
			}
		} else {
			int j = 0;
			while(j < arr.length) {
				if(arr[j].getStateName().compareTo(nameOfState) == 0) {
					break;
				}
				j++;
			}
			if(j == arr.length) {
				System.out.println("Sequential Search");
				System.out.printf("\nError: %s not found\n", nameOfState);
			} else {
				System.out.println("Sequential Search");
				System.out.println("\nName:       " + arr[j].getStateName());
				System.out.println("MHI:        " + arr[j].getMedianHouseholdIncome());
				System.out.println("VCR:        " + arr[j].getViolentCrimeRate());
				System.out.printf("CFR:        %.6f", ((double)arr[j].getCovid19Deaths() / (double)arr[j].getCovid19Cases()));
				System.out.printf("\nCase Rate:  %.2f", ((double)arr[j].getCovid19Cases() / (double)arr[j].getPopulation()) * 100000);
				System.out.printf("\nDeath Rate: %.2f", ((double)arr[j].getCovid19Deaths() / (double)arr[j].getPopulation()) * 100000);
				System.out.println();
			}
		}
	}
	
	/**
	 * This method will find the spearman's correlation matrix between covid-19 case rate and median household income, covid-19 case rate and violent crime rate,
	 * covid-19 death rate and median household income, and covid-19 death rate and violent crime rate. 
	 * @param arr an array of State objects
	 */
	public static void spearmansCorrelation(State[] arr) {
		
		double[] covid19CaseRates = new double[50];
		double[] covid19DeathRates = new double[50];
		int[] medianHouseholdIncomes = new int[50];
		double[] violentCrimeRates = new double[50];
		int[] squaredRanks = new int[50];
		double spearmansCorrelation = 0;
		
		for(int i = 0; i < arr.length; i++) {
			covid19CaseRates[i] = ((double)arr[i].getCovid19Cases() / (double)arr[i].getPopulation()) * 100000;
			medianHouseholdIncomes[i] = arr[i].getMedianHouseholdIncome();
			violentCrimeRates[i] = arr[i].getViolentCrimeRate();
			covid19DeathRates[i] = ((double)arr[i].getCovid19Deaths() / (double)arr[i].getPopulation()) * 100000;
		}
		int rankArrayCaseRate[] = rankArray(covid19CaseRates);
		int rankArrayMHI[] = rankArray(medianHouseholdIncomes);
		int rankArrayVCR[] = rankArray(violentCrimeRates);
		int rankArrayDeathRate[] = rankArray(covid19DeathRates);
		
		spearmansCorrelation = calculateSpearmansCorrelation(arr, squaredRanks, rankArrayMHI, rankArrayCaseRate);
		System.out.println("-------------------------------------------------");
		System.out.println("|            |        MHI      |       VCR      |");
		System.out.println("-------------------------------------------------");
		System.out.printf("| Case Rate  |     %.4f     |     ", spearmansCorrelation);
		
		spearmansCorrelation = calculateSpearmansCorrelation(arr, squaredRanks, rankArrayVCR, rankArrayCaseRate);
		System.out.printf("%.4f     |\n", spearmansCorrelation);
		System.out.println("-------------------------------------------------");
		
		spearmansCorrelation = 	calculateSpearmansCorrelation(arr, squaredRanks, rankArrayMHI, rankArrayDeathRate);
		System.out.printf("| Death Rate |     %.4f     |     ", spearmansCorrelation);
		
		spearmansCorrelation = 	calculateSpearmansCorrelation(arr, squaredRanks, rankArrayVCR, rankArrayDeathRate);
		System.out.printf("%.4f     |\n", spearmansCorrelation);
		System.out.println("-------------------------------------------------");
	}
	
	/**
	 * This method calculates the rank of each state according to either covid-19 case rate, median household income, violent crime rate, or covid-19 death rate.
	 * @param arr a double array of state ranks
	 * @return the rank of each state for spearman's correlation matrix 
	 */
	public static int[] rankArray(double arr[]) {
		
		int[] ranks = new int[50];
		
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr.length; j++) {
				if(arr[i] > arr[j]) {
					ranks[i]++;
				}
			}
			ranks[i]++;
		}
		return ranks;
	}
	
	/**
	 * This method calculates the rank of each state according to either covid-19 case rate, median household income, violent crime rate, or covid-19 death rate.
	 * @param arr an int array of state ranks
	 * @return the rank of each state for spearman's correlation matrix 
	 */
	public static int[] rankArray(int arr[]) {
		
		int[] ranks = new int[50];
		
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr.length; j++) {
				if(arr[i] > arr[j]) {
					ranks[i]++;
				}
			}
			ranks[i]++;
		}
		return ranks;
	}
	
	/**
	 * This method calculate the spearmans correlation 
	 * @param statesArr an array of State objects
	 * @param squaredRanksArr an array consisting of squared states' ranks
	 * @param firstRankArr the first array entered based on state ranking
	 * @param secondRankArr the second array entered based on state ranking
	 * @return spearmans correlation 
	 */
	public static double calculateSpearmansCorrelation(State statesArr[], int squaredRanksArr[], int firstRankArr[], int secondRankArr[]) {
		
		int sum = 0;
		double spearmansCorr = 0;
		
		for(int i = 0; i < statesArr.length; i++) {
			squaredRanksArr[i] = (int) Math.pow((firstRankArr[i] - secondRankArr[i]), 2);
			sum+= squaredRanksArr[i];
		}
		spearmansCorr = 1 - ((6 * sum) / (50*(Math.pow(50, 2) - 1)));
		
		return spearmansCorr;
	}
}