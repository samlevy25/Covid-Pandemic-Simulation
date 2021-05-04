/**
 *  @creator : Jacob Elbaz , ID : 336068895
 *  @creator : Samuel Elie Levy  , ID : 345112148
 */
package Population;

import Country.Settlement;
import Location.Point;
import Virus.IVirus;

public class Convalescent extends Person {

    private final IVirus virus;
    /**
     * Constructor
     * @param a : Person's age
     * @param p : Person's Location
     * @param s : Person's Settlement
     * @param v : The virus before convalescence
     */
    public Convalescent(int a, Point p, Settlement s, IVirus v) {
        super(a, p, s);
        virus = v;
    }

    /**
     * Probability to be sick
     * @return
     */
    @Override
    public double contagionProbability() {
        return 0.2;
    }

    @Override
    public String toString() {
        return "Convalescent{" +
                "virus=" + virus.toString() +
                ", " + super.toString();
    }
}
