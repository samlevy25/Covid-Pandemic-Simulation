/**
 *  @creator : Jacob Elbaz , ID : 336068895
 *  @creator : Samuel Elie Levy  , ID : 345112148
 */
package Country;

import java.awt.*;

import static java.awt.Color.*;

/**
 * enum of colors
 */
public enum RamzorColor {
    Green(GREEN, 1),
    Yellow(YELLOW, 0.8),
    Orange(ORANGE, 0.6),
    Red(RED, 0.4);

    public final Color color;
    public final double percent;

    /**
     * Constructor
     * @param c : color
     * @param p : Percent color
     */
    RamzorColor(Color c , double p)
    {
        this.color = c;
        this.percent = p;
    }
}
