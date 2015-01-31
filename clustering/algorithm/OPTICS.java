package algorithm;

import distance.Euclidean;
import distance.PointDistanceComparator;
import domain.Cluster;
import domain.Point;
import domain.SteepDownArea;
import domain.SteepUpArea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class OPTICS implements ClusteringAlgorithm {

	double epsilon;
	int minPointsInCluster;
	long clusterIdCounter = 0;
	double epsProc = 0.05;
	List<Point> points;
	List<Point> ordered = new ArrayList<Point>();
	List<Cluster> clusters = new ArrayList<Cluster>();
	Euclidean euclidean = new Euclidean();
	Comparator<Point> comparator = new PointDistanceComparator();

	public OPTICS(List<Point> points, int minPointsInCluster, double epsilon) {
		this.points = points;
		this.minPointsInCluster = minPointsInCluster;
		this.epsilon = epsilon;
	}

	@Override
	public List<Cluster> cluster() {
		performOPTICS();
		extractHierarchy();

		List<Cluster> clustersToDelete = new ArrayList<Cluster>();
		for (int i = clusters.size() - 1; i > 0; i--) {
			for (int j = i - 1; j > -1; j--) {
				if (!clustersToDelete.contains(clusters.get(j))
						&& clusters.get(i).getPoints().get(0)
								.equals(clusters.get(j).getPoints().get(0))) {
					clustersToDelete.add(clusters.get(j));
				}
			}
		}
		for (Cluster c : clustersToDelete) {
			clusters.remove(c);
		}

		return clusters;
	}

	private void extractHierarchy() {

		for (Point p : ordered) {
			if (p.getReachDistance() == null) {
				p.setReachDistance(epsilon);
			}
		}

		List<SteepDownArea> SDAreas = new ArrayList<SteepDownArea>();

		int index = 0;
		double mib = 0;

		while (index < ordered.size() - 1) {

			Point p = ordered.get(index);
			Point q = ordered.get(index + 1);
			mib = Math.max(mib, p.getReachDistance());

			// Start of steep-downward area
			if (q.getReachDistance() <= p.getReachDistance() * (1 - epsProc)) {

				SDAreas = filterSteepDownAreas(SDAreas, mib);

				SteepDownArea sta = new SteepDownArea(index, 0);

				// Decend to bottom
				index = decend(q, index);
				sta.setEndIndex(index);

				SDAreas.add(sta);
				index++;
				mib = ordered.get(index).getReachDistance();
			}
			// Start of steep-upward area
			else if (p.getReachDistance() <= q.getReachDistance()
					* (1 - epsProc)) {

				SDAreas = filterSteepDownAreas(SDAreas, mib);

				SteepUpArea sua = new SteepUpArea(index);

				// Ascend to top
				index = ascend(p, index);
				sua.setEndIndex(index);

				index++;
				mib = ordered.get(index).getReachDistance();

				int[] clusterIndexes;
				for (SteepDownArea sda : SDAreas) {

					clusterIndexes = getClusterIndexes(sda, sua);

					if (isValidCluster(clusterIndexes, sda.getMib())) {

						Cluster cluster = new Cluster(clusterIdCounter++);
						for (int i = clusterIndexes[0]; i < clusterIndexes[1] + 1; i++) {
							cluster.getPoints().add(ordered.get(i));
						}
						clusters.add(cluster);
					}
				}
			} else {
				index++;
			}
		}
	}

	// Calculate proper cluster end and start points
	private int[] getClusterIndexes(SteepDownArea sda, SteepUpArea sua) {
		Point p = ordered.get(sda.getStartIndex());
		Point q = ordered.get(sua.getEndIndex() + 1);

		int[] clusterIndexes = { sda.getStartIndex(), sua.getEndIndex() };

		if (!(p.getReachDistance() / q.getReachDistance() <= epsProc || q
				.getReachDistance() / p.getReachDistance() <= epsProc)) {

			if (p.getReachDistance() * (1 - epsProc) >= q.getReachDistance()) {

				for (int i = sua.getStartIndex(); i < sua.getEndIndex() + 1; i++) {

					if (ordered.get(i).getReachDistance() > ordered.get(
							sua.getEndIndex() + 1).getReachDistance()
							&& ordered.get(sda.getStartIndex())
									.getReachDistance() < ordered.get(i)
									.getReachDistance()) {
						clusterIndexes[0] = i;
					}
				}

			} else if (q.getReachDistance() >= p.getReachDistance()
					* (1 - epsProc)) {
				for (int i = sua.getStartIndex(); i < sua.getEndIndex() + 1; i++) {
					if (ordered.get(i).getReachDistance() < ordered.get(
							sda.getEndIndex()).getReachDistance()
							&& ordered.get(sua.getEndIndex())
									.getReachDistance() > ordered.get(i)
									.getReachDistance()) {
						clusterIndexes[1] = i;
					}
				}
			}
		}
		return clusterIndexes;

	}

	private boolean isValidCluster(int[] indexes, double SDAmib) {
		if ((indexes[1] - indexes[0] + 1) >= minPointsInCluster
				&& SDAmib <= ordered.get(indexes[1] + 1).getReachDistance()
						* (1 - epsProc)) {
			return true;
		}
		return false;
	}

	// Filter out some of the SDA's and update SDA mib values
	private List<SteepDownArea> filterSteepDownAreas(
			List<SteepDownArea> sDAreas, double mib) {
		List<SteepDownArea> currentSDAreas = new ArrayList<SteepDownArea>();
		for (SteepDownArea sda : sDAreas) {
			if (ordered.get(sda.getStartIndex()).getReachDistance()
					* (1 - epsProc) >= mib) {
				if (sda.getMib() < mib) {
					sda.setMib(mib);
				}
				currentSDAreas.add(sda);
			}
		}
		return currentSDAreas;
	}

	private int ascend(Point q, int index) {
		boolean isEndOfClimb = false;
		int numOfNonSteepPts = 0;

		// make sure e-steep upward area ends with e-steep upward point
		int lastGoodIndex = index;

		while (!isEndOfClimb) {
			Point o;

			// make sure we stay within bounds when ascending
			if (index + 1 >= ordered.size()) {
				if (numOfNonSteepPts > 0) {
					index = lastGoodIndex;
				}
				break;
			} else {
				o = ordered.get(index + 1);
			}

			// according to the steep-up definition
			if (numOfNonSteepPts > minPointsInCluster) {
				index = lastGoodIndex;
				isEndOfClimb = true;
			} else if (q.getReachDistance() <= o.getReachDistance()
					* (1 - epsProc)) {
				lastGoodIndex = index++;
				q = o;
			} else if (q.getReachDistance() <= o.getReachDistance()) {
				numOfNonSteepPts++;
				q = o;
				index++;
			} else {
				isEndOfClimb = true;
				if (numOfNonSteepPts > 0) {
					index = lastGoodIndex;
				}
			}
		}
		return lastGoodIndex;
	}

	private int decend(Point q, int index) {
		boolean isEndOfDecent = false;
		int numOfNonSteepPts = 0;

		// make sure e-steep downward area ends with e-steep downward point
		int lastGoodIndex = index;

		while (!isEndOfDecent) {
			Point o;

			// make sure we stay within bounds when descending
			if (index + 1 >= ordered.size()) {
				if (numOfNonSteepPts > 0) {
					index = lastGoodIndex;
				}
				break;
			} else {
				o = ordered.get(index + 1);
			}

			// according to the steep-down definition
			if (numOfNonSteepPts > minPointsInCluster) {
				index = lastGoodIndex;
				isEndOfDecent = true;
			} else if (o.getReachDistance() <= q.getReachDistance()
					* (1 - epsProc)) {
				lastGoodIndex = index++;
				q = o;
			} else if (o.getReachDistance() <= q.getReachDistance()) {
				numOfNonSteepPts++;
				q = o;
				index++;
			} else {
				isEndOfDecent = true;
				if (numOfNonSteepPts > 0) {
					index = lastGoodIndex;
				}
			}
		}
		return lastGoodIndex;
	}

	private void performOPTICS() {
		for (Point p : points) {
			if (!ordered.contains(p)) {

				List<Point> neighbours = getNeighbours(p);
				ordered.add(p);
				p.setCoreDistance(calculateCoreDistance(p, neighbours));

				if (p.getCoreDistance() != null) {

					PriorityQueue<Point> seeds = new PriorityQueue<Point>(
							comparator);
					update(p, neighbours, seeds);

					while (seeds.size() > 0) {
						Point q = seeds.poll();
						List<Point> currentNeighbours = getNeighbours(q);
						ordered.add(q);

						q.setCoreDistance(calculateCoreDistance(q,
								currentNeighbours));
						if (q.getCoreDistance() != null)
							update(q, currentNeighbours, seeds);
					}
				}
			}
		}
	}

	private void update(Point p, List<Point> neighbours,
			PriorityQueue<Point> seeds) {
		double newReachDistance;

		for (Point o : neighbours) {
			if (!ordered.contains(o)) {
				newReachDistance = Math.max(p.getCoreDistance(),
						euclidean.calculateDistance(p, o));
				if (o.getReachDistance() == null) {
					o.setReachDistance(newReachDistance);
					seeds.add(o);
				} else {
					if (newReachDistance < o.getReachDistance()) {
						seeds.remove(o);
						o.setReachDistance(newReachDistance);
						seeds.add(o);
					}
				}
			}
		}
	}

	private Double calculateCoreDistance(Point p, List<Point> neighbours) {
		if (neighbours.size() + 1 < minPointsInCluster) {
			return null;
		} else {
			Double[] dist = new Double[neighbours.size()];

			for (int i = 0; i < neighbours.size(); i++) {
				dist[i] = euclidean.calculateDistance(p, neighbours.get(i));
			}
			Arrays.sort(dist);
			return dist[minPointsInCluster - 2];
		}
	}

	private List<Point> getNeighbours(Point p) {
		// excluding p
		double currentDist;
		List<Point> neighbours = new ArrayList<Point>();

		for (Point q : points) {
			currentDist = euclidean.calculateDistance(p, q);
			if (currentDist < epsilon && currentDist != 0) {
				neighbours.add(q);
			}
		}
		return neighbours;
	}

	@Override
	public String toString() {
		return "OPTICS Clustering Algorithm";
	}
}