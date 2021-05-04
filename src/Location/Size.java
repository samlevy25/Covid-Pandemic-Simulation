/**
 *  @creator : Jacob Elbaz , ID : 336068895
 *  @creator : Samuel Elie Levy  , ID : 345112148
 */
package Location;

public class Size {

    private final int width;
    private final int height;

    /**
     * Constructor
     * @param w : width of the settlement in the map
     * @param h : height of the settlement in the map
     */
    public Size(int w, int h){
        width = w;
        height = h;
    }

    /**
     * @return : width of the settlement
     */
    public int getWidth(){ return width; }

    /**
     * @return : Height of the settlement
     */
    public int getHeight(){ return height; }

    @Override
    public String toString() {
        return "(" + width +
                "x" + height +
                ')';
    }
    public boolean equals(Size other_s){
        return this.width == other_s.getWidth() && this.height == other_s.getHeight();
    }
}
