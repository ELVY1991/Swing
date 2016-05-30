package Monkey;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import javax.swing.DefaultComboBoxModel;
import org.eclipse.ui.internal.dialogs.AdaptableForwarder;

public class Util {

	public static DefaultComboBoxModel<String> getDeviceId(){
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
		
		try {
			Process p = Runtime.getRuntime().exec("cmd /c adb devices");
			InputStream inputStream = p.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String line;
			while ((line= bufferedReader.readLine())!= null) {
				if (line.endsWith("device")) {
					String[] list =line.split("\t");
					model.addElement(list[0]);
					System.out.println(list[0]);
				}
			}
			bufferedReader.close();
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
		
	}	 
		
	public static ArrayList<String> doCommand(String deviceId ,String command){

		ArrayList<String> list = new ArrayList<>();
		try {
			Process p = Runtime.getRuntime().exec("cmd /c adb -s "+ deviceId + " "+command);
			InputStream inputStream = p.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String line;
			while ((line= bufferedReader.readLine())!= null) {
				list.add(line);
				Frame.textArea.append(line);
			}
			bufferedReader.close();
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static DefaultComboBoxModel<String> listPackages() {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
		try {
			Process p = Runtime.getRuntime().exec("cmd /c adb shell pm list packages");
			InputStream inputStream = p.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String line;
			while ((line= bufferedReader.readLine())!= null) {
				if (line.startsWith("package")) {
					String[] strings = line.split(":");
					model.addElement(strings[1]);
					System.out.println(line);
				}
					
				}
			bufferedReader.close();
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return model;
	}
	
	public static DefaultComboBoxModel<String> cmdList() {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
		Set set = commandMap.getCommandMap().keySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			String string = (String) iterator.next();
			model.addElement(string);
		}
		return model;
		
	}

	public static void main(String[] args) {
		getDeviceId();
//		doCommand("8fe9d6f3","reboot");
		cmdList();
	}
}
