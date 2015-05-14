import java.util.ArrayList;
import java.util.List;

class CarParkServiceImpl implements CarParkService {

	public List<CarPark> createSlots(int slotCount) {
		List<CarPark> slotList = new ArrayList<CarPark>(slotCount);
		for (int i = 0; i < slotCount; i++) {
			slotList.add(i, null);
		}
		System.out.println("created a parking lot of " + slotCount + " slots");
		return slotList;
	}

	public boolean park(List<CarPark> carParkSlot, String parkCommand) {
		String park[] = parkCommand != null ? parkCommand.split(" ") : null;
		CarPark parkObj = null;
		if (park != null && park.length > 2) {
			parkObj = new CarPark(park[2], park[1]);
		}
		if (carParkSlot != null && carParkSlot.indexOf(null) != -1) {
			carParkSlot.set(carParkSlot.indexOf(null), parkObj);
			System.out.println("Allocated slot number: "
					+ (carParkSlot.indexOf(parkObj) + 1));
		} else {
			System.out.println("Sorry parkinglot is full");
		}
		return true;
	}

	public boolean leaveSlot(List<CarPark> carParkSlot, String slotStling) {
		if (slotStling != null && !slotStling.isEmpty()) {
			int slotNo = Integer.parseInt(slotStling);
			if (slotNo > 0) {
				carParkSlot.set((slotNo - 1), null);
			}
			System.out.println(" Slot number: " + slotNo + "is free");
			return true;
		}
		return false;
	}

	public void status(List<CarPark> carParkSlot) {
		if (carParkSlot != null && carParkSlot.size() > 0) {
			System.out.println("Slot No.	Registration No	   Color");
			for (CarPark parkObj : carParkSlot) {
				if (parkObj != null) {
					System.out.println((carParkSlot.indexOf(parkObj) + 1) + "	"
							+ parkObj.regNo + "	" + parkObj.color);
				}
			}
		}

	}

	public void printRegOrSlotNoOfColor(List<CarPark> carParkSlot,
			String color, boolean isSlot) {
		if (carParkSlot != null && carParkSlot.size() > 0) {
			StringBuilder regOrSlotString = new StringBuilder();
			for (CarPark parkObj : carParkSlot) {
				if (parkObj != null && parkObj.color.equalsIgnoreCase(color)) {
					if (isSlot) {
						regOrSlotString.append((carParkSlot.indexOf(parkObj) + 1)+",");
					} else {
						regOrSlotString.append(parkObj.regNo+",");
					}
				}
			}
			if(regOrSlotString.length()>0){
			System.out.println(regOrSlotString.deleteCharAt(regOrSlotString.length()-1));
			}
		}

	}

	public void printSlotNoByRegistrationNo(List<CarPark> carParkSlot,
			String regNo) {
		boolean isfound = false;
		if (carParkSlot != null && carParkSlot.size() > 0) {
			for (CarPark parkObj : carParkSlot) {
				if (parkObj != null && parkObj.regNo.equalsIgnoreCase(regNo)) {
					int slot = carParkSlot.indexOf(parkObj);
					if (slot != -1)
						System.out.println((slot + 1));
					isfound = true;
					break;
				}
			}
			if (!isfound) {
				System.out.println("Not Found");
			}
		} else {
			System.out.println("Not Found");
		}

	}

	public void executeCommand(List<CarPark> slotList, String command) {
		String commandArray[] = command != null ? command.split(" ") : null;
		String subCommand = commandArray != null && commandArray.length > 0 ? commandArray[0]
				: "";
		if (subCommand != null) {
			if (subCommand.equalsIgnoreCase(CarParkConstants.PARK)) {
				park(slotList, command);
			} else if (subCommand.equalsIgnoreCase(CarParkConstants.LEAVE)
					&& commandArray.length > 1) {
				leaveSlot(slotList, commandArray[1]);
			} else if (subCommand.equalsIgnoreCase(CarParkConstants.STATUS)) {
				status(slotList);
			} else if (subCommand
					.equalsIgnoreCase(CarParkConstants.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR)
					&& commandArray.length > 1) {
				printRegOrSlotNoOfColor(slotList, commandArray[1], false);
			} else if (subCommand
					.equalsIgnoreCase(CarParkConstants.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR)
					&& commandArray.length > 1) {
				printRegOrSlotNoOfColor(slotList, commandArray[1], true);
			} else if (subCommand
					.equalsIgnoreCase(CarParkConstants.SLOT_NUMBERS_FOR_REGISTRATION_NUMBER)
					&& commandArray.length > 1) {
				printSlotNoByRegistrationNo(slotList, commandArray[1]);
			}
		}
	}

}