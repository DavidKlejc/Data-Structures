/**
 *  This Link class consists of links, which will be used to access data stored 
 *  within a link as well as the reference of a link to another object. 
 * 
 * @author Dawid Klejc
 * @version 3/7/2021
*/
public class Link {
	
	public State stateData;
	public Link next;
	public Link previous;
	
	/**
	 * This constructs a link to be implemented into a linked list.
	 * @param data the data within each state object 
	 */
	public Link(State data) {
		stateData = data; 
	}
	
	/**
	 * This prints the data within an individual state
	 */
	public void displayLink() {
		System.out.printf("%-20s", stateData.getStateName());
		System.out.printf("%-15d", stateData.getMedianHouseholdIncome());
		System.out.printf("%-15.1f", stateData.getViolentCrimeRate());
		System.out.printf("%-18f", stateData.getCFR());
		System.out.printf("%-19.2f", stateData.getCaseRate());
		System.out.printf("%.2f\n", stateData.getDeathRate());
	}
}
