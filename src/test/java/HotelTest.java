package java;

import hotelserviceapp.sources.Rooms;
import hotelserviceapp.sources.Hotel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class HotelTest {

	Hotel testHotel;
	Rooms testRoom1;
	Rooms testRoom2;

	@BeforeEach
	public void setUp() {
		testHotel = new Hotel();
		testRoom1 = new Rooms();
		testRoom2 = new Rooms();
	}

	@Test
	void addNewRoom() {
		//given
		int idx = 0;
		//when
		testHotel.addNewRoom(idx, testRoom1);
		//then
		assertFalse(testHotel.listOfRooms.isEmpty());
	}

	@Test
	void searchForRooms() {
		//given
		testRoom1.setCommodities(3, 0, 0);
		testRoom2.setCommodities(2, 0, 0);
		//when
		testHotel.addNewRoom(0, testRoom1);
		testHotel.addNewRoom(1, testRoom2);
		//then
		assertFalse(testHotel.searchForRooms(3).isEmpty());
		assertTrue(testHotel.searchForRooms(3).size() == 1);

	}
}
