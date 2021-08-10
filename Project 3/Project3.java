import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * COP 3530: Project 3 – Linked Lists
 * <p>
 * This Project3 class will define four groups of states based on COVID-19 death rate. States with a death rate of less than 50 will be defined as "VGOOD".
 * States with a death rate greater than 50 but less than 100 will be defined as "GOOD". States with a death rate greater than 100 but less than 150 will be
 * defined as "FAIR". Lastly, states with a death rate greater than 150 will be defined as "POOR". Only states with a death rate less than 150 will be inserted
 * into a stack of state objects, implemented with a double-ended singly linked list. This stack will be printed top to bottom, followed by the creation of a 
 * priority queue implemented with a double-ended doubly linked list. The states from the stack will be removed and inserted into a priority queue based on 
 * death rate in a sorted manner. The user will be provided a menu with options to choose from on how they would like to manipulate the priority queue. If the 
 * user chooses option 1, they will be able to enter two numbers that will indicate the beginning and end of an interval. These numbers indicate the beginning
 * death rate and ending death rate of interval they would like to remove from the priority queue. Any states within the interval will be removed from the 
 * priority queue. Option 2 will provide the user with the ability to print the priority queue, and option 3 will allow the user to exit the program. 
 * 
 * @author Dawid Klejc
 * @version 3/7/2021
*/
public class Project3 {
	public static void main(String[] args) {

		// Groups rated by State Death Rate (Lower death rate = higher priority in the queue)
		// VGOOD:  DR < 50
		// GOOD:   50 < DR < 100 
		// FAIR:  100 < DR < 150 
		// POOR:  150 < DR 
		
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
		State state = null;
		Stack stack = new Stack();
 		int userInput = 1; 
 		int intervalStart = 0;
 		int intervalEnd = 0;
		
		Scanner in = new Scanner(System.in);
		System.out.println("COP3530 Project 3");
		System.out.println("Instructor: Xudong Liu");
		System.out.println("Student: Klejc Dawid");
		System.out.println("\nLinked Lists");
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
				state = new State(stateName, capitol, region, houseOfReps, population, covid19Cases, covid19Deaths, medianHouseholdIncome, violentCrimeRate);
				// Only pushes states with FAIR, GOOD, AND VGOOD death rates (This includes all states with a death rate less than 150)
				if(state.getDeathRate() < 150) {
					stack.push(state);
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("\nStack Contents:");
		state.printStateFormat();
		stack.printStack(stack.first);
		PriorityQ pQueue = new PriorityQ();
		System.out.println();
 		while(!stack.isEmpty()) {
 	 		pQueue.insert(stack.pop());
 		}
 		System.out.println("Priority Queue Contents:");
		state.printStateFormat();
 		pQueue.printPriorityQ(pQueue.first);

 		do {
 			boolean check = true;
 			System.out.println("\n1. Enter a DR interval for deletions");
 			System.out.println("2. Print priority queue");
 			System.out.println("3. Exit");
 			System.out.print("Enter your choice: ");
 			while(check) {
		 		try {
		 			userInput = in.nextInt();
		 			if(userInput > 0 && userInput < 4) {
			 			check = false;
		 			} else {
		 				System.out.print("Invalid choice, enter 1-3: ");
		 			}
	 			} catch(InputMismatchException e) {
	 				System.out.print("Invalid choice, enter 1-3: ");
	 				in.next();
	 			}
 			}
 			switch(userInput) {
 			case 1: 
 				System.out.print("Enter DR interval like [x,y]: ");
 				while(true) {
 					try {
		 				String intervalInput = in.next();
		 				String[] splitInterval = intervalInput.split("\\[|,|\\]");
		 				intervalStart = Integer.parseInt(splitInterval[1]);
		 				intervalEnd = Integer.parseInt(splitInterval[2]);
		 				if(intervalStart > intervalEnd) {
		 					System.out.print("Invalid interval, first number must be no bigger than the second: ");
		 				} else {
		 					pQueue.intervalDelete(intervalStart, intervalEnd);
		 					System.out.printf("\nStates of priority queue with DRs in [%d,%d] are deleted\n", intervalStart, intervalEnd);
		 					break;
		 				}
 	 				} catch(Exception e) {
 	 					System.out.print("Invalid interval, enter numbers: ");
 	 					continue;
 	 				}
 				}
 				break;
 			case 2:
 				state.printStateFormat();
 				pQueue.printPriorityQ(pQueue.first);
 				break;
 			case 3: 
 				System.out.println("\nHave a good day!");
 				break;
 			}
 		} while(userInput != 3);
 		in.close();
 		}
}
