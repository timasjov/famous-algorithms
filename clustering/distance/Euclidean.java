package distance;

import domain.Point;

public class Euclidean {

    public double calculateDistance(Point p, Point q) {
        return Math.sqrt(Math.pow(q.getX() - p.getX(), 2) + Math.pow(q.getY() - p.getY(), 2));
    }

}