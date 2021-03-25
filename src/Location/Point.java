package Location;

public class Point {
    public Point(int x, int y){
        m_x = x;
        m_y = y;
    }
    public int getM_x(){
        return m_x;
    }
    public int getM_y(){
        return m_y;
    }
    private final int m_x;
    private final int m_y;

    @Override
    public String toString() {
        return "Point(" + m_x +
                ", " + m_y +
                ')';
    }
    public boolean equals(Point other_p){
        return this.getM_x() == other_p.getM_x() && this.getM_y() == other_p.getM_y();
    }
}

