/**
 *  This state class will store information about a state and provide methods to
 *  get and set that data. 
 * 
 * @author Dawid Klejc
 * @version 2/5/2021
*/
public class State {

	private String stateName;
	private String capitolName;
	private String regionName;
	private int numOfHouseOfRepresentatives;
	private int populationCount;
	private int covid19Cases;
	private int covid19Deaths;
	private int medianHouseholdIncome;
	private double violentCrimeRate;
	
	/**
	 * This constructs a state with a specified state name, capitol name, region name, number of house of representatives in the state, the population count, covid-19 cases,
	 * covid-19 deaths, median household income, and violent crime rate.
	 * @param stateName the name of the state
	 * @param capitolName the states capitol name
	 * @param regionName the states region name
	 * @param numOfHouseOfRepresentatives the number of house of representatives in the state
	 * @param populationCount the states population count
	 * @param covid19Cases the amount of covid-19 cases the state has
	 * @param covid19Deaths the amount of covid-19 deaths the state has
	 * @param medianHouseholdIncome the median household income of the state
	 * @param violentCrimeRate the violent crime rate of the state
	 */
	public State(String stateName, String capitolName, String regionName, int numOfHouseOfRepresentatives, int populationCount, int covid19Cases, int covid19Deaths, int medianHouseholdIncome, double violentCrimeRate) {
		this.stateName = stateName;
		this.capitolName = capitolName;
		this.regionName = regionName;
		this.numOfHouseOfRepresentatives = numOfHouseOfRepresentatives;
		this.populationCount = populationCount;
		this.covid19Cases = covid19Cases;
		this.covid19Deaths = covid19Deaths;
		this.medianHouseholdIncome = medianHouseholdIncome;
		this.violentCrimeRate = violentCrimeRate;
	}

	/**
	 * This returns the current name of this state
	 * @return this state's name
 	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * This sets the current name of this state
	 * @param stateName the name of the state
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	/**
	 * This returns the current capitol name of this state
	 * @return this state's capitol name
 	 */
	public String getCapitolName() {
		return capitolName;
	}

	/**
	 * This sets the current capitol name of this state
	 * @param capitolName the states capitol name
	 */
	public void setCapitolName(String capitolName) {
		this.capitolName = capitolName;
	}

	/**
	 * This returns the current region name of this state
	 * @return this state's region name
	 */
	public String getRegionName() {
		return regionName;
	}

	/**
	 * This sets the current region name of this state
	 * @param regionName the states region name
	 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	/**
	 * This returns the current number of house of representatives in this state
	 * @return the number of house of representatives in the state
	 */
	public int getNumOfHouseOfRepresentatives() {
		return numOfHouseOfRepresentatives;
	}

	/**
	 * This sets the current number of house of representatives in this state
	 * @param numOfHouseOfRepresentatives the number of house of representatives in the state
	 */
	public void setNumOfHouseOfRepresentatives(int numOfHouseOfRepresentatives) {
		this.numOfHouseOfRepresentatives = numOfHouseOfRepresentatives;
	}

	/**
	 * This returns the current population in this state
	 * @return the population count in the state
	 */
	public int getPopulation() {
		return populationCount;
	}

	/**
	 * This sets the current population count in this state
	 * @param populationCount the states population count
	 */
	public void setPopulation(int populationCount) {
		this.populationCount = populationCount;
	}

	/**
	 * This returns the current number of covid-19 cases in this state
	 * @return the number of covid-19 cases in the state
	 */
	public int getCovid19Cases() {
		return covid19Cases;
	}

	/**
	 * This sets the current number of covid-19 cases in this state
	 * @param covid19Cases the amount of covid-19 cases the state has
	 */
	public void setCovid19Cases(int covid19Cases) {
		this.covid19Cases = covid19Cases;
	}

	/**
	 * This returns the current number of covid-19 deaths in this state
	 * @return the number of covid-19 deaths in the state
	 */
	public int getCovid19Deaths() {
		return covid19Deaths;
	}

	/**
	 * This sets the current number of covid-19 deaths in this state
	 * @param covid19Deaths the amount of covid-19 deaths the state has
	 */
	public void setCovid19Deaths(int covid19Deaths) {
		this.covid19Deaths = covid19Deaths;
	}

	/**
	 * This returns the current median household income in this state
	 * @return the median household income of the state
	 */
	public int getMedianHouseholdIncome() {
		return medianHouseholdIncome;
	}

	/**
	 * This sets the current median household income in this state
	 * @param medianHouseholdIncome the median household income of the state
	 */
	public void setMedianHouseholdIncome(int medianHouseholdIncome) {
		this.medianHouseholdIncome = medianHouseholdIncome;
	}

	/**
	 * This returns the current violent crime rate in this state
	 * @return the violent crime rate of the state
	 */
	public double getViolentCrimeRate() {
		return violentCrimeRate;
	}

	/**
	 * This sets the current violent crime rate in this state
	 * @param violentCrimeRate the violent crime rate of the state
	 */
	public void setViolentCrimeRate(double violentCrimeRate) {
		this.violentCrimeRate = violentCrimeRate;
	}
	
	/**
	 * This returns the current state name, capitol name, region name, number of house of representatives, population, covid-19 cases, covid-19 deaths, median household income, and violent crime rate.
	 */
	@Override
	public String toString() {
		return "State: " + stateName + " Capitol: " + capitolName + " Region: " + regionName + " Number of House of Representatives: " + numOfHouseOfRepresentatives + " Population: " + populationCount + " Covid-19 Cases: " + covid19Cases + " Covid-19 Deaths: " + covid19Deaths + " Median Household Income: " + medianHouseholdIncome + " Violent Crime Rate: " + violentCrimeRate;
	}
}
