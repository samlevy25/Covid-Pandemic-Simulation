/**
 *  @creator : Jacob Elbaz , ID : 336068895
 *  @creator : Samuel Elie Levy  , ID : 345112148
 */
package Location;

public class Location {

    private final Point position;
    private final Size size;

    /**
     * Constructor
     * @param p : settlement's position on the map (Type : Position )
     * @param s : settlement's size (Type : Size)
     */
    public Location(Point p, Size s) {
        position = p;
        size = s;
    }

    /**
     * @return settlement's position on the map
     */
    public Point getPosition() { return position; }

    /**
     * @return settlement's size
     */
    public Size getSize() { return size; }

    @Override
    public String toString() {
        return "[" +
                 position.toString() + "; " +
                 size.toString() +
                ']';
    }

    /**
     * check if both settlements are same
     * @param other_l : Another settlement to check
     * @return True if both settlements are same otherwise False
     */
    public boolean equals(Location other_l){ return this.position == other_l.getPosition() && this.size == other_l.getSize(); }

    public Point getCenter() {
        int x = size.getWidth()/2 + position.getM_x();
        int y = size.getHeight()/2 + position.getM_y();
        return new Point(x, y);
    }
}
