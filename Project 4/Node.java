/**
 *  This Node class constructs a node that will be used for the 
 *  implementation of a binary search tree. Two types of data will
 *  be inserted into each node. 
 * 
 * @author Dawid Klejc
 * @version 3/27/2021
*/
public class Node {

	public String stateName;
	public double deathRate;
	public Node leftChild;
	public Node rightChild;
	
	public void displayNode() {
		System.out.printf("%-20s", stateName);
		System.out.printf("%.2f\n", deathRate);
	}
}
