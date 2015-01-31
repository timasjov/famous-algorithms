package distance;

import domain.Point;

import java.util.Comparator;


public class PointDistanceComparator implements Comparator<Point>{
	
   @Override
    public int compare(Point x, Point y) {
        if (x.getReachDistance() < y.getReachDistance()) {
            return -1;
        }
        if (x.getReachDistance() > y.getReachDistance()) {
            return 1;
        }
        return 0;
    }
}