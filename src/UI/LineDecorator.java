package UI;

import Country.Settlement;
import Location.Point;

import java.awt.*;

public class LineDecorator {
    private Color colorA;
    private Color colorB;
    private Point p1;
    private Point p2;
    public LineDecorator(Settlement a, Settlement b, Point pA, Point pB){
        colorA = a.getRamzorColor().color;
        colorB = b.getRamzorColor().color;
        p1 = pA;
        p2 = pB;
    }
    public void drawLine(Graphics g){
        int newRed = (colorA.getRed() + colorB.getRed())/2;
        int newBlue = (colorA.getBlue() + colorB.getBlue())/2;
        int newGreen = (colorA.getGreen() + colorB.getGreen())/2;
        Color lineColor = new Color(newRed, newGreen, newBlue);
        g.setColor(lineColor);
        g.drawLine(p1.getM_x(), p1.getM_y(), p2.getM_x(), p2.getM_y());
    }
}
