package Monkey;

import java.util.HashMap;

public class commandMap {

	public static HashMap<String, String> getCommandMap() {
		HashMap<String, String> map = new HashMap<>();
		map.put("reboot", "reboot");
		map.put("Focued Activity", "shell dumpsys activity | findstr \"mF\"");
		return map;
		
	}
}
