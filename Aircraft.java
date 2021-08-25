/*
 * 
 * This class models an aircraft type with a model name, a maximum number of economy seats, and a max number of forst class seats 
 * 
 * Add code such that class Aircraft implements the Comparable interface
 * Compare two Aircraft objects by first comparing the number of economy seats. If the number is equal, then compare the
 * number of first class seats 
 */

/**
 * 
 * @author Kerry Kanhai
 * Student Number: 501063750
 *
 */
public class Aircraft implements Comparable<Aircraft>
{
  int numEconomySeats;
  int numFirstClassSeats;
  
  String model;
  
  public Aircraft(int seats, String model)
  {
  	this.numEconomySeats = seats;
  	this.numFirstClassSeats = 0;
  	this.model = model;
  }

  public Aircraft(int economy, int firstClass, String model)
  {
  	this.numEconomySeats = economy;
  	this.numFirstClassSeats = firstClass;
  	this.model = model;
  }
  
	public int getNumSeats()
	{
		return numEconomySeats;
	}
	
	public int getTotalSeats()
	{
		return numEconomySeats + numFirstClassSeats;
	}
	
	public int getNumFirstClassSeats()
	{
		return numFirstClassSeats;
	}

	public String getModel()
	{
		return model;
	}

	public void setModel(String model)
	{
		this.model = model;
	}
	
	public void print()
	{
		System.out.println("Model: " + model + "\t Economy Seats: " + numEconomySeats + "\t First Class Seats: " + numFirstClassSeats);
	}

	/**
	 * The compareTo method compares two aircraft objects
	 * If their number of seats are equal, it then compares the first class seats
	 * If number of seats not equal, it compares it normally
	 */
	public int compareTo(Aircraft other) {
		if (this.getNumSeats() == other.getNumSeats()) {
			if (this.getNumFirstClassSeats() > other.getNumFirstClassSeats()) {
				return 1;
			}
			else if (this.getNumFirstClassSeats() < other.getNumFirstClassSeats()) {
				return -1;
			}
			else {
				return 0;
			}
		}
		else if (this.getNumSeats() > other.getNumSeats()) {
			return 1;
		}
		else {
			return -1;
		}
	}
	
}
  
