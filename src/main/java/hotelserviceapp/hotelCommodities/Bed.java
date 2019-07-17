package hotelserviceapp.hotelCommodities;

import hotelserviceapp.hotelCommodities.domain.AbstractCommodity;

public class Bed extends AbstractCommodity {
	private int numberOfPersonas;
	enum BedTypes{
		Single,
		Double,
		KingSize,
		Couch,
	}
	BedTypes bedType ;

	 public Bed() {
		initStaticInventoryNumber();
		StaticInventoryNumber++;
		inventoryNumber = StaticInventoryNumber;
		bedType = BedTypes.Single;
		setNumberOfPersonas();
	}

	private void setBedType(BedTypes bedType){
	 	if(bedType != null){
	 		this.bedType = bedType;
		}
	}

	private void setNumberOfPersonas(){
	 	switch (bedType){
			case Single:
			case Couch: {
				numberOfPersonas = 1;
				break;
			}
			case Double:
			case KingSize: {
				numberOfPersonas = 2;
				break;
			}
		}
	}
	public void setNumberOfPersonas(int numberOfPersons) {
		this.numberOfPersonas = numberOfPersons;
	}

	public int getNumberOfPersonas() {
		return numberOfPersonas;
	}

	public void prepare() {
		System.out.println("The bed sheets are being replaced.");
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
