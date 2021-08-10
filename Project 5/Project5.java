import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * COP 3530: Project 5 - Hash Tables
 * <p>
 * This Project5 class will prompt the user to input the name of a CSV file, 
 * parse that CSV file for information containing COVID-19 data for US states, and 
 * use that data to create a hash table. The user will be provided a menu to 
 * manipulate the hash table, as explained in its respective class. 
 * 
 * @author Dawid Klejc
 * @version 4/16/2021
*/
public class Project5 {
	public static void main(String[] args) {
		
		String stateName;
		long population;
		long covid19Deaths;
 		int userInput = 1; 
		String line;
		HashTable hashTable = new HashTable();
		String[] stateNames = new String[50];
		Long[] statePopulations = new Long[50];
		Long[] stateDeaths = new Long[50];
		
		Scanner in = new Scanner(System.in);
		System.out.println("COP3530 Project 5");
		System.out.println("Instructor: Xudong Liu");
		System.out.println("Student: Klejc Dawid");
		System.out.println("\nHash Tables");
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
			int recordCount = 0;
			
			while((line = br.readLine()) != null) {
				String[] fileData = line.split(",|\n");
				stateName = fileData[0];
				population = Long.parseLong(fileData[4]);
				covid19Deaths = Long.parseLong(fileData[6]);
				stateNames[recordCount] = stateName;
				statePopulations[recordCount] = population;
				stateDeaths[recordCount] = covid19Deaths;
				hashTable.insert(stateName, population, covid19Deaths);
				recordCount++;
			}
			System.out.printf("\nThere were %d state records read into the hash table.\n", recordCount);
		} catch(IOException e) {
			e.printStackTrace();
		}

 		do {
 			boolean check = true;
 			printMenu();
 			while(check) {
		 		try {
		 			userInput = in.nextInt();
		 			if(userInput > 0 && userInput < 9) {
			 			check = false;
		 			} else {
		 				System.out.print("Invalid choice, enter 1-6: ");
		 			}
	 			} catch(InputMismatchException e) {
	 				System.out.print("Invalid choice, enter 1-6: ");
	 				in.next();
	 			}
 			}
 			switch(userInput) {
 			case 1: 
 				hashTable.display();
 				break;
 			case 2:
 				System.out.print("Enter state name: ");
				in.nextLine();
 				String stateToBeDeleted = in.nextLine();
 				int deleteCount = 0;
 				for(int i = 0; i < stateNames.length; i++) {
 					if(stateNames[i].compareToIgnoreCase(stateToBeDeleted) == 0) {
 						hashTable.delete(stateNames[i], statePopulations[i], stateDeaths[i]);
 						break;
 					} 
 					deleteCount++;
 				}
 				if(deleteCount == 50) {
 					System.out.printf("\n%s is not a state.\n", stateToBeDeleted);
 				}
 				break;
 			case 3: 
 				System.out.print("Enter state name: ");
				in.nextLine();
 				String stateToInsert = in.nextLine();
 				int insertCount = 0;
 				for(int i = 0; i < stateNames.length; i++) {
 					if(stateNames[i].compareToIgnoreCase(stateToInsert) == 0) {
 						hashTable.insert(stateNames[i], statePopulations[i], stateDeaths[i]);
 						System.out.printf("\n%s is inserted into the hash table.\n", stateToInsert);
 						break;
 					}
 					insertCount++;
 				}
 				if(insertCount == 50) {
					System.out.printf("\n%s is not a state.\n", stateToInsert);
				}
				break;
 			case 4:
 				System.out.print("Enter state name: ");
				in.nextLine();
 				String stateToFind = in.nextLine();
 				int findCount = 0;
 				for(int i = 0; i < stateNames.length; i++) {
 					if(stateNames[i].compareToIgnoreCase(stateToFind) == 0) {
 						hashTable.find(stateNames[i], statePopulations[i], stateDeaths[i]);
 						break;
 					}
 					findCount++;
 				}
 				if(findCount == 50) {
 					System.out.printf("\n%s is not a state.\n", stateToFind);	
 				}
 				break;
 			case 5:
 				hashTable.printEmptyAndCollisions();
 				break;
 			case 6:
 				System.out.print("\nHave a good day!");
 				break;
 			default:
 				break;
 			}
 		} while(userInput != 6);
		
 		in.close();
 		}
	
	/**
	 * This will print the option menu for the user to choose from. Options range from
	 * modifying and printing the hash table to exiting the program. 
	 */
	public static void printMenu() {
		System.out.println("\n1. Print hash table");
		System.out.println("2. Delete a state of a given name");
		System.out.println("3. Insert a state of a given name");
		System.out.println("4. Search and print a state and its DR for a given name");
		System.out.println("5. Print numbers of empty cells and collisions");
		System.out.println("6. Exit");
		System.out.print("Enter your choice: ");
	}
}
