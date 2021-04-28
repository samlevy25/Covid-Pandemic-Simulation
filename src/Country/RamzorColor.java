package Country;

import java.awt.*;

import static java.awt.Color.*;

public enum RamzorColor {
    Green(GREEN, 100),
    Yellow(YELLOW, 80),
    Orange(ORANGE, 60),
    Red(RED, 40);

    public final Color color;
    public final double percent;


    RamzorColor(Color c , double p)
    {
        this.color = c;
        this.percent = p;
    }
}
