/**
 * 
 * @author Kerry Kanhai
 * Student Number: 501063750
 *
 */
public class Passenger {
	
	private String name;
	private String passportNumber;
	private int seatNum;
	
	public Passenger() {
		this.name = "";
		this.passportNumber = "";
		this.seatNum = 0;
	}
	
	public Passenger(String name, String passportNumber, int seatNum) {
		this.name = name;
		this.passportNumber = passportNumber;
		this.seatNum = seatNum;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassportNumber() {
		return this.passportNumber;
	}
	
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	
	public int getSeatNum() {
		return this.seatNum;
	}
	
	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}
	
	/**
	 * Overrides the equals method
	 * Checks if passenger name and passport number are equal
	 */
	@Override
	public boolean equals(Object other) {
		Passenger otherPassenger = (Passenger) other;
		
		return (this.name.equals(otherPassenger.name) && this.passportNumber.equals(otherPassenger.passportNumber));
	}
}
