import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class CarPark {
	String color;
	String regNo;

	CarPark(String color, String regNo) {
		this.color = color;
		this.regNo = regNo;
	}
}

public class MainTest {
	public static void main(String args[]) throws IOException {
		FileInputStream in = null;
		CarParkService cpsrvc = new CarParkServiceImpl();
		String filePath = args != null && args.length > 0 ? args[0] : "";
		if (filePath.length() > 0) {
			BufferedReader br = new BufferedReader(new FileReader(args[0]));
			String command = br.readLine();
			int slotCount = Integer.parseInt(command.substring(command
					.lastIndexOf("t") + 2));
			List<CarPark> slotList = cpsrvc.createSlots(slotCount);
			while ((command = br.readLine()) != null) {
				cpsrvc.executeCommand(slotList, command);
			}
		} else {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			String command = br.readLine();
			int slotCount = Integer.parseInt(command.substring(command
					.lastIndexOf("t") + 2));
			List<CarPark> slotList = cpsrvc.createSlots(slotCount);
			do {
				command = br.readLine();
				cpsrvc.executeCommand(slotList, command);
			} while (!command.equalsIgnoreCase("stop"));
		}
	}
}
