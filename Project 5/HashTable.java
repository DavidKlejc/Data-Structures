/**
 *  This HashTable class will instantiate a hash table. It
 *  will hold all of the nodes in the hash table and use several methods to 
 *  insert, find, delete, and print nodes in the hash table. The hash table
 *  will implement separate chaining to deal with any collisions using
 *  an array of double-ended singly-linked lists. 
 * 
 * @author Dawid Klejc
 * @version 4/16/2021
*/
public class HashTable {
	
	/**
	 *  This Node class constructs a node that will be used for the 
	 *  implementation of a hash table. Three types of data will
	 *  be inserted into each node: US State name, population, and 
	 *  covid-19 death count. 
	 * 
	 * @author Dawid Klejc
	 * @version 4/16/2021
	*/
	private class Node {
		
		String name;
		long population;
		long deaths;
		Node nextNode;
		
		/**
		 * This constructs a node to be implemented in a linked list. 
		 * @param name the name of the state
		 * @param population the population count of the state
		 * @param deaths the covid-19 death count of the state
		 */
		public Node(String name, long population, long deaths) {
			this.name = name;
			this.population = population;
			this.deaths = deaths;
		}
		
		/**
		 * This printNode method will print the content of a node. 
		 */
		public void printNode() {
			System.out.printf("%-30s %-20.2f\n", name, (double)deaths/population * 100000);
		}
	}
	
	/**
	 * This List class will create a double-ended singly linked list and
	 * implement several methods to insert, find, and delete states. 
	 * @author Dawid Klejc
	 * @version 4/16/2021
	 */
	public class List {
		
		Node first;
		Node last;
		
		/**
		 * This will construct an empty linked list. 
		 */
		public List() {
			first = null;
			last = null;
		}
	}
	
	private List[] hashArray;

	/**
	 * This constructs an empty hash table. 
	 */
	public HashTable() {
		hashArray = new List[101];
		for(int i = 0; i < hashArray.length; i++) {
			hashArray[i] = new List();
		}
	}
	
	/**
	 * This insert method will insert a node into the linked list at the proper
	 * position in the hash table based on state name, its population, and its deaths. 
	 * @param state the name of the state 
	 * @param population the population count of the state 
	 * @param deaths the covid-19 death count of the state 
	 */
	public void insert(String state, long population, long deaths) {
		
		int hashVal = hashFunc(state, population, deaths);
		Node newNode = new Node(state, population , deaths);
		
		if(hashArray[hashVal].first == null) {
			hashArray[hashVal].first = newNode;
			hashArray[hashVal].last = newNode;
		} else {
			hashArray[hashVal].last.nextNode = newNode;
			hashArray[hashVal].last = newNode;
		}
	}
	
	/**
	 * This find method will search the linked list at the proper position in the hash
	 * table for the state. If found, it will return its table index or -1 if not found. 
	 * @param state the name of the state 
	 * @param population the population count of the state 
	 * @param deaths the covid-19 death count of the state 
	 * @return hash table index if state is found, -1 if not found 
	 */
	public double find(String state , long population, long deaths) {
		
		double deathRate = 0;
		int hashVal = hashFunc(state, population, deaths);
		Node current = hashArray[hashVal].first;
		
		while(current != null) {
			if(current.name.compareToIgnoreCase(state) == 0) {
				deathRate = ((double)current.deaths / (double)current.population) * 100000;
				System.out.printf("\n%s is found at index %d with a DR of %.2f\n", state, hashVal, deathRate);
				return hashVal;
			} 
			current = current.nextNode;
		}
		System.out.printf("\n%s is not found.\n", state);
		return -1;
	}
	
	/**
	 * This delete method will find and delete the state of the given name, its population,
	 * and its deaths from the hash table. 
	 * @param state the name of the state 
	 * @param population the population count of the state 
	 * @param deaths the covid-19 death count of the state 
	 */
	public void delete(String state, long population, long deaths) {
		
		int hashVal = hashFunc(state, population, deaths);
		Node current = hashArray[hashVal].first;
		Node previous = hashArray[hashVal].first;
		
		if(current == null) {
			System.out.printf("\n%s is not found.\n", state);
			return;
		}
		while(current.name.compareToIgnoreCase(state) != 0) {
			if(current.nextNode == null) {
				System.out.printf("\n%s is not found.\n", state);
				return;
			} else {
				previous = current;
				current = current.nextNode;
			}
		}
		if(current == hashArray[hashVal].first) {
			hashArray[hashVal].first = hashArray[hashVal].first.nextNode;
			System.out.printf("\n%s is deleted from the hash table.\n", state);
		} else {
			previous.nextNode = current.nextNode;
			if(previous.nextNode == null) {
				hashArray[hashVal].last = previous;
			}
			System.out.printf("\n%s is deleted from the hash table.\n", state);
		}
	}

	/**
	 * This display method will traverse the hash table and print its content. 
	 */
	public void display() {
		
		System.out.println();
		for(int i = 0; i < hashArray.length; i++) {
			if(hashArray[i].first == null) {
				if(i < 10) {
					System.out.println(i + ".   Empty");
				} else if(i > 9 && i < 100) {
					System.out.println(i + ".  Empty");
				} else {
					System.out.println(i + ". Empty");
				}
			} else {
				if(i < 10) {
					System.out.print(i + ".   ");
					displayList(hashArray, i);
				} else if(i > 9 && i < 100) {
					System.out.print(i + ".  ");
					displayList(hashArray, i);
				} else {
					System.out.print(i + ". ");
					displayList(hashArray, i);
				}
			}
		}
	}
	
	/**
	 * This printEmptyAndCollisions method will print the number of empty cells and the number
	 * of collided cells in the hash table. An empty cell is any cell with no state. A collided
	 * cell is any cell consisting of multiple states. 
	 */
	public void printEmptyAndCollisions() {
		
		int emptyCells = 0;
		int collisions = 0;
		
		for(int i = 0; i < hashArray.length; i++) {
			if(hashArray[i].first == null) {
				emptyCells++;
			} else if(hashArray[i].first.nextNode != null) {
				collisions++;
			}
		}
		System.out.printf("\nThere are %d empty cells and %d collisions in the hash table.\n", emptyCells, collisions);
	}
	
	/**
	 * This will traverse the linked list and print its content. 
	 */
	public void displayList(List[] arr, int i) {
		
		Node current = arr[i].first;
		while(current != null) {
			current.printNode();
			if(current.nextNode != null) {
				System.out.print("     ");
			}
			current = current.nextNode;
		}
	}
	
	/**
	 * This hashFunc method will calculate the index value for a state in the hash table. The 
	 * index value is determined by summing up the unicode values of all the character's in the 
	 * state's name string (including spaces), add its population and deaths, and then modulus 
	 * the result with 101. 
	 * @param state
	 * @param population
	 * @param deaths
	 * @return the index value for the current state 
	 */
	public int hashFunc(String state, long population, long deaths) {
		
		int index = 0;
		int sum = 0;
		
		for(int i = 0; i < state.length(); i++) {
			sum += (int)state.charAt(i);
		}
		
		sum += population + deaths;
		index = sum % 101; 
		return index; 
	}
}