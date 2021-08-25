import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Kerry Kanhai
 * Student Number: 501063750
 *
 */

// Flight System for one single day at YYZ (Print this in title) Departing flights!!


public class FlightReservationSystem
{
	public static void main(String[] args)
	{
		// Create a FlightManager object
		FlightManager manager = new FlightManager();

		// List of reservations that have been made
		ArrayList<Reservation> myReservations = new ArrayList<Reservation>();	// my flight reservations

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		while (scanner.hasNextLine())
		{
			String inputLine = scanner.nextLine();
			if (inputLine == null || inputLine.equals("")) continue;

			// The command line is a scanner that scans the inputLine string
			// For example: list AC201
			Scanner commandLine = new Scanner(inputLine);
			
			// The action string is the command to be performed (e.g. list, cancel etc)
			String action = commandLine.next();

			if (action == null || action.equals("")) continue;

			if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;
			
			// List all flights
			else if (action.equalsIgnoreCase("LIST"))
			{
				manager.printAllFlights(); 
			}
			// Reserve a flight based on Flight number string (example input: res AC220)
			else if (action.equalsIgnoreCase("RES"))
			{
				// Get the flight number string from the commndLine scanner (use hasNext() to check if there is a
				// flight number string entered
				if (commandLine.hasNext()) {
					String flightNum = commandLine.next();
					Reservation myRes = manager.reserveSeatOnFlight(flightNum, flightNum);
					if (myRes == null) {
						String errorMsg = manager.getErrorMessage();
						System.out.println(errorMsg);
					}
					else {
						myReservations.add(myRes);
						System.out.println(myRes.getFlightInfo());
					}
				}
				
				// call reserveSeatOnFlight() method in manager passing in the flight number string
				// A reference to a Reservation object is returned. Check to make sure it is not == null
				// If it is null, then call manager.getErrorMessage() and print the message
				// If it is not null, add the reservation to the myReservations array list and print the reservation (see class Reservation)
				
			}
		  // Reserve a first class seat on a flight based on Flight number string (example input: res AC220)
			else if (action.equalsIgnoreCase("RESFCL"))
			{
				if (commandLine.hasNext()) {
					String flightNum = commandLine.next();
					Reservation myRes = manager.reserveSeatOnFlight(flightNum, LongHaulFlight.firstClass);
					if (myRes == null) {
						String errorMsg = manager.getErrorMessage();
						System.out.println(errorMsg);
					}
					else if (!myRes.firstClass) {
						System.out.println("Flight has no first class seats!");
					}
					else {
						myReservations.add(myRes);
						System.out.println(myRes.getFlightInfo());
					}
				}
				// Same as above except call 
				// manager.reserveSeatOnFlight() and pass in the flight number string as well as the string constant:
				// LongHaulFlight.firstClass (see class LongHaulFlight)
				// Do the LongHaulFlight class and this command after all the basic functionality is working for regular flights
			}
			// Query the flight manager to see if seats are still available for a specific flight (example input: seats AC220)
		  // This one is done for you as a guide for other commands
			else if (action.equalsIgnoreCase("SEATS"))
			{
				String flightNum = null;

				if (commandLine.hasNext())
				{
					flightNum = commandLine.next();
					
					if (manager.seatsAvailable(flightNum))
					{
						System.out.println("Seats are available");
					}
					else
					{
						System.out.println(manager.getErrorMessage());
					}
				}
			}
			// Cancel an existing reservation (example input: cancel AC220) 
			else if (action.equalsIgnoreCase("CANCEL"))
			{
				String flightNum = null;
				boolean found = false;
				if (commandLine.hasNext()) {
					flightNum = commandLine.next();
					for (int i = 0; i < myReservations.size(); i++) {
						if (myReservations.get(i).getFlightNum().equals(flightNum)) {
							found = manager.cancelReservation(myReservations.get(i));
							myReservations.remove(myReservations.get(i));
						}
					}
				}
				if (!found) {
					System.out.println(manager.getErrorMessage());
				}
        // get the flight number string from commandLine scanner (check if there is input first)
				// Use the flight number to find the Reservation object in the myReservations array list
				// If the reservation is found,  
				// 		call cancelReservation() method in the flight manager
				//    remove the reservation from myReservations
				// If the reservation is not found, print a message (see video)
			}
			else if (action.equalsIgnoreCase("RESPSNGR"))
			{
				String flightNum = null;
				boolean found = false;
				Passenger passenger = new Passenger();
				if (commandLine.hasNext()) {
					flightNum = commandLine.next();
					passenger.setName(commandLine.next());
					passenger.setPassportNumber(commandLine.next());
					Reservation myRes = manager.passengerReserveSeat(passenger, flightNum);
					if (myRes == null) {
						String errorMsg = manager.getErrorMessage();
						System.out.println(errorMsg);
					}
					else {
						myReservations.add(myRes);
						System.out.println(myRes.getFlightInfo());
					}
				}
			}
			else if (action.equalsIgnoreCase("CNCLPSNGR")) 
			{
				boolean cancel = false;
				if (commandLine.hasNext()) {
					String name = commandLine.next();
					String passportNum = commandLine.next();
					Passenger passenger = new Passenger();
					passenger.setName(name);
					passenger.setPassportNumber(passportNum);
					for (int i = 0; i < myReservations.size(); i++) {
						if (myReservations.get(i).getPassenger() != null && myReservations.get(i).getPassenger().equals(passenger)) {
							cancel = manager.passengerCancelReservation(passenger);
							myReservations.remove(myReservations.get(i));
						}
					}
				}
				if (!cancel) {
					System.out.println("Passenger not found");
				}
			}
			else if (action.equalsIgnoreCase("PSNGRS")) 
			{
				if (commandLine.hasNext()) {
					String flightNum = commandLine.next();
					for (int i = 0; i < myReservations.size(); i++) {
						if (myReservations.get(i).getFlightNum().equals(flightNum)) {
							System.out.println("Name: " + myReservations.get(i).getPassenger().getName()
									+ "\tPassport Number: " + myReservations.get(i).getPassenger().getPassportNumber()
									+ "\t\tSeat Number: " + myReservations.get(i).getPassenger().getSeatNum());
						}
					}
				}
				
			}
      // Print all the reservations in array list myReservations
			else if (action.equalsIgnoreCase("MYRES"))
			{
				for (int i = 0; i < myReservations.size(); i++) {
					System.out.println(myReservations.get(i).getFlightInfo());
				}
			}
			// Print the list of aircraft (see class Manager)
			else if (action.equalsIgnoreCase("CRAFT"))
		  {
			  manager.printAllAircraft();
			}
			// These commands can be left until we study Java interfaces
			// Feel free to implement the code in class Manager if you already understand interface Comparable
			// and interface Comparator
			else if (action.equalsIgnoreCase("SORTCRAFT"))
		  {
		  	manager.sortAircraft();
		  }
		  else if (action.equalsIgnoreCase("SORTBYDEP"))
		  {
			  manager.sortByDeparture();
			  
		  }
		  else if (action.equalsIgnoreCase("SORTBYDUR"))
		  {
			  manager.sortByDuration();
		  }
	  
			System.out.print("\n>");
		}
	}

	
}

