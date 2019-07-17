import hotelserviceapp.sources.Manager;
import hotelserviceapp.sources.Rooms;
import hotelserviceapp.sources.Hotel;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.*;


class ManagerTest {

	Manager Ivan;
	Hotel transylvania;

	@BeforeEach
	public void initTestVariables() {
		Ivan = new Manager();
		transylvania = new Hotel();
	}

	@Test
	void bookRoom() {
		//given
		Rooms room101 = new Rooms();
		Rooms room102 = new Rooms();
		Rooms room103 = new Rooms();
		//when
		room101.setCommodities(1, 1, 1);
		room102.setCommodities(3, 1, 2);
		room103.setCommodities(2, 2, 2);

		transylvania.setHotelName("transylvania");
		Ivan.setHotel(transylvania);
		transylvania.addNewRoom(0, room101);
		transylvania.addNewRoom(1, room102);
		transylvania.addNewRoom(2, room103);

		Ivan.bookRoom("1111111111", configureDate("2019-07-20"), configureDate("2019-07-25"), 2, 5);
		Ivan.bookRoom("2222222222", configureDate("2019-07-22"), configureDate("2019-07-23"), 1, 1);
		Ivan.bookRoom("33333333333", configureDate("2019-07-19"), configureDate("2019-07-21"), 2, 2);
		//then
		assertFalse(room101.getBookings().isEmpty());
		assertTrue(room102.getBookings().isEmpty());
		assertFalse(room103.getBookings().isEmpty());


	}


	@Test
	void unBookRoom() {
		//given
		String EGN = "1111111111";
		LocalDate startDate = LocalDate.parse("2001-01-01");
		LocalDate endDate = LocalDate.parse("2002-02-02");
		int numberOfRequiredBeds = 3;
		int numberOfDays = 5;
		Manager Ivan = new Manager();
		Hotel transylvania = new Hotel();
		Rooms testRoom1 = new Rooms();
		Rooms testRoom2 = new Rooms();
		//when
		testRoom1.setCommodities(3, 1, 1);
		testRoom2.setCommodities(3, 1, 1);

		transylvania.addNewRoom(0, testRoom1);
		transylvania.addNewRoom(1, testRoom2);

		Ivan.setHotel(transylvania);
		Ivan.bookRoom(EGN, startDate, endDate, numberOfRequiredBeds, numberOfDays);
		//then
		assertTrue(Ivan.getManagedHotel().listOfRooms.get(0).removeBooking(EGN, startDate, endDate));
	}

	static LocalDate configureDate(String date) {
		return LocalDate.parse(date);
	}

}

