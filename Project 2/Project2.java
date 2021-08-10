import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * COP 3530: Project 2 – Stacks and Priority Queues
 * <p>
 * This Project2 class will define four groups of states based on COVID-19 death rate. States with a death rate of less than 50 will be defined as "VGOOD".
 * States with a death rate greater than 50 but less than 100 will be defined as "GOOD". States with a death rate greater than 100 but less than 150 will be
 * defined as "FAIR". Lastly, states with a death rate greater than 150 will be defined as "POOR". Priority queues will be made for each one of these groups.
 * This class will determine which state goes into what priority queue and then print those respective groups. Then, this class will remove items from the poor 
 * priority queue and push those items onto the stack one by one. The same is done for the rest of the priority queues. Lastly, the stack will be displayed. 
 * 
 * @author Dawid Klejc
 * @version 2/18/2021
*/
public class Project2 {
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
		State state;
		State[] stateObjectArray = new State[50];
		
		Scanner in = new Scanner(System.in);
		System.out.println("COP3530 Project 2");
		System.out.println("Instructor: Xudong Liu");
		System.out.println("Student: Klejc Dawid");
		System.out.println("\nStacks and Priority Queues");
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
				stateObjectArray[i++] = state;
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println("\n");
		
		// Groups rated by State Death Rate (Lower death rate = higher priority in the queue)
		// VGOOD:  DR < 50
		// GOOD:   50 < DR < 100 
		// FAIR:  100 < DR < 150 
		// POOR:  150 < DR 
		
		// Variables will track the current size of the priority queue
		int VGOODCount = 0;
		int GOODCount = 0;
		int FAIRCount = 0;
		int POORCount = 0;
		
		// Determine the size of each priority queue
		for(int i = 0; i < stateObjectArray.length; i++) {
			// VGOOD
			if(stateObjectArray[i].getDeathRate() < 50) {
				VGOODCount++;
			} 			
			// GOOD
			else if(stateObjectArray[i].getDeathRate() > 50 && stateObjectArray[i].getDeathRate() < 100) {
				GOODCount++;
			} 
			// FAIR
			else if(stateObjectArray[i].getDeathRate() > 100 && stateObjectArray[i].getDeathRate() < 150) {
				FAIRCount++;
			} 
			// POOR
			else {
				POORCount++;
			}
		}
		
		// Create the four priority queues now that we know the size of them
		PriorityQ VGOOD = new PriorityQ(VGOODCount);
		PriorityQ GOOD = new PriorityQ(GOODCount);
		PriorityQ FAIR = new PriorityQ(FAIRCount);
		PriorityQ POOR = new PriorityQ(POORCount);
		
		// Insert each state into their respective priority queue 
		for(int i = 0; i < stateObjectArray.length; i++) {
			// VGOOD
			if(stateObjectArray[i].getDeathRate() < 50) {
				VGOOD.insert(stateObjectArray[i]);
			} 			
			// GOOD
			else if(stateObjectArray[i].getDeathRate() > 50 && stateObjectArray[i].getDeathRate() < 100) {
				GOOD.insert(stateObjectArray[i]);
			} 
			// FAIR
			else if(stateObjectArray[i].getDeathRate() > 100 && stateObjectArray[i].getDeathRate() < 150) {
				FAIR.insert(stateObjectArray[i]);
			} 
			// POOR
			else {
				POOR.insert(stateObjectArray[i]);
			}
		}
		
		System.out.println("POOR Priority Queue Contents:\n");
		POOR.printQueue();
		System.out.println("\n\nFAIR Priority Queue Contents:\n");
		FAIR.printQueue();
		System.out.println("\n\nGOOD Priority Queue Contents:\n");
		GOOD.printQueue();
		System.out.println("\n\nVGOOD Priority Queue Contents:\n");		
		VGOOD.printQueue();
		
		Stack stack = new Stack(50);
		
		for(int i = 0; i < 50; i++) {
			if(!POOR.isEmpty()) {
				stack.push(POOR.remove());
			}
			else if(!FAIR.isEmpty()) {
				stack.push(FAIR.remove());
			}
			else if(!GOOD.isEmpty()) {
				stack.push(GOOD.remove());
			} else {
				stack.push(VGOOD.remove());
			}
		}
		
		System.out.println("\n\nStack Contents:");
		stack.printStack();
		
		in.close();
	}
}
