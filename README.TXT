Name: Kerry Kanhai
Student Number: 501063750
Course: CPS209


How to use the program:

Command "list" lists all the flights available

Command "res flightNum" will reserve a flight with that flight number. If that flight
is InFlight, it will not reserve. If the flight num is not found, it will print an error message. If there
are no seats available, it will print a message saying that flights are full


Command "myres" will show your current reservations. If one of the current reservations are first class seats,
it will print "FCL" in addition to the flight info

Command "respsngr flightNum PassengerName PassportNumber" will reserve a flight for that passenger with that name
and passport number

Command "psngrs flightNum" will show the passenger list for that flight.

Command "cnclpsngr PassengerName PassportNum" will remove the passenger with that name and passport number
from that flight. If the command is used on a passenger that doesn't exist, it will print out an error message

Command "resfcl flightNum" will reserve first class seats for a flight. If the flight is not a longhaul flight,
no reservation will be made and an error message will be printed. 

Command "seats flightNum" will show if there are seats available for that flight.

Command "sortbydur" will sort the flights list by duration and command "list" will print it in that order

Command "sortbydep" will sort the flights list by departure time and command "list" will print it in that order

Command "sortcraft" will sort the crafts that are being used