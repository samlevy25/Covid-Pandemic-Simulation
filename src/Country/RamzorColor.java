package Country;

import java.awt.*;

import static java.awt.Color.*;

public enum RamzorColor {
    Green(GREEN, 1),
    Yellow(YELLOW, 0.8),
    Orange(ORANGE, 0.6),
    Red(RED, 0.4);

    public final Color color;
    public final double percent;


    RamzorColor(Color c , double p)
    {
        this.color = c;
        this.percent = p;
    }
}
