import java.util.List;

public interface CarParkService {

	public List<CarPark> createSlots(int slots);
	
	boolean park(List<CarPark> carParkSlot,String parkCommand);

	boolean leaveSlot(List<CarPark> carParkSlot, String slotString);

	void status(List<CarPark> carParkSlot);

	void printRegOrSlotNoOfColor(List<CarPark> carParkSlot, String color,
			boolean isSlot);

	void printSlotNoByRegistrationNo(List<CarPark> carParkSlot, String regNo);
	
	void executeCommand(List<CarPark> carParkSlot,String command);

}
