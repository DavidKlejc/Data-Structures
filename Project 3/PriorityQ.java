/**
 * This PriorityQ class will implement a priority queue of state objects using
 * a sorted double-ended doubly linked list on covid-19 death rate. The lower the 
 * death rate, the higher the priority. This class will also consist of insert, remove,
 * printPriorityQ, and isEmpty methods that will be further explained in their respective 
 * documentation. 
 * 
 * @author Dawid Klejc
 * @version 3/7/2021
 */
public class PriorityQ {

	Link first; 
	Link last;
	Link newLink;
	
	/**
	 * This constructs a priority queue of state objects using a sorted
	 * double-ended doubly linked list
	 */
	public PriorityQ() {
		first = null;
		last = null;
	}
	
	/**
	 * This will insert a state onto the priority queue in constant O(N) time. It does so
	 * by first checking if the linked list is empty, if so then assign the first and last
	 * link to the new link. If the list is not empty, then this method will find the
	 * correct position that the new state must be inserted in and insert it either at the
	 * beginning, middle, or end of the linked list. 
	 * @param item the state that will be inserted into the priority queue
	 */
	public void insert(State item) {
		newLink = new Link(item);
		Link current = first;
		
		if(isEmpty()) {
			first = newLink;
			last = newLink;
		} else {
			while (current.next != null && current.stateData.getDeathRate() < newLink.stateData.getDeathRate()) {
				current = current.next;
			}	
			if(current == first && current.stateData.getDeathRate() > newLink.stateData.getDeathRate()) {
				first.previous = newLink;
				newLink.next = first;
				first = newLink;
			} else if(current == last && current.stateData.getDeathRate() < newLink.stateData.getDeathRate()) {
				last.next = newLink;
				newLink.previous = last;
				last = newLink;
			} else {
				current.previous.next = newLink;
				newLink.next = current;
				newLink.previous = current.previous;
				current.previous = newLink;
			}
		}
	}
	
	/**
	 * This will remove a state from the priority queue and return it in O(1) time.
	 * @return the state removed from the priority queue
	 */
	public Link remove() {
		Link temp = first;
		if(first.next == null) {
			last = null;
		} else {
			first.next.previous = null;
		}
		first = first.next;
		return temp;
	}
	
	/**
	 * This will take in a beginning state death rate and an ending state death rate as parameters, both of 
	 * which will be used to determine the interval in which states should be deleted from the priority queue. 
	 * Any states within the provided interval will be deleted from the priority queue and return true. This is
	 * done by checking several cases in which the interval can possibly be in and deleting the states dependent
	 * on those cases. 
	 * @param beginningDR the beginning death rate of the interval
	 * @param endingDR the ending death rate of the interval 
	 * @return true if a state was found and deleted, false otherwise 
	 */
	public boolean intervalDelete(double beginningDR, double endingDR) {
		boolean returnStatement = false;
		Link intervalStart = first;
		Link intervalEnd = first;

		while(intervalStart.stateData.getDeathRate() < beginningDR) {
			if(intervalStart.next == null) {
				break;
			}
			intervalStart = intervalStart.next;
		}
		while(intervalEnd.stateData.getDeathRate() < endingDR) {
			if(intervalEnd.next == null) {
				break;
			}
			intervalEnd = intervalEnd.next;
		}
		if(intervalStart == first && intervalStart.next != null && intervalEnd.next != null) {
			intervalEnd.previous = null;
			first = intervalEnd;
			returnStatement = true;
		} else if(intervalStart.next != null && intervalEnd.next != null) {
			intervalEnd.previous = intervalStart.previous;
			intervalStart.previous.next = intervalEnd;
			returnStatement = true;
		} else if(intervalStart.previous == null && intervalEnd.next == null) {
			first = null;
			returnStatement = true;
		} else {
			intervalStart.previous.next = null;
			returnStatement = true;
		}
		return returnStatement;
	}
	
	/**
	 * This prints the priority queue from highest priority to lowest priority without changing the 
	 * priority queue. It does so via recursion. There is a try catch statement in the event that the user
	 * decides to delete the entire linked list, which would result in a null output. 
	 * @param link the current state in the priority queue to be printed
	 */
	public void printPriorityQ(Link link) {
		try {
			// Base case
			if(link.next == null) {
				link.displayLink();
			// Recursive case
			} else {
				link.displayLink();
				printPriorityQ(link.next);
			}
		} catch(NullPointerException e) {
			System.out.println("");
		}
	}
	
	/**
	 * This will return true if the queue is empty, false if the queue is not empty.
	 * @return true if the first link is null, false if the first link is not null
	 */
	public boolean isEmpty() {
		return (first == null);
	}
}
