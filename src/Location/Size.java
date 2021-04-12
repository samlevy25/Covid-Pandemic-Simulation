package Location;

public class Size {
    private final int width;
    private final int height;
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public Size(int w, int h){
        width = w;
        height = h;
    }

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
