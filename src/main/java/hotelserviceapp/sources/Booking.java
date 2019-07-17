package hotelserviceapp.sources;

import java.time.*;
import java.util.Set;

public class Booking {
	static private int totalNumberOfBookings  = 0;
	private int bookingID ;
	private LocalDate startDate;
	private LocalDate endDate;
	private int numberOfDays;
	private Rooms bookedRoom;
	private String guestID;

	public Booking() {
		guestID = "";
		totalNumberOfBookings ++;
		bookingID = totalNumberOfBookings;
	}

	public Booking(String EGN, LocalDate newStartDate, LocalDate newEndDate, Rooms roomToBeBooked) {
		assertAndSetID(EGN);
		assertAndSetDates(newStartDate, newEndDate);
		assertRoom(roomToBeBooked);
		totalNumberOfBookings ++;
		bookingID = totalNumberOfBookings;
	}

	/**
	 * This method takes the listed parameters below and uses them to initialize the class' members.
	 *
	 * @param newGuestID     newGuestID is a string type variable , containing the guest's EGN number.
	 * @param newStartDate    newStartDate is a LocalDate type variable which contains the requested booking's starting date.
	 * @param newEndDate      newEndDate is a LocalDate type variable which contains the requested booking's end date .
	 * @param newBookedRoom   newBookedRoom is an object which represents the room that is being booked.
	 * @param newNumberOfDays newNumberOfDays is an integer type variable which represents the number of days for which the room will be booked.
	 */

	public void updateRoom(String newGuestID, LocalDate newStartDate, LocalDate newEndDate, Rooms newBookedRoom, int newNumberOfDays) {
		assertAndSetID(newGuestID);
		assertAndSetDates(newStartDate, newEndDate);
		assertRoom(newBookedRoom);
		numberOfDays = newNumberOfDays;

	}

	/**
	 * When invoked this method returns the booking's start date.
	 *
	 * @return startDate
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * When invoked this method returns the booking's end date.
	 *
	 * @return endDate
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * When invoked this method returns the EGN of the guest which owns this booking.
	 *
	 * @return
	 * Returns the EGN of the guest
	 */
	public String getGuestID() {
		return guestID;
	}

	/**
	 * Returns the number of days variable.
	 *
	 * @return
	 */
	public int getNumberOfDays() {
		return numberOfDays;
	}

	/**
	 * Returns the booked room object.
	 *
	 * @return
	 */
	public Rooms getBookedRoom() {
		return bookedRoom;
	}

	/**
	 * Method for checking if the guest's ID has a correct value.
	 *
	 * @param ID The guest's ID.
	 */
	private void assertAndSetID(String ID) {
		if (ID.length() < 10) {
			System.out.println("ERROR :: Invalid ID !");
			guestID = "";
			return;
		}
		if (ID.isEmpty()) {
			System.out.println("WARNING :: ID Empty String!");
			return;
		}
		if (ID == null) {
			System.out.println("ERROR :: ID is null !");
			guestID = "";
			return;
		}
		guestID = ID;
	}

	/**
	 * Asserts whether the dates have correct values.
	 *
	 * @param fromDate The booking's starting date.
	 * @param toDate   The booking's end date.
	 */
	private void assertAndSetDates(LocalDate fromDate, LocalDate toDate) {
		if (fromDate == null) {
			System.out.println("ERROR :: fromDate has a null value !");
			return;
		}
		if (toDate == null) {
			System.out.println("ERROR :: toDate has a null value !");
			return;
		}
		if (fromDate.isBefore(LocalDate.now())) {
			System.out.println("ERROR :: Invalid value :: The staring date is before today !");
			//return;
		}
		if (toDate.isBefore(LocalDate.now())) {
			System.out.println("ERROR :: Invalid value :: The end date is before today !");
			//return;
		}
		if (fromDate.isAfter(toDate)) {
			System.out.println("ERROR :: Thr starting date is after the end date !");
			return;
		}
		startDate = fromDate;
		endDate = toDate;
	}

	/**
	 * Asserts whether the room to be booked is a valid object.
	 *
	 * @param roomToBeBooked roomToBeBooked represents the object that is about to be booked.
	 */
	void assertRoom(Rooms roomToBeBooked) {
		if (roomToBeBooked == null) {
			System.out.println("ERROR :: Room object has null value !");
			return;
		}
		if (roomToBeBooked.getRoomNumber() <= 0) {
			System.out.println("WARNING :: Room number has negative value, may be invalid object !");
		}
		bookedRoom = roomToBeBooked;
	}

	public boolean isPresentIn(Set<Booking> bookings){

		for (Booking booking : bookings) {
			if (this.hashCode() == booking.hashCode()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean equals(Object compareObject) {
		if (!(compareObject instanceof Booking)) return false;
		return this.hashCode() ==compareObject.hashCode();
	}
	@Override
	public int hashCode(){
		return this.bookingID;
	}
}
