package camelinaction;

import java.awt.geom.Point2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class Analyzer {
	
	private ArrayList<Point2D.Double> points;
	private ArrayList<Line2D.Double> lines;
	
	
	public Analyzer() {
		points = new ArrayList<>();
		lines = new ArrayList<>();
	}
	
	public void addPoints(ArrayList<Point2D.Double> p) {
		points = p;
	}
	
	public ArrayList<Point2D.Double> calculate() {
		ArrayList<Point2D.Double> convexHull = ConvexHull.quickHull(points);
		Collections.sort(convexHull, new Comparator<Point2D.Double>() {
						@Override
						public int compare(Point2D.Double p1, Point2D.Double p2)
						{
							if (p1.getX() < p2.getX()) {
				                return -1;
				            }
				            else if (p1.getX() > p2.getX()) {
				                return 1;
				            }
				            else {
				                return 0;
				            }
						}
	    		});
		
		
		for (int i = 0; i < convexHull.size() - 1; i++) {
			lines.add(new Line2D.Double(convexHull.get(i), convexHull.get(i+1)));
		}
		
		Map<Point2D.Double, Double>  map = new HashMap<Point2D.Double, Double>();
		
		for (Point2D.Double d : points) {
			Double min = Double.MAX_VALUE;
			for (Line2D.Double l : lines) {
				double dist = l.ptLineDist(d);
				if (dist < min) {
					min = dist;
				}
			}
			
			map.put(d, min);
		}
		/**
		SortedSet<Point2D.Double> sorted_points = new TreeSet<double>((double) map.keySet());
		Point2D.Double sorted_array[] = (Point2D.Double[]) sorted_points.toArray();
		ArrayList<Point2D.Double> result = new ArrayList<Point2D.Double>();**/
	
		
		List<Map.Entry<Point2D.Double, Double>> list =
	            new LinkedList<Map.Entry<Point2D.Double, Double>>( map.entrySet() );
	        Collections.sort( list, new Comparator<Map.Entry<Point2D.Double, Double>>()
	        {
	            public int compare( Map.Entry<Point2D.Double, Double> o1, Map.Entry<Point2D.Double, Double> o2 )
	            {
	                return (o1.getValue()).compareTo( o2.getValue() );
	            }
	        } );

	        /*Map<Point2D.Double, Double> result = new LinkedHashMap<Point2D.Double, Double>();
	        for (Map.Entry<Point2D.Double, Double> entry : list)
	        {
	            result.put( entry.getKey(), entry.getValue() );
	        }*/
	        
	    ArrayList<Point2D.Double> response = new ArrayList<Point2D.Double>();
	    
	    int i = 0;
		for (Map.Entry<Point2D.Double, Double> r : list) {
			if (i < 10) {
				System.out.println(Double.toString(r.getKey().getX()) + "," + Double.toString(r.getKey().getY()) + "," + Double.toString(r.getValue()));
				response.add(r.getKey());
			}
			i++;
		}
		
		return response;
	}
		
}
	



