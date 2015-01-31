package domain;

import java.util.ArrayList;
import java.util.List;

public class Cluster {

    Long id;
    List<Point> points;

    public Cluster(Long id) {
        this.id = id;
        this.points = new ArrayList<Point>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

	@Override
	public String toString() {
		return "Cluster [id=" + id + ", points=" + points + "]";
	}
    

}