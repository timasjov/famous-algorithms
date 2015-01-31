package algorithm;

import distance.Euclidean;
import domain.Cluster;
import domain.Point;
import domain.PointType;

import java.util.ArrayList;
import java.util.List;

public class DBSCAN implements ClusteringAlgorithm {

    double epsilon;
    int minPointsInCluster;
    List<Point> points;
    List<Point> visited = new ArrayList<Point>();
    List<Cluster> clusters = new ArrayList<Cluster>();
    Euclidean euclidean = new Euclidean();
    long clusterIdCounter = 0;

    public DBSCAN(List<Point> points, int minPointsInCluster, double epsilon) {
        this.points = points;
        this.minPointsInCluster = minPointsInCluster;
        this.epsilon = epsilon;
    }

    @Override
    public List<Cluster> cluster() {
        for (Point p : points) {
            if (!visited.contains(p)) {
                List<Point> neighbours = getNeighbours(p);
                if (neighbours.size() >= minPointsInCluster) {
                    p.setPointType(PointType.IN_CLUSTER);
                    visited.add(p);

                    Cluster cluster = expandCluster(p, neighbours);
                    clusters.add(cluster);
                } else {
                    p.setPointType(PointType.NOIZE);
                    visited.add(p);
                }
            }
        }
        return clusters;
    }

    private Cluster expandCluster(Point p, List<Point> neighbours) {
        Cluster cluster = new Cluster(clusterIdCounter);
        cluster.getPoints().add(p);
        clusterIdCounter++;

        int index = 0;
        while (neighbours.size() > index) {
            Point neighbour = neighbours.get(index);
            if (!visited.contains(neighbour)) {
                visited.add(neighbour);
                List<Point> currentNeighbours = getNeighbours(neighbour);
                if (currentNeighbours.size() >= minPointsInCluster) {
                    neighbours = merge(neighbours, currentNeighbours);
                }
            }
            if (!pointIsMemberOfAnyCluster(neighbour)) {
                neighbour.setPointType(PointType.IN_CLUSTER);
                cluster.getPoints().add(neighbour);
            }
            index++;
        }

        return cluster;
    }

    private List<Point> merge(List<Point> neighbours, List<Point> currentNeighbours) {
        for (Point p : currentNeighbours) {
            if (!neighbours.contains(p)) {
                neighbours.add(p);
            }
        }
        return neighbours;
    }

    private boolean pointIsMemberOfAnyCluster(Point p) {
        return p.getPointType() != null && p.getPointType().equals(PointType.IN_CLUSTER);
    }

    private List<Point> getNeighbours(Point p) {
        // including p
        List<Point> neighbours = new ArrayList<Point>();
        for (Point q : points) {
            if (euclidean.calculateDistance(p, q) < epsilon) {
                neighbours.add(q);
            }
        }
        return neighbours;
    }

    @Override
    public String toString() {
        return "DBSCAN Clustering Algorithm";
    }

}