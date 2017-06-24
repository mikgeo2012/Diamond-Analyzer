package camelinaction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.camel.builder.SimpleBuilder;

abstract public class AbstractUser {
	protected ArrayList<String[]> data;
	protected ArrayList<Diamond> d;
	protected ArrayList<Diamond> top_d = null;
	protected Processor processor;
	
	protected abstract void processMessage(String msg);
	protected abstract void process();
	
	public boolean compareDiamonds(ArrayList<Diamond> results) {
		for (Diamond d : results) {
			boolean temp = false;
			for (Diamond t : top_d) {
				if (t.equals(d)) {
					temp = true;
				}
			}
			
			if (temp == false) {
				return false;
			}
		}
		
		return true;
	}
	
	protected static void writeToFile(ArrayList<Diamond> ds, String filename) {
		BufferedWriter bw = null;
		
		Date date = new Date();
		
		Format formatter = new SimpleDateFormat("YYYY-MM-dd_hh-mm-ss");
		
		try {
	    	  File file = new File("data/" + filename + "_" + formatter.format(date) + ".csv");

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

}
