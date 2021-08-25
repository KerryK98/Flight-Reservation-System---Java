 /*
 * A simple class to model an electronic airline flight reservation
 * 
 * This class has been done for you
 */
/**
 * 
 * @author Kerry Kanhai
 * Student Number: 501063750
 *
 */
public class Reservation
{
	String flightNum;
	String flightInfo;
	boolean firstClass;
	Passenger passenger;
	
	
	public Reservation(String flightNum, String info)
	{
		this.flightNum = flightNum;
		this.flightInfo = info;
		this.firstClass = false;
	}
	
	public Reservation(String flightNum, String info, Passenger passenger)
	{
		this.flightNum = flightNum;
		this.flightInfo = info;
		this.firstClass = false;
		this.passenger = passenger;
	}
	
	public Passenger getPassenger() {
		return passenger;
	}
	
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	
	public boolean isFirstClass()
	{
		return firstClass;
	}

	public void setFirstClass()
	{
		this.firstClass = true;
	}

	public String getFlightNum()
	{
		return flightNum;
	}
	
	public String getFlightInfo()
	{
		return flightInfo;
	}

	public void setFlightInfo(String flightInfo)
	{
		this.flightInfo = flightInfo;
	}

	public void print()
	{
		System.out.println(flightInfo);
	}
}
