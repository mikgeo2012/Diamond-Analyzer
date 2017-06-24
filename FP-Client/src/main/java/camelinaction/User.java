package camelinaction;

import java.awt.geom.Point2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.apache.camel.builder.SimpleBuilder;

public class User {	
	private ArrayList<String[]> data;
	private ArrayList<Diamond> d;
	private Processor processor;
	
	private static User instance = null;
	
	public User() {	
		data = new ArrayList<>();
		d = new ArrayList<>();
		processor = new Processor();
	}
	
	public static User getInstance() {
		if (instance == null) {
			synchronized(User.class) {
				if (instance == null) {
                    instance = new User();
                }
			}
		}
		
		return instance;
	}
	
	public void process() {
		User u = User.getInstance();
		System.out.println("PROCESSING!!!!!!");
		System.out.println(u.data.size());
		u.processor.addData(u.data);
		ArrayList<Diamond> results = u.processor.analyze();
		
		writeToFile(results, "result.csv");
		/*for (Point2D.Double r : results) {
			System.out.println(Double.toString(r.getX()) + "," + Double.toString(r.getY()));
		}*/
		
		
	}
	
	
	public void processMessage(String msg) {
		User u = User.getInstance();
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
	
	public void writeToFile(ArrayList<Diamond> ds, String filename) {
		BufferedWriter bw = null;
		
		try {
	    	  File file = new File(filename);

	    	  if (!file.exists()) {
	    		  file.createNewFile();
	    	  }

	    	  FileWriter fw = new FileWriter(file);
	    	  bw = new BufferedWriter(fw);
	    	  
	    	  bw.write(String.format("%-16s | %-16s | %-16s | %-16s | %-16s | %-16s", "Price", "Carat", "Cut", "Color", "Clarity", "ID"));
	    	  bw.newLine();
	    	  for (Diamond d : ds) {
	    		  bw.write(d.toString());
	    		  bw.newLine();
	    	  }
	    	  
	          System.out.println("File written Successfully");

	      } catch (IOException ioe) {
		   ioe.printStackTrace();
	      }
		
		finally
		{ 
		   try{
		      if(bw!=null)
		    	  bw.close();
		   }catch(Exception ex){
		       System.out.println("Error in closing the BufferedWriter"+ex);
		    }
		}
	}
	
	public static void main(String args[]) throws Exception {
		
	}

}
