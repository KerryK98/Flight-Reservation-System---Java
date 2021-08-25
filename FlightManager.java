import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author Kerry Kanhai
 * Student Number: 501063750
 *
 */

public class FlightManager
{
  // Contains list of Flights departing from Toronto in a single day
	ArrayList<Flight> flights = new ArrayList<Flight>();
  
  String[] cities = 	{"Dallas", "New York", "London", "Paris", "Tokyo"};
  final int DALLAS = 0;  final int NEWYORK = 1;  final int LONDON = 2;  final int PARIS = 3; final int TOKYO = 4;
  
  // flight times in hours
  int[] flightTimes = { 3, // Dallas
  											1, // New York
  											7, // London
  											8, // Paris
  											16// Tokyo
  										};
  
  // Contains list of available airplane types and their seat capacity
  ArrayList<Aircraft> airplanes = new ArrayList<Aircraft>();  
  
  String errorMsg = null; // if a method finds an error (e.g. flight number not found) set this string. See video!
  
  Random random = new Random(); // random number generator - google "Java class Random". Use this in generateFlightNumber
  
  
  public FlightManager()
  {
  	// DO NOT ALTER THIS CODE - THE TA'S WILL USE IT TO TEST YOUR PROGRAM
  	// IN ASSIGNMENT 2 YOU WILL BE LOADING THIS INFORMATION FROM A FILE
  
  	// Create some aircraft types with max seat capacities
  	airplanes.add(new Aircraft(85, "Boeing 737"));
  	airplanes.add(new Aircraft(180,"Airbus 320"));
  	airplanes.add(new Aircraft(37, "Dash-8 100"));
  	airplanes.add(new Aircraft(12, "Bombardier 5000"));
  	airplanes.add(new Aircraft(592, 14, "Boeing 747"));
  	
  	// Populate the list of flights with some random test flights
  	String flightNum = generateFlightNumber("United Airlines");
  	Flight flight = new Flight(flightNum, "United Airlines", "Dallas", "1400", flightTimes[DALLAS], airplanes.get(0));
  	flights.add(flight);
  	flight.setStatus(Flight.Status.DELAYED);
  	
   	flightNum = generateFlightNumber("Air Canada");
   	flight = new Flight(flightNum, "Air Canada", "London", "2300", flightTimes[LONDON], airplanes.get(1));
   	flights.add(flight);
   	
   	flightNum = generateFlightNumber("Air Canada");
   	flight = new Flight(flightNum, "Air Canada", "Paris", "2200", flightTimes[PARIS], airplanes.get(1));
   	flights.add(flight);
   	
   	flightNum = generateFlightNumber("Porter Airlines");
   	flight = new Flight(flightNum, "Porter Airlines", "New York", "1200", flightTimes[NEWYORK], airplanes.get(2));
   	flights.add(flight);
   	
   	flightNum = generateFlightNumber("United Airlines");
   	flight = new Flight(flightNum, "United Airlines", "New York", "0900", flightTimes[NEWYORK], airplanes.get(3));
   	flights.add(flight);
   	flight.setStatus(Flight.Status.INFLIGHT);
   	
   	flightNum = generateFlightNumber("Air Canada");
   	flight = new Flight(flightNum, "Air Canada", "New York", "0600", flightTimes[NEWYORK], airplanes.get(2));
   	flights.add(flight);
   	flight.setStatus(Flight.Status.INFLIGHT);
   	
   	
   	flightNum = generateFlightNumber("United Airlines");
   	flight = new Flight(flightNum, "United Airlines", "Paris", "2330", flightTimes[PARIS], airplanes.get(0));
   	flights.add(flight);
   	
    /*
     * Add this code back in when you are ready to tackle class LongHaulFlight and have implemented its methods
     */
   	flightNum = generateFlightNumber("Air Canada");
   	flight = new LongHaulFlight(flightNum, "Air Canada", "Tokyo", "2200", flightTimes[TOKYO], airplanes.get(4));
   	flights.add(flight);
  }
  
  /**
   * Generates a random flight number between 101 and 301
   * Returns the short form of the appropriate airline combined with the 
   * flight number
   * 
   * @param airline
   * @return
   */
  private String generateFlightNumber(String airline)
  {
    // Your code here
	  String flightNum = "";
	  int randomNum = random.nextInt(200) + 101;
	  
	  if (airline.equals("Air Canada")) {
		  flightNum = "AC";
	  }
	  else if (airline.equals("United Airlines")) {
		  flightNum = "UA";
	  }
	  else if (airline.equals("Porter Airlines")) {
		  flightNum = "PA";
	  }
	  
	  return flightNum + randomNum;
  }

  /**
   * Prints the flight by calling the toString method for Flight
   */
  public void printAllFlights()
  {
  	for (int i = 0; i < flights.size(); i++)
  	{
  		System.out.println(flights.get(i).toString());
  	}
  }
  
  /**
   * This method calls the seatsAvailable method for flight to check if there are seats available
   * It checks for the appropriate flight num in the flights arraylist and calls the method
   * on that flight 
   * 
   * @param flightNum
   * @return
   */
  public boolean seatsAvailable(String flightNum)
  {
    // First check for a valid flight number
    // if it is not found, set errorMsg String and return false
    // To determine if the given flightNum is valid, search the flights array list and find 
    // the  Flight object with matching flightNum string
    // Once a Flight object is found, check if seats are available (see class Flight) 
    // if flight is full, set errorMsg to an appropriate message (see video) and return false
    // otherwise return true
    for (int i = 0; i < flights.size(); i++) {
    	if (flights.get(i).getFlightNum().equals(flightNum)) {
    		if (flights.get(i).seatsAvailable()) {
    			return true;
    		}
    		else {
    			this.errorMsg = "Flight " + flights.get(i).getFlightNum() + " is full";
    			return false;
    		}
    	}
    }
    
    this.errorMsg = "Flight " + flightNum + " Not Found";
    return false;
  }
  
  /**
   * Returns a reservation object if a successful reservation is made 
   * It first loops through the flights to find the matching flight num
   * When the matching flight num is found, it checks if that flight is in flight
   * If it is, it sets the error message and returns null
   * 
   * If the flight num is a first class seat, it reserves a first class seat by
   * calling the LongHaul first class reservation and sets the reservation to type first class
   * It then returns the reservation object
   * 
   * If not first class, it reserves an economy seat by creating a new Reservation object and returning that
   * 
   * If flights are either full or don't exist, the appropriate error messages are set
   * 
   * 
   * @param flightNum
   * @param seatType
   * @return
   */
  public Reservation reserveSeatOnFlight(String flightNum, String seatType)
  {
  	// Check for valid flight number by searching through flights array list
  	// If matching flight is not found, set instance variable errorMsg (see at top) and return null 
  	
	  for (int i = 0; i < flights.size(); i++) {
		  if (flights.get(i).getFlightNum().equals(flightNum)) {
			  if (flights.get(i).getStatus().equals(Flight.Status.INFLIGHT)) {
				  this.errorMsg = "Flight is InFlight. Choose another flight!";
				  return null;
			  }
			  else if (seatType.equals(LongHaulFlight.firstClass) && flights.get(i) instanceof LongHaulFlight) {
				  LongHaulFlight tempFlight = (LongHaulFlight) flights.get(i);
				  boolean reserved = tempFlight.reserveSeat(seatType);
				  if (reserved) {
					  String flightInfo = tempFlight.toString() + "\n\tFCL";
					  Reservation reservation = new Reservation(tempFlight.getFlightNum(), flightInfo);
					  reservation.setFirstClass();
					  return reservation;
				  }
				  else {
					  this.errorMsg = "Flight " + flights.get(i).getFlightNum() + " is full";
					  return null;
				  }
			  }
			  else {
				  boolean reserved = flights.get(i).reserveSeat();
				  if (reserved) {
					  String flightInfo = flights.get(i).toString();
					  Reservation reservation = new Reservation(flights.get(i).getFlightNum(), flightInfo);
					  return reservation;
				  }
				  else {
					  this.errorMsg = "Flight " + flights.get(i).getFlightNum() + " is full";
					  return null;
				  }
			  }
		  }
	  }
	  this.errorMsg = "Flight " + flightNum + " Not Found";
	  return null;
	  
	  
  	// If flight found
  	//    
  	//		****beginning of long haul flight code - you may want to skip initially
  	//		check if seat type is first class and check if this is a long haul flight (Hint: use instanceof operator)
  	//    if above is true
  	//			call reserveSeat method in class LongHaulFlight
  	//			if long haul flight first class is not full
  	//				create Reservation object, set firstClass boolean variable to true in Reservation object
  	//				return reference to Reservation object
  	//			else long haul first class is full
  	//				set errorMsg and return null
  	//		***end of long haul flight code
  	//
  	//		else must be economy seat 
  	//			reserve seat on flight (see class Flight reserveSeat() and overridden reserveSeat() in class LongHaulFlight)
  	//      if flight not full
  	//				create Reservation object and return reference to Reservation object 
  	//			else set ErrorMesg (flight full) and return null
  	
  	 // remove when finished code above
  }
  
  /**
   * This method returns a reservation for a passenger
   * It first checks if the flight is in flight and if it is, it returns a null object with the appropriate error message
   * 
   * 
   * @param passenger
   * @param flightNum
   * @return
   */
  public Reservation passengerReserveSeat(Passenger passenger, String flightNum) {
	  for (int i = 0; i < flights.size(); i++) {
		  if (flights.get(i).getFlightNum().equals(flightNum)) {
			  if (flights.get(i).getStatus().equals(Flight.Status.INFLIGHT)) {
				  this.errorMsg = "Flight is InFlight. Choose another flight!";
				  return null;
			  }
			  boolean reserved = flights.get(i).passengerReserve(passenger);
			  if (reserved) {
				  String flightInfo = flights.get(i).toString();
				  Reservation reservation = new Reservation(flightNum, flightInfo, passenger);
				  return reservation;
			  }
			  else {
				  this.errorMsg = "Flight " + flights.get(i).getFlightNum() + " is full";
				  return null;
			  }
		  }
	  }
	  
	  this.errorMsg = "Flight " + flightNum + " Not Found";
	  return null;
  }
  
  /**
   * This method allows a passenger to cancel a reservation
   * The method loops through the flights
   * It then checks if the passengerList for each flight is empty or not
   * If it is not empty, it goes through the passengerList to find the matching passenger passed in
   * If found, the passenger is removed from the passengerList and the seat is cancelled and true is returned
   * 
   * If no passenger is found, the appropriate error message is set 
   * and false is returned 
   * 
   * @param passenger
   * @return
   */
  public boolean passengerCancelReservation(Passenger passenger) {
	  for (int i = 0; i < flights.size(); i++) {
		  if (!flights.get(i).getPassengerList().isEmpty()) {
			  for (int j = 0; j < flights.get(i).getPassengerList().size(); i++) {
				  if (flights.get(i).getPassengerList().get(j).equals(passenger)) {
					  flights.get(i).getPassengerList().remove(j);
					  flights.get(i).cancelSeat();
					  return true;
				  }
			  }
		  }
	  }
	  
	  this.errorMsg = "Passenger '" + passenger.getName() + "' " + " with passport number " + passenger.getPassportNumber() + " Not Found";
	  return false;
  }
  
  public String getErrorMessage()
  {
  	return errorMsg;
  }
  
  /**
   * Finds the appropriate flight num using the flight num from the reservation object
   * If the flight num exists, it checks if the flight is a first class seat 
   * If it is a first class seat, it calls the method from LongHaulFlight
   * otherwise it calls the regular cancelSeat method
   * If no appropriate flight num is found, the error message is set and 
   * false is returned
   * 
   * @param res
   * @return boolean value
   */
  public boolean cancelReservation(Reservation res)
  {
  	// Get the flight number string from res
  	// Search flights to find the Flight object - if not found, set errorMsg variable and return false
  	// if found, cancel the seat on the flight (see class Flight)
  	String flightNum = res.getFlightNum();
  	for (int i = 0; i < flights.size(); i++) {
  		if (flightNum.equals(flights.get(i).getFlightNum())) {
  			if (res.isFirstClass() && flights.get(i) instanceof LongHaulFlight) {
  				LongHaulFlight tempFlight = (LongHaulFlight) flights.get(i);
  				tempFlight.cancelSeat(LongHaulFlight.firstClass);
  				return true;
  			}
  			else {
  				flights.get(i).cancelSeat();
  	  			return true;
  			}
  		}
  	}
  	this.errorMsg = "Flight " + flightNum + " Not Found";
  	return false;
  	// Once you have the above basic functionality working, try to get it working for canceling a first class reservation
  	// If this is a first class reservation (see class Reservation) and the flight is a LongHaulFlight (Hint use instanceof)
  	// then cancel the first class seat on the LongHaulFlight (Hint: you will need to cast)   
  	
     // remove this when you have written the code above
  }
  
  // Sort the array list of flights by increasing departure time 
  // Essentially one line of code but you will be making use of a Comparator object below
  public void sortByDeparture()
  {
	  Collections.sort(flights, new DepartureTimeComparator());
  }
  // Write a simple inner class that implements the Comparator interface (NOTE: not *Comparable*)
  // This means you will be able to compare two Flight objects by departure time
  private class DepartureTimeComparator implements Comparator<Flight>
  {
	  public int compare(Flight a, Flight b) {
		  return (a.getDepartureTime().compareTo(b.getDepartureTime()));
	  }
  }
  //Sort the array list of flights by increasing flight duration  
  // Essentially one line of code but you will be making use of a Comparator object below
  public void sortByDuration()
  {
	  Collections.sort(flights, new DurationComparator());
  }
  //Write a simple inner class that implements the Comparator interface (NOTE: not *Comparable*)
 // This means you will be able to compare two Flight objects by duration
  private class DurationComparator implements Comparator<Flight>
  {
	  public int compare(Flight a, Flight b) {
		  if (a.getFlightDuration() > b.getFlightDuration()) {
			  return 1;
		  }
		  else if (a.getFlightDuration() < b.getFlightDuration()) {
			  return -1;
		  }
		  else {
			  return 0;
		  }
	  }
  }
  // Prints all aircraft in airplanes array list. 
  // See class Aircraft for a print() method
  public void printAllAircraft()
  {
	  for (int i = 0; i < airplanes.size(); i++) {
		  airplanes.get(i).print();
	  }
  }
  
  // Sort the array list of Aircraft objects 
  // This is one line of code. Make sure class Aircraft implements the Comparable interface
  public void sortAircraft()
  {
	  Collections.sort(airplanes);
  }
  
}
