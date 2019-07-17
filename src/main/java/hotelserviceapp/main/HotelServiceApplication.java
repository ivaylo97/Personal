package hotelserviceapp.main;
import hotelserviceapp.sources.*;


import java.time.LocalDate;


public class HotelServiceApplication {

	public static void main(String[] args) {

		Manager Ivan = new Manager();
		Hotel transylvania = new Hotel();

		Rooms room101 = new Rooms();
		Rooms room102 = new Rooms();
		Rooms room103 = new Rooms();

		room101.setCommodities(1, 1, 1);
		room102.setCommodities(3, 1, 2);
		room103.setCommodities(2, 1, 1);

		transylvania.setHotelName("transylvania");
		Ivan.setHotel(transylvania);
		transylvania.addNewRoom(0, room101);
		transylvania.addNewRoom(1, room102);
		transylvania.addNewRoom(2, room103);

		Ivan.bookRoom("1111111111", configureDate("2019-07-20"), configureDate("2019-07-25"), 2, 5);
		Ivan.bookRoom("2222222222", configureDate("2019-07-22"), configureDate("2019-07-23"), 1, 1);
		Ivan.bookRoom("4444444444", configureDate("2019-07-19"), configureDate("2019-07-21"), 2, 2);

		for (int generalPurposeCounter = 0, numberOfRooms = transylvania.getNumberOfRooms(); generalPurposeCounter < numberOfRooms; generalPurposeCounter++) {
			Ivan.getManagedHotel().listOfRooms.get(generalPurposeCounter).removeAllBookings();
		}

	}

	/**
	 * Helper method used to parse a string to a local date.
	 *
	 * @param date String type variable which represents the date that is to be parsed.
	 * @return LocalDate.parse(date);
	 * Returns the date in local date format.
	 */
	static LocalDate configureDate(String date) {
		return LocalDate.parse(date);
	}

}
