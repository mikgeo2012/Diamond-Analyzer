package camelinaction;

import java.util.ArrayList;
import java.util.Scanner;

public class Client {
	public static void main(String args[]) throws Exception {
		System.out.println("Diamond Price Analyzer");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Institute:");
        String inst = sc.nextLine();
        System.out.println("Enter the Color:");
        String color = sc.nextLine();
        System.out.println("Enter the Cut:");
        String cut = sc.nextLine();
        System.out.println("Enter the Clarity:");
        String clarity = sc.nextLine();
        
        Endpoint t = new Endpoint(cut, clarity, color, inst);
        while (true) {
	        t.r();
        }
        	
        
    
	}

}
