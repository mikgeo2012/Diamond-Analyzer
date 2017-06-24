package camelinaction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UserGIA extends AbstractUser {
	
	
	private static UserGIA instance = null;
	
	public UserGIA() {	
		data = new ArrayList<>();
		d = new ArrayList<>();
		
		processor = new Processor();
	}
	
	public static UserGIA getInstance() {
		if (instance == null) {
			synchronized(UserGIA.class) {
				if (instance == null) {
                    instance = new UserGIA();
                }
			}
		}
		
		return instance;
	}
	
	public void process() {
		UserGIA u = UserGIA.getInstance();
		System.out.println("PROCESSING");
		System.out.println(u.data.size());
		u.processor.addData(u.data);
		ArrayList<Diamond> results = u.processor.analyze();
		
		if (top_d == null) {
			top_d = new ArrayList<>();
			writeToFile(results, "GIA_result");
			top_d.addAll(results);
		} else {
			if (top_d.size() != results.size()) {
				writeToFile(results, "GIA_result");
				top_d.clear();
				top_d.addAll(results);
			} else if (!compareDiamonds(results)) {
				writeToFile(results, "GIA_result");
				top_d.clear();
				top_d.addAll(results);
			} 
			
			
		}
		
		
		
		
		
	}
	
	@Override
	public void processMessage(String msg) {
		UserGIA u = UserGIA.getInstance();
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
