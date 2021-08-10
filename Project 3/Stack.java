/**
 * This Stack class will implement a stack of State objects using a double-ended singly linked
 * list. The class will consist of push, pop, printStack, and isEmpty methods. Each of these
 * methods will have further details about their specific use in their respective documentation. 
 * 
 * @author Dawid Klejc
 * @version 3/7/2021
 */
public class Stack {
	
	Link first; 
	Link last;
	Link newLink;
	
	/**
	 * This constructs the stack, assigning the first and last link to null. 
	 */
	public Stack() {
		first = null;
		last = null;
	}
	
	/**
	 * This pushes (adds) an item onto the stack, in our case, a state, in O(1) time
	 * @param item the state that will be pushed onto the stack
	 */
	public void push(State item) {
		newLink = new Link(item);
		newLink.next = first;
		first = newLink;
	}
	
	/**
	 * This pops (removes) an item off of the stack, in our case, a state, in O(1) time
	 * @return the state removed from the top of the stack
	 */
	public State pop() {
		Link removedState = first;
		first = first.next;
		return removedState.stateData; 
	}
	
	/**
	 * This will recursively print the content of the stack from the top down to the bottom.
	 * @param link the current state in the stack to be printed
	 */
	public void printStack(Link link) {
		// Base case
		if(link.next == null) {
			link.displayLink();
		// Recursive case
		} else {
			link.displayLink();
			printStack(link.next);
		}
	}
	
	/**
	 * This will return true if the stack if empty, false if the stack is not empty.
	 * @return true if the first link is null, false if the first link is not null
	 */
	public boolean isEmpty() {
		return (first == null);
	}
}
