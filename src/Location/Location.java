package Location;

public class Location {
    private Point position;
    private Size size;

    public Point getPosition() {
        return position;
    }

    public Size getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Location{" +
                "position=" + position.toString() +
                ", size=" + size.toString() +
                '}';
    }

    public boolean equals(Location other_l){
        return this.position == other_l.getPosition() && this.size == other_l.getSize();
    }
    public Location(Point p, Size s) {
        position = p;
        size = s;
    }
}
