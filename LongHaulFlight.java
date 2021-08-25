/*
 * A long haul flight is a flight that travels thousands of kilometers and typically has separate seating areas 
 */

/**
 * 
 * @author Kerry Kanhai
 * Student Number: 501063750
 *
 */

public class LongHaulFlight extends Flight
{
	int numFirstClassPassengers;
	String seatType;
	
	// Possible seat types
	public static final String firstClass = "First Class Seat";
	public static final String economy 		= "Economy Seat";  
	

	public LongHaulFlight(String flightNum, String airline, String dest, String departure, int flightDuration, Aircraft aircraft)
	{
		// use the super() call to initialize all inherited variables
		// also initialize the new instance variables 
		super(flightNum, airline, dest, departure, flightDuration, aircraft);
		this.seatType = LongHaulFlight.firstClass;
	}

	public LongHaulFlight()
	{
     // default constructor
		super();
	}
	
	/*
	 * Reserves a seat on a flight. Essentially just increases the number of (economy) passengers
	 */
	@Override
	public boolean reserveSeat()
	{
		// override the inherited reserveSeat method and call the reserveSeat method below with an economy seatType
		// use the constants defined at the top
		return this.reserveSeat(LongHaulFlight.economy);
	}

	/*
	 * Reserves a seat on a flight. Essentially just increases the number of passengers, depending on seat type (economy or first class)
	 */
	public boolean reserveSeat(String seatType)
	{
		// if seat type is economy 
		//			call the superclass method reserveSeat() and return the result
		// else if the seat type is first class then 
		// 			check to see if there are more first class seats available (use the aircraft method to get the max first class seats
		// 			of this airplane
		//    	if there is a seat available, increment first class passenger count (see instance variable at the top of the class)
		//    	return true;
		// else return false
		
		// remove this once you have written your code
		if (seatType.equals(LongHaulFlight.economy)) {
			return super.reserveSeat();
		}
		else if (seatType.equals(LongHaulFlight.firstClass)) {
			if (this.numFirstClassPassengers < this.aircraft.getNumFirstClassSeats()) {
				this.numFirstClassPassengers++;
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Overrides the super class cancelSeat
	 * 
	 */
	@Override
	public void cancelSeat()
	{
	  // override the inherited cancelSeat method and call the cancelSeat method below with an economy seatType
		// use the constants defined at the top
		this.cancelSeat(LongHaulFlight.economy);
	}
	
	/**
	 * This method either reduces the first class or economy passengers by 1
	 * If the seat type is of first class, the number of first class passengers is reduced
	 * If it is eocnomy, economy passenger number is reduced by 1
	 * @param seatType
	 */
	public void cancelSeat(String seatType)
	{
		// if seat type is first class and first class passenger count is > 0
		//  decrement first class passengers
		// else
		// decrement inherited (economy) passenger count
		
		if (seatType.equals(LongHaulFlight.firstClass) && this.numFirstClassPassengers > 0) {
			this.numFirstClassPassengers--;
		}
		else {
			this.passengers--;
		}
		
	}
	// return the total passenger count of economy passengers *and* first class passengers
	// use instance variable at top and inherited method that returns economy passenger count
	public int getPassengerCount()
	{
		return super.getPassengers() + this.numFirstClassPassengers;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\t\tLongHaul";
	}
}
