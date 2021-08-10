import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * COP 3530: Project 4 - Binary Search Trees
 * <p>
 * This Project4 class will prompt the user to input the name of a CSV file, 
 * parse that CSV file for information containing COVID-19 data for US states, and 
 * use that data to create a binary search tree. The binary search tree will consist 
 * of state names and state covid-19 death rates. The user will be provided a menu to 
 * manipulate the binary search tree, as explained in its own class. 
 * 
 * @author Dawid Klejc
 * @version 3/27/2021
*/
public class Project4 {
	public static void main(String[] args) {

		// Groups rated by State Death Rate (Lower death rate = higher priority in the queue)
		// VGOOD:  DR < 50
		// GOOD:   50 < DR < 100 
		// FAIR:  100 < DR < 150 
		// POOR:  150 < DR 
		
		String stateName;
		double population;
		double covid19Deaths;
		double covid19deathRate;
		String line;
 		int userInput = 1; 
 		BinarySearchTree BST = new BinarySearchTree();
		
		Scanner in = new Scanner(System.in);
		System.out.println("COP3530 Project 4");
		System.out.println("Instructor: Xudong Liu");
		System.out.println("Student: Klejc Dawid");
		System.out.println("\nBinary Search Trees");
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
				population = Double.parseDouble(fileData[4]);
				covid19Deaths = Double.parseDouble(fileData[6]);
				covid19deathRate = (covid19Deaths / population) * 100000;
				BST.insert(stateName, covid19deathRate);
				recordCount++;
			}
			System.out.printf("\nThere were %d records read to build a binary search tree.\n", recordCount);
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
		 				System.out.print("Invalid choice, enter 1-8: ");
		 			}
	 			} catch(InputMismatchException e) {
	 				System.out.print("Invalid choice, enter 1-8: ");
	 				in.next();
	 			}
 			}
 			switch(userInput) {
 			case 1: 
 				System.out.println("\nInorder traversal:");
 				printTreeFormat();
 				BST.printInorder(BST.root);
 				break;
 			case 2:
 				System.out.println("\nPreorder traversal:");
 				printTreeFormat();
 				BST.printPreorder(BST.root);
 				break;
 			case 3: 
 				System.out.println("\nPostorder traversal:");
 				printTreeFormat();
 				BST.printPostorder(BST.root);
 				break;
 			case 4:
 				System.out.print("Enter state name: ");
				in.nextLine();
 				String stateToBeDeleted = in.nextLine();
 				BST.delete(stateToBeDeleted);
 				System.out.printf("\n%s is deleted from the binary search tree.\n", stateToBeDeleted);
 				break;
 			case 5:
 				System.out.print("Enter state name: ");
				in.nextLine();
 				String stateToFind = in.nextLine();
 				try {
	 				double stateDeathRate = BST.find(stateToFind);
	 				if(stateDeathRate < 0) {
	 					System.out.printf("\n%s is not found.", stateToFind);
	 				} else {
	 				System.out.printf("\n%s is found with a death rate of %.2f.\n", stateToFind, stateDeathRate);
	 				System.out.printf("Path to %s is ", stateToFind);
	 				BST.printPathToNode(stateToFind);
	 				}
	 				System.out.println();
 				} catch(NullPointerException e) {
 					System.out.println();
 				}
 				break;
 			case 6:
 				int numberOfStates;
 				System.out.print("Enter the number of states: ");
 				numberOfStates = in.nextInt();
 				System.out.println("\nUnimplemented");
 				break;
 			case 7: 
 				System.out.print("Enter the number of states: ");
 				numberOfStates = in.nextInt();
 				System.out.println("\nUnimplemented");
 				break;
 			case 8:
 				System.out.println("\nHave a good day!");
 				break;
 			default:
 				break;
 			}
 		} while(userInput != 8);
		
 		in.close();
 		}
	
	/**
	 * This will print the option menu for the user to choose from. Options range from
	 * modifying and printing the binary search tree to exiting the program. 
	 */
	public static void printMenu() {
		System.out.println("\n1. Print tree inorder");
		System.out.println("2. Print tree preorder");
		System.out.println("3. Print tree postorder");
		System.out.println("4. Delete a state for a given name");
		System.out.println("5. Search and print a state and its path for a given name");
		System.out.println("6. Print bottom states regarding DR");
		System.out.println("7. Print top states regarding DR");
		System.out.println("8. Exit");
		System.out.print("Enter your choice: ");
	}
	
	/**
	 * This will format and print the header above each tree traversal 
	 */
	public static void printTreeFormat() {
		System.out.print("Name\t\t    Death Rate\n");
		System.out.println("------------------------------");
	}
}
