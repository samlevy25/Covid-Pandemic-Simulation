package UI;

import Country.Settlement;

import java.awt.*;

public class LineDecorator {
    private Color colorA;
    private Color colorB;
    public LineDecorator(Settlement a, Settlement b){
        colorA = a.getRamzorColor().color;
        colorB = b.getRamzorColor().color;
    }
    public void draw(Graphics g){

    }
}
