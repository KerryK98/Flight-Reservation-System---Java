import java.util.ArrayList;
import java.util.Random;
/* 
 *  Class to model an airline flight. In this simple system, all flights originate from Toronto
 *  
 *  This class models a simple flight that has only economy seats
 */

/**
 * 
 * @author Kerry Kanhai
 * Student Number: 501063750
 *
 */
public class Flight
{
	public enum Status {DELAYED, ONTIME, ARRIVED, INFLIGHT};

	String flightNum;
	String airline;
	String origin, dest;
	String departureTime;
	Status status; // see enum Status above. google this to see how to use it
	int flightDuration;
	Aircraft aircraft;
	protected int passengers; // count of (economy) passengers on this flight - initially 0
	ArrayList<Passenger> passengerList;
	
	public Flight()
	{
		// write code to initialize instance variables to default values
		this.flightNum = "";
		this.airline = "";
		this.origin = "";
		this.dest = "";
		this.departureTime = " ";
		this.status = Status.ONTIME;
		this.aircraft = null;
		this.passengers = 0;
		passengerList = new ArrayList<Passenger>();
	}
	
	public Flight(String flightNum, String airline, String dest, String departure, int flightDuration, Aircraft aircraft)
	{
		this.flightNum = flightNum;
		this.airline = airline;
		this.dest = dest;
		this.origin = "Toronto";
		this.departureTime = departure;
		this.flightDuration = flightDuration;
		this.aircraft = aircraft;
		passengers = 0;
		status = Status.ONTIME;
		passengerList = new ArrayList<Passenger>();
		
	}
	
	public ArrayList<Passenger> getPassengerList() {
		return this.passengerList;
	}
	
	public String getFlightNum()
	{
		return flightNum;
	}
	public void setFlightNum(String flightNum)
	{
		this.flightNum = flightNum;
	}
	public String getAirline()
	{
		return airline;
	}
	public void setAirline(String airline)
	{
		this.airline = airline;
	}
	public String getOrigin()
	{
		return origin;
	}
	public void setOrigin(String origin)
	{
		this.origin = origin;
	}
	public String getDest()
	{
		return dest;
	}
	public void setDest(String dest)
	{
		this.dest = dest;
	}
	public String getDepartureTime()
	{
		return departureTime;
	}
	public void setDepartureTime(String departureTime)
	{
		this.departureTime = departureTime;
	}
	
	public Status getStatus()
	{
		return status;
	}
	public void setStatus(Status status)
	{
		this.status = status;
	}
	public int getFlightDuration()
	{
		return flightDuration;
	}
	public void setFlightDuration(int dur)
	{
		this.flightDuration = dur;
	}
	
	public int getPassengers()
	{
		return passengers;
	}
	public void setPassengers(int passengers)
	{
		this.passengers = passengers;
	}
	
	/**
	 * Checks if seats are available on the current flight
	 * If the number of passengers are less than available
	 * seats, then it returns true
	 * 
	 * Else returns false
	 * 
	 * @return
	 */
	public boolean seatsAvailable()
	{
		// your code here
		if (this.passengers < aircraft.getNumSeats()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Cancel a seat on this flight - If there are passengers in the flight, reduce the passengers number by 1
	 */
	public void cancelSeat()
	{
		if (this.passengers > 0) {
			this.passengers--;
		}
	}
	
	/*
	 * reserve a seat on this flight - essentially increases the passenger count by 1 only if there is room for more
	 * economy passengers on the aircraft used for this flight (see instance variables above)
	 */
	public boolean reserveSeat()
	{
		// your code here
		if (this.passengers < aircraft.getNumSeats()) {
			this.passengers++;
			return true;
		}
		
		return false;
	}
	
	/**
	 * Returns true if a successful reservation has been made as a passenger
	 * It first randomizes a seat number to assign to the passenger
	 * If the seat number is taken, it will generate a new random number and 
	 * loop again to see if it has been taken until it gets an unused seat number
	 * After the seat number has been assigned, the passenger is added to the passenger
	 * list of the flight
	 * 
	 * @param passenger
	 * @return boolean value
	 */
	public boolean passengerReserve(Passenger passenger) {
		if (this.reserveSeat()) {
			Random random = new Random();
			int seatNum = random.nextInt(aircraft.getNumSeats()) + 1;	
			int index = 0;
			
			// While loop to continuosly 
			while (true) {
				if (index == passengerList.size()) {
					break;
				}
				if (passengerList.get(index).getSeatNum() == seatNum) {
					seatNum = random.nextInt(aircraft.getNumSeats()) + 1;
					index = 0;
				}
				else {
					index++;
				}
			}
			passenger.setSeatNum(seatNum);
			passengerList.add(passenger);
			return true;
		}
		else {
			return false;
		}
	}
	
	public String toString()
	{
		 return airline + "\t Flight:  " + flightNum + "\t Dest: " + dest + "\t Departing: " + departureTime + "\t Duration: " + flightDuration + "\t Status: " + status;
		
	}

  
}
