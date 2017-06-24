package camelinaction;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class ConvexHull {
	public static ArrayList<Point2D.Double> quickHull(ArrayList<Point2D.Double> Point2Ds)
    {
        ArrayList<Point2D.Double> convexHull = new ArrayList<Point2D.Double>();
        if (Point2Ds.size() < 3)
            return (ArrayList) Point2Ds.clone();
 
        int minPoint2D = -1, maxPoint2D = -1;
        Double minX = Double.MAX_VALUE;
        Double maxX = Double.MIN_VALUE;
        for (int i = 0; i < Point2Ds.size(); i++)
        {
            if (Point2Ds.get(i).getX() < minX)
            {
                minX = Point2Ds.get(i).getX();
                minPoint2D = i;
            }
            if (Point2Ds.get(i).getX() > maxX)
            {
                maxX = Point2Ds.get(i).getX();
                maxPoint2D = i;
            }
        }
        Point2D.Double A = Point2Ds.get(minPoint2D);
        Point2D.Double B = Point2Ds.get(maxPoint2D);
        convexHull.add(A);
        convexHull.add(B);
        Point2Ds.remove(A);
        Point2Ds.remove(B);
 
        ArrayList<Point2D.Double> leftSet = new ArrayList<Point2D.Double>();
        ArrayList<Point2D.Double> rightSet = new ArrayList<Point2D.Double>();
 
        for (int i = 0; i < Point2Ds.size(); i++)
        {
            Point2D.Double p = Point2Ds.get(i);
            if (Point2DLocation(A, B, p) == -1)
                leftSet.add(p);
            else if (Point2DLocation(A, B, p) == 1)
                rightSet.add(p);
        }
        hullSet(A, B, rightSet, convexHull);
        hullSet(B, A, leftSet, convexHull);
 
        return convexHull;
    }
 
    public static Double distance(Point2D.Double A, Point2D.Double B, Point2D.Double C)
    {
        Double ABx = B.getX() - A.getX();
        Double ABy = B.getY() - A.getY();
        Double num = ABx * (A.getY() - C.getY()) - ABy * (A.getX() - C.getX());
        if (num < 0)
            num = -num;
        return num;
    }
 
    public static void hullSet(Point2D.Double A, Point2D.Double B, ArrayList<Point2D.Double> set,
            ArrayList<Point2D.Double> hull)
    {
        int insertPosition = hull.indexOf(B);
        if (set.size() == 0)
            return;
        if (set.size() == 1)
        {
            Point2D.Double p = set.get(0);
            set.remove(p);
            hull.add(insertPosition, p);
            return;
        }
        Double dist = Double.MIN_VALUE;
        int furthestPoint2D = -1;
        for (int i = 0; i < set.size(); i++)
        {
            Point2D.Double p = set.get(i);
            Double distance = distance(A, B, p);
            if (distance > dist)
            {
                dist = distance;
                furthestPoint2D = i;
            }
        }
        Point2D.Double P = set.get(furthestPoint2D);
        set.remove(furthestPoint2D);
        hull.add(insertPosition, P);
 
        // Determine who's to the left of AP
        ArrayList<Point2D.Double> leftSetAP = new ArrayList<Point2D.Double>();
        for (int i = 0; i < set.size(); i++)
        {
            Point2D.Double M = set.get(i);
            if (Point2DLocation(A, P, M) == 1)
            {
                leftSetAP.add(M);
            }
        }
 
        // Determine who's to the left of PB
        ArrayList<Point2D.Double> leftSetPB = new ArrayList<Point2D.Double>();
        for (int i = 0; i < set.size(); i++)
        {
            Point2D.Double M = set.get(i);
            if (Point2DLocation(P, B, M) == 1)
            {
                leftSetPB.add(M);
            }
        }
        hullSet(A, P, leftSetAP, hull);
        hullSet(P, B, leftSetPB, hull);
 
    }
 
    public static int Point2DLocation(Point2D A, Point2D B, Point2D P)
    {
        Double cp1 = (B.getX() - A.getX()) * (P.getY() - A.getY()) - (B.getY() - A.getY()) * (P.getX() - A.getX());
        if (cp1 > 0)
            return 1;
        else if (cp1 == 0)
            return 0;
        else
            return -1;
    }

}
