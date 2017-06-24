package camelinaction;

import java.util.ArrayList;
import java.awt.geom.Point2D;

public class Processor {
	private ArrayList<Diamond> diamonds;
	private Analyzer analyzer;
	
	public Processor() {
		diamonds = new ArrayList<>();
		analyzer = new Analyzer();
	}
	
	public void addData(ArrayList<String[]> data) {
		diamonds.clear();
		for (String s[] : data) {
			diamonds.add(new Diamond(s[0],s[1],s[2],s[3],s[4],s[5]));
		}
	}
	
	public ArrayList<Diamond> analyze() {
		ArrayList<Point2D.Double> points = new ArrayList<>();
		for (Diamond di : diamonds) {
			points.add(di.getPoint());
		}
		
		analyzer.addPoints(points);
		ArrayList<Point2D.Double> results = analyzer.calculate();
		
		return getTopDiamond(results);
		
	}
	
	public ArrayList<Diamond> getTopDiamond(ArrayList<Point2D.Double> results) {
		ArrayList<Diamond> diamond_results = new ArrayList<>();
		for (Point2D.Double r : results) {
			for (Diamond d : diamonds) {
				if (d.getPoint().equals(r)) {
					diamond_results.add(d);
				}
			}
		}
		
		return diamond_results;
	}
}
