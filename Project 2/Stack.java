/**
 * This Stack class will implement a stack of State objects using an array. The class will 
 * consist of push, pop, printStack, isEmpty, and isFill methods. Each of these methods will
 * have further details about their specific use in their respective documentation. 
 * 
 * @author Dawid Klejc
 * @version 2/18/2021
 */
public class Stack {

	private int maxSize;
	private State[] stateObject;
	private int top;
	
	/**
	 * This constructs a stack of state objects
	 * @param sizeOfStack the capacity of the stack
	 */
	public Stack(int sizeOfStack) {
		maxSize = sizeOfStack;
		stateObject = new State[maxSize];
		top = -1;
	}
	
	/**
	 * This pushes (adds) an item onto the stack, in our case, a state
	 * @param item the item that will be pushed onto the stack
	 */
	public void push(State item) {
		if(isFull()) {
			System.out.println("Stack full!");
		} else {
			stateObject[++top] = item;
		}
	}
	
	/**
	 * This pops (removes) an item off of the stack, in our case, a state
	 * @return the item removed from the top of the stack
	 */
	public State pop() {
		if(isEmpty()) {
			System.out.println("Stack empty!");
		}
		return stateObject[top--];
	}
	
	/**
	 * This prints the stack from top down to the bottom without modifying the content of the stack
	 */
	public void printStack() {
		
		System.out.printf("\n%s %18s %14s %14s %23s %20s", "Name", "MHI", "VCR", "CFR", "Case Rate", "Death Rate\n");
		System.out.println("-------------------------------------------------------------------------------------------------");

		for(int i = top; i >= 0; i--) {
			System.out.printf("%-20s", stateObject[i].getStateName());
			System.out.printf("%-15d", stateObject[i].getMedianHouseholdIncome());
			System.out.printf("%-15.1f", stateObject[i].getViolentCrimeRate());
			System.out.printf("%-18f", stateObject[i].getCFR());
			System.out.printf("%-19.2f", stateObject[i].getCaseRate());
			System.out.printf("%.2f\n", stateObject[i].getDeathRate());
		}
	}
	
	/**
	 * This will return true if the stack if empty, false if the stack is not empty
	 * @return true if top equals -1, false if top is not equal to -1
	 */
	public boolean isEmpty() {
		return (top == -1);
	}
	
	/**
	 * This will return true if the stack is full, false if the stack is not fully
	 * @return true if top equals the size of the array, false if top does not equal the size of the array
	 */
	public boolean isFull() {
		return (top == maxSize - 1);
	}
}
