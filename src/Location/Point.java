/**
 *  @creator : Jacob Elbaz , ID : 336068895
 *  @creator : Samuel Elie Levy  , ID : 345112148
 */
package Location;

public class Point {

    private final int m_x;
    private final int m_y;

    /**
     * Constructor
     * @param x : position on the map  "x"
     * @param y : position on the map  "y"
     */
    public Point(int x, int y){
        m_x = x;
        m_y = y;
    }

    /**
     * @return : position "x"
     */
    public int getM_x() { return m_x; }

    /**
     * @return position "y"
     */
    public int getM_y(){ return m_y; }



    @Override
    public String toString() {
        return "(" + m_x +
                ", " + m_y +
                ')';
    }
    public boolean equals(Point other_p)
    {
        return this.getM_x() == other_p.getM_x() && this.getM_y() == other_p.getM_y();
    }
}

