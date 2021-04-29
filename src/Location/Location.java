package Location;

public class Location {
    private final Point position;
    private final Size size;

    public Point getPosition() {
        return position;
    }

    public Size getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "[" +
                 position.toString() + "; " +
                 size.toString() +
                ']';
    }

    public boolean equals(Location other_l){
        return this.position == other_l.getPosition() && this.size == other_l.getSize();
    }
    public Location(Point p, Size s) {
        position = p;
        size = s;
    }
    public Point getCenter() {
        int x = size.getWidth()/2 + position.getM_x();
        int y = size.getHeight()/2 + position.getM_y();
        return new Point(x, y);
    }
}
