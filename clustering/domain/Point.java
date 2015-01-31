package domain;

public class Point {

    Long id;
    Double x;
    Double y;
    Double coreDistance;
    Double reachDistance;
    PointType pointType;

    public Point(Long id, Double x, Double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public PointType getPointType() {
        return pointType;
    }

    public void setPointType(PointType pointType) {
        this.pointType = pointType;
    }

    public Double getCoreDistance() {
        return coreDistance;
    }

    public void setCoreDistance(Double coreDistance) {
        this.coreDistance = coreDistance;
    }

    public Double getReachDistance() {
        return reachDistance;
    }

    public void setReachDistance(Double reachDistance) {
        this.reachDistance = reachDistance;
    }

    @Override
    public String toString() {
        return "Point{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", pointType=" + pointType +
                '}';
    }
}