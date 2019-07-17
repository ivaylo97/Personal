package hotelserviceapp.sources;

import hotelserviceapp.hotelCommodities.domain.AbstractCommodity;
import hotelserviceapp.hotelCommodities.Bed;
import hotelserviceapp.hotelCommodities.Shower;
import hotelserviceapp.hotelCommodities.Toilet;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Rooms {
	static private int totalNumberOfRooms = 0;
	private int roomNumber;
	private int numberOfBeds;
	private int numberOfShowers;
	private int numberOfToilets;
	private Set<AbstractCommodity> commodities;
	private Set<LocalDate> maintenanceDates;
	private Set<Booking> bookings;

	/**
	 * Constructor initializing the members of the Rooms class
	 */
	public Rooms() {
		commodities = new HashSet<AbstractCommodity>();
		maintenanceDates = new HashSet<LocalDate>();
		bookings = new HashSet<Booking>();
		totalNumberOfRooms++;
		roomNumber = totalNumberOfRooms;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	/**
	 * This method invokes all of the commodities available for the maintenance of the room.
	 *
	 * @param newMaintenanceDate A Date typed object that represents a date at which the room is under maintenance.
	 */
	public void maintainRoom(LocalDate newMaintenanceDate) {
		for (AbstractCommodity commodity : commodities) {
			commodity.prepare();
		}
		maintenanceDates.add(newMaintenanceDate);
	}

	/**
	 * This method takes the guest's EGN , fromDate to tell us which is the booking's starting day , and
	 * toDate to tell us which is the booking's last day.And roomToBeBooked which is used to address the room
	 * that is to be booked.
	 * It later traverses the booking set and checks whether the selected dates are already booked by checking whether the
	 * starting/end dates are already present in the booking set.
	 * Returns room number on successful booking , and negative value on failure.
	 *
	 * @param guestEGN       newGuestEGN is a string type variable , containing the guest's EGN number.
	 * @param fromDate       fromDate is a LocalDate type variable which contains the requested booking's starting date.
	 * @param toDate         toDate is a LocalDate type variable which contains the requested booking's end date .
	 * @param roomToBeBooked roomToBeBooked is an object which represents the room that is being booked.
	 * @param numberOfDays   numberOfDays is an integer type variable which represents the number of days for which the room will be booked.
	 */
	public int createBooking(String guestEGN, LocalDate fromDate, LocalDate toDate, Rooms roomToBeBooked, int numberOfDays) {
		Booking objectToBeBooked = new Booking();
		Iterator<Booking> temporaryIterator = bookings.iterator();
		objectToBeBooked.updateRoom(guestEGN, fromDate, toDate, roomToBeBooked, numberOfDays);
		if(!objectToBeBooked.isPresentIn(bookings)) {
			if (checkAvailability(objectToBeBooked, temporaryIterator)){
				bookings.add(objectToBeBooked);
				return roomNumber;
			}
		}
		return -1;
	}

	/**
	 * An overloaded version of the upper method.The difference is that this method takes a predefined object
	 * as a parameter.
	 * It later traverses the booking set and checks whether the selected dates are already booked by checking whether
	 * the starting/end dates are already present in the booking set.
	 *
	 * @param temporaryObject Represents the object to be booked.
	 */
	public void createBooking(Booking temporaryObject) {

		Iterator<Booking> temporaryIterator = bookings.iterator();
		LocalDate temporaryObjectStartDate = temporaryObject.getStartDate();
		LocalDate temporaryObjectEndDate = temporaryObject.getEndDate();

		while (temporaryIterator.hasNext()) {
			if (temporaryObjectStartDate.isAfter(temporaryIterator.next().getStartDate())) {
				if (temporaryObjectStartDate.isBefore(temporaryIterator.next().getEndDate())) {
					System.out.println("The Starting date of your booking is already taken.");
					return;
				}
			} else {
				if (temporaryObjectEndDate.isAfter(temporaryIterator.next().getStartDate())) {
					System.out.println("The end date of your booking overlaps with another booking's days.");
					return;
				}
			}
		}
		bookings.add(temporaryObject);
	}

	/**
	 * This method removes a booking out of the booking set.
	 * It traverses the booking set and compares the dates,which have been passed as parameters , with those that are
	 * present in a member of the set.If the comparison results in a true value the set member is removed.
	 *
	 * @param fromDate LocalDate type variable  that represents the starting date of the booking that is to be removed.
	 * @param toDate   LocalDate type variable  that represents the end date of the booking that is to be removed.
	 * @param guestEGN String type variable which contains the EGN of the guest which booked the room.
	 */

	public boolean removeBooking(String guestEGN, LocalDate fromDate, LocalDate toDate) {
		Iterator<Booking> temporaryIterator = bookings.iterator();
		Booking tempBooking;
		while (temporaryIterator.hasNext()) {
			tempBooking = temporaryIterator.next();
			if (guestEGN.equals(tempBooking.getGuestID())) {
				if (tempBooking.getStartDate().equals(fromDate) && tempBooking.getEndDate().equals(toDate)) {
					temporaryIterator.remove();
					System.out.println("Booking remove successful!");
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Removes all of the bookings currently present in the booking set.
	 */
	public void removeAllBookings() {
		bookings.clear();
	}

	/**
	 * This method takes number of beds/showers/toilets in a single room , and adds them to the commodities.
	 *
	 * @param newNumberOfBeds    An int type variable ,used to set the number of beds present in the room,also to add that number of beds into
	 *                           the commodities set.
	 * @param newNumberOfShowers An int type variable ,used to set the number of showers present in the room,also to add that number of showers
	 *                           into the commodities set.
	 * @param newNumberOfToilets An int type variable , used to set the number of toilets present in the room , also to to add that number of
	 *                           toilets into the commodities set.
	 */
	public void setCommodities(int newNumberOfBeds, int newNumberOfShowers, int newNumberOfToilets) {
		if(newNumberOfBeds >= 0 && newNumberOfShowers >= 0 && newNumberOfToilets >= 0){
			numberOfBeds = newNumberOfBeds;
			numberOfShowers = newNumberOfShowers;
			numberOfToilets = newNumberOfToilets;

		}

		addBeds();
		addShowers();
		addToilets();
	}

	/**
	 * Returns the number of beds in the room.
	 *
	 * @return numberOfBeds
	 */
	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	/**
	 * Returns the number of showers in the room.
	 *
	 * @return numberOfShowers
	 */
	public int getNumberOfShowers() {
		return numberOfShowers;
	}

	/**
	 * Returns the number of toilets in the room.
	 *
	 * @return numberOfToilets
	 */
	public int getNumberOfToilets() {
		return numberOfToilets;
	}

	public Set<LocalDate> getMaintenanceDates() {
		return maintenanceDates;
	}

	public Set<Booking> getBookings() {
		return bookings;
	}

	public Set<AbstractCommodity> getCommodities() {
		return commodities;
	}

	private boolean checkAvailability(Booking objectToBeBooked, Iterator<Booking> temporaryIterator) {

		Booking temporaryObject;
		LocalDate objectToBeBookedStartDate = objectToBeBooked.getStartDate();
		LocalDate objectToBeBookedEndDate = objectToBeBooked.getEndDate();

		while (temporaryIterator.hasNext()) {

			temporaryObject = temporaryIterator.next();

			if (objectToBeBookedStartDate.isAfter(temporaryObject.getStartDate())) {
				if (objectToBeBookedStartDate.isBefore(temporaryObject.getEndDate())) {
					System.out.println("The Starting date of your booking is already taken.");
					return false;
				}
			} else {
				if (objectToBeBookedEndDate.isAfter(temporaryObject.getStartDate())) {
					System.out.println("The end date of your booking overlaps with another booking's days");
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Adds as much beds as specified in the numberOfBeds variable.
	 */
	private void addBeds() {
		Iterator<AbstractCommodity> tempIterator;

		for (int bedCounter = 0, addFlag = 1; bedCounter < numberOfBeds; addFlag = 1, bedCounter++) {
			tempIterator = commodities.iterator();
			Bed newBed = new Bed();
			if(tempIterator.hasNext()) {
				while (tempIterator.hasNext()) {
					if (tempIterator.next().equals(newBed)) {
						addFlag = 0;
						bedCounter--;
					}
				}
			}
			if (addFlag == 1) {
				commodities.add(newBed);
			}
		}

	}

	/**
	 * Adds as much showers as, are specified in the numberOfShowers variable.
	 */
	private void addShowers() {
		Iterator<AbstractCommodity> tempIterator;

		for (int showerCounter = 0, addFlag = 1; showerCounter < numberOfShowers; addFlag = 1, showerCounter++) {
			tempIterator = commodities.iterator();
			Shower newShower = new Shower();
			if(tempIterator.hasNext()) {
				while (tempIterator.hasNext()) {
					if (tempIterator.next().equals(newShower)) {
						addFlag = 0;
						showerCounter--;
					}
				}
			}
			if (addFlag == 1) {
				commodities.add(newShower);
			}
		}
	}

	/**
	 * Adds as much toilets as specified in the numberOfToilets variable.
	 */
	private void addToilets() {
		Iterator<AbstractCommodity> tempIterator;

		for (int showerCounter = 0, addFlag = 1; showerCounter < numberOfToilets; addFlag = 1, showerCounter++) {
			tempIterator = commodities.iterator();
			Toilet newToilet = new Toilet();
			if(tempIterator.hasNext()) {
				while (tempIterator.hasNext()) {
					if (tempIterator.next().equals(newToilet)) {
						addFlag = 0;
						showerCounter--;
					}
				}
			}
			if (addFlag == 1) {
				commodities.add(newToilet);
			}
		}
	}

	@Override
	public boolean equals(Object compareObject) {
		if (!(compareObject instanceof Rooms)) return false;
		return this.hashCode() == compareObject.hashCode();
	}
	@Override
	public int hashCode(){
		return this.roomNumber;
	}
}
