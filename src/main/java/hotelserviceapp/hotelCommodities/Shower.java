package hotelserviceapp.hotelCommodities;

import hotelserviceapp.hotelCommodities.domain.AbstractCommodity;

public class Shower extends AbstractCommodity {
	public Shower() {
		initStaticInventoryNumber();
		StaticInventoryNumber++;
		inventoryNumber = StaticInventoryNumber;
	}

	public void prepare() {
		System.out.println("The shower is being cleaned.");
	}

	@Override
	public int hashCode() {
		return this.inventoryNumber;
	}

	private void initStaticInventoryNumber() {
		if (inventoryNumber == 0) {
			inventoryNumber = 1;
		}
	}
}
