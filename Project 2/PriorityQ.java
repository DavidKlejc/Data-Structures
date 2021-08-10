/**
 * This PriorityQ class will implement a priority queue of state objects using
 * an unsorted array based on covid-19 death rate. The lower the death rate,
 * the higher the priority. This class will also consist of insert, remove,
 * printQueue, isEmpty, and isFull methods that will be further explained in their
 * respective documentation. 
 * 
 * @author Dawid Klejc
 * @version 2/18/2021
 */
public class PriorityQ {

	private int maxSize;
	private State[] stateObject;
	private int numItems;
	
	/**
	 * This constructs a priority queue of state objects
	 * @param sizeOfQueue the capacity of the queue
	 */
	public PriorityQ(int sizeOfQueue) {
		maxSize = sizeOfQueue;
		stateObject = new State[maxSize];
		numItems = 0;
	}
	
	/**
	 * This will insert an item onto the priority queue in constant O(1) time
	 * @param item the item that will be inserted into the priority queue
	 */
	public void insert(State item) {
		
		if(isFull()) {
			System.out.println("Queue full!");
		} else {
			// O(1) insertion 
			stateObject[numItems++] = item;
		}
		
	}
	
	/**
	 * This will remove an item from the priority queue and return it in O(N) time. It does so by first finding the smallest death rate in the
	 * the priority queue, and storing its index. It swaps the state with the smallest death rate with the last state in the queue. After swapping,
	 * the size of the priority queue is decreased by one, and the state with the smallest death rate is returned. 
	 * @return the item removed from the priority queue
	 */
	public State remove() {
		
		int priority = 0;
		State deletedElement = null;
		double smallestValue = stateObject[0].getDeathRate();
		if(isEmpty()) {
			System.out.println("Queue empty!");
		} else {
			for(int i = 0; i < maxSize; i++) {
				if(smallestValue > stateObject[i].getDeathRate()) {
					smallestValue = stateObject[i].getDeathRate(); 
					priority = i;
				}
			}
			deletedElement = stateObject[priority]; 
			stateObject[priority] = stateObject[maxSize - 1];
			stateObject[maxSize - 1] = deletedElement; 
			maxSize--;
			numItems--;
		}
		return deletedElement;
	}
	
	/**
	 * This prints the priority queue from the beginning to the end of its array 
	 */
	public void printQueue() {
		
		System.out.printf("%s %18s %14s %14s %23s %20s", "Name", "MHI", "VCR", "CFR", "Case Rate", "Death Rate\n");
		System.out.println("-------------------------------------------------------------------------------------------------");
		
		for(int i = 0; i < maxSize; i++) {
			System.out.printf("%-20s", stateObject[i].getStateName());
			System.out.printf("%-15d", stateObject[i].getMedianHouseholdIncome());
			System.out.printf("%-15.1f", stateObject[i].getViolentCrimeRate());
			System.out.printf("%-18f", stateObject[i].getCFR());
			System.out.printf("%-19.2f", stateObject[i].getCaseRate());
			System.out.printf("%.2f\n", stateObject[i].getDeathRate());
		}
	}
	
	/**
	 * This will return true if the queue is empty, false if the queue is not empty
	 * @return true if the number of items in the queue is equal to zero, false if the number of items is not equal to zero 
	 */
	public boolean isEmpty() {
		return (numItems == 0);
	}
	
	/**
	 * This will return true if the queue is full, false if the queue is not full 
	 * @return true if the number of items in the queue equals the size of the queue, false otherwise 
	 */
	public boolean isFull() {
		return (numItems == maxSize);
	}
}
