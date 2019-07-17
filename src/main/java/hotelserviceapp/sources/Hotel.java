package hotelserviceapp.sources;
import java.util.ArrayList;
import java.util.List;


public class Hotel {

	private String hotelName;
	public List<Rooms> listOfRooms;
	private int numberOfRooms = -1;

	public Hotel() {
		hotelName = "";
		listOfRooms = new ArrayList<>();
		InitNumberOfRooms();
	}

	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	/**
	 * Initializes the numberOfRooms variable.
	 */

private 	void InitNumberOfRooms() {
		if (numberOfRooms == -1)
			numberOfRooms = 0;
	}

	/**
	 * @param idx     An int type variable, which is used to point out the index,of array list of rooms , at which the room will
	 *                be added.
	 * @param NewRoom A Rooms type object which represents the room that will be added to the list of rooms.
	 */
	public void addNewRoom(int idx, Rooms NewRoom) {
		listOfRooms.add(idx, NewRoom);
		numberOfRooms++;
	}


	/**
	 * @return Returns the Hotel's name
	 */
	 String getHotelName() {
		return hotelName;
	}


	/**
	 * @param newHotelName String type variable ,used to set a new value to the hotel's name.
	 */
	public void setHotelName(String newHotelName) {
		hotelName = newHotelName;
	}


	/**
	 * Searches the list of rooms for free ones , if there are any it returns them in an arraylist format.
	 * If none are free returns null.
	 *
	 * @return
	 * Returns a list of room that have the required number of rooms.
	 */
	public List<Rooms> searchForRooms(int numberOfRequiredBeds) {
		List<Rooms> temporaryList = new ArrayList<>();
		for (int roomCounter = 0; roomCounter < listOfRooms.size(); roomCounter++) {
			if (listOfRooms.get(roomCounter).getNumberOfBeds() == numberOfRequiredBeds) {
				temporaryList.add(listOfRooms.get(roomCounter));
			}
		}
		return temporaryList;
	}
}

