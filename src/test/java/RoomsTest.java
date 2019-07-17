package java;

import hotelserviceapp.sources.Rooms;
import hotelserviceapp.sources.Booking;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RoomsTest {


	@Test
	void initNumberOfRooms() {
		//given
		Rooms testRoom ;
		//when
		testRoom= new Rooms();
		//then
		assertTrue(testRoom.getRoomNumber() == 1);
	}

	@Test
	void roomMaintenance() {
		//given
		LocalDate testMaintenanceDate = LocalDate.parse("2001-01-01");
		Rooms testRoom = new Rooms();
		//when
		testRoom.maintainRoom(testMaintenanceDate);
		//then
		assertFalse(testRoom.getMaintenanceDates().isEmpty());
	}

	@Test
	void createBooking() {
		//given
		int testNumberOfDays = 365;
		Rooms testRoomToBeBooked = new Rooms();
		LocalDate testStartDate = LocalDate.parse("2001-01-01");
		LocalDate testEndDate = LocalDate.parse("2002-02-02");
		String guestEng = "1111111111";
		//when
		testRoomToBeBooked.createBooking(guestEng, testStartDate, testEndDate, testRoomToBeBooked, testNumberOfDays);
		//then
		assertFalse(testRoomToBeBooked.getBookings().isEmpty());
	}

	@Test
	void createBooking1() {
		//given
		Booking testBooking = new Booking();
		Rooms testBookRoom = new Rooms();
		LocalDate testStartDate = LocalDate.parse("2000-02-02");
		LocalDate testEndDate = LocalDate.parse("2000-02-02");
		//when
		testBooking.updateRoom("1111111111", testStartDate, testEndDate, testBookRoom, 1);
		testBookRoom.createBooking(testBooking);
		//then
		assertFalse(testBookRoom.getBookings().isEmpty());
	}

	@Test
	void removeBooking() {
		//given
		int testNumberOfDays = 365;
		Rooms testRoomToBeUnbooked = new Rooms();
		LocalDate testStartDate = LocalDate.parse("2001-01-01");
		LocalDate testEndDate = LocalDate.parse("2002-02-02");
		String guestEng = "11111111111";
		//when
		testRoomToBeUnbooked.createBooking(guestEng, testStartDate, testEndDate, testRoomToBeUnbooked, testNumberOfDays);
		testRoomToBeUnbooked.removeBooking(guestEng, testStartDate, testEndDate);
		//then
		assertTrue(testRoomToBeUnbooked.getBookings().isEmpty());
	}

	@Test
	void removeAllBookings() {
		//given
		Booking testBooking = new Booking();
		Rooms testBookRoom = new Rooms();
		LocalDate testStartDate = LocalDate.parse("2000-02-02");
		LocalDate testEndDate = LocalDate.parse("2000-02-02");
		//when
		testBooking.updateRoom("1111111111", testStartDate, testEndDate, testBookRoom, 1);
		testBookRoom.createBooking(testBooking);
		testBookRoom.removeAllBookings();
		//then
		assertTrue(testBookRoom.getBookings().isEmpty());
	}

	@Test
	void setCommodities() {
		//given
		int numberOfBeds = 1;
		int numberOfToilets = 3;
		int numberOfShowers = 3;
		Rooms testRoom = new Rooms();
		//when
		testRoom.setCommodities(numberOfBeds, numberOfShowers, numberOfToilets);
		//then
		assertFalse(testRoom.getCommodities().isEmpty());
		assertTrue(testRoom.getNumberOfBeds() == 1);
		assertTrue(testRoom.getNumberOfShowers() == 3);
		assertTrue(testRoom.getNumberOfToilets() == 3);
	}
}
