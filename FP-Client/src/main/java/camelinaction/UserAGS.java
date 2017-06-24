package camelinaction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UserAGS extends AbstractUser {
	
	
	private static UserAGS instance = null;
	
	public UserAGS() {	
		data = new ArrayList<>();
		d = new ArrayList<>();
		processor = new Processor();
	}
	
	public static UserAGS getInstance() {
		if (instance == null) {
			synchronized(UserAGS.class) {
				if (instance == null) {
                    instance = new UserAGS();
                }
			}
		}
		
		return instance;
	}
	
	public void process() {
		UserAGS u = UserAGS.getInstance();
		System.out.println("PROCESSING!!!!!!");
		System.out.println(u.data.size());
		u.processor.addData(u.data);
		ArrayList<Diamond> results = u.processor.analyze();
		
		if (top_d == null) {
			top_d = new ArrayList<>();
			writeToFile(results, "AGS_result");
			top_d.addAll(results);
		} else {
			if (top_d.size() != results.size()) {
				writeToFile(results, "AGS_result");
				top_d.clear();
				top_d.addAll(results);
			} else if (!compareDiamonds(results)) {
				writeToFile(results, "AGS_result");
				top_d.clear();
				top_d.addAll(results);
			} 
		}
		
		
	}
	
	@Override
	public void processMessage(String msg) {
		UserAGS u = UserAGS.getInstance();
		String[] msg_array = msg.split(",");
		for (String s : msg_array) {
			System.out.println(s);
		}
		String temp_data[] = new String[6];
		temp_data[0] = msg_array[0];
		temp_data[1] = msg_array[2];
		temp_data[2] = msg_array[3];
		temp_data[3] = msg_array[4];
		temp_data[4] = msg_array[5];
		temp_data[5] = msg_array[6];
		u.data.add(temp_data);
		
		
		
	}
	
	
}
