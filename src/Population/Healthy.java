/**
 *  @creator : Jacob Elbaz , ID : 336068895
 *  @creator : Samuel Elie Levy  , ID : 345112148
 */
package Population;

import Country.Settlement;
import Location.Point;
import Simulation.Clock;

public class Healthy extends Person {
    /**
     * Constructor
     * @param a : Healthy's age
     * @param p : Healthy's Location
     * @param s : Healthy's Settlement
     */
    public Healthy(int a, Point p, Settlement s) {
        super(a, p, s);
    }

    /**
     * @return Probability to be Sick
     */
    @Override
    public double contagionProbability() {
        return 1;
    }

    /**
     * The person becomes vaccinated
     * @return The person vaccinated
     */
    public Vaccinated vaccinate() // the person becomes vaccinated
    {
        return new Vaccinated(this.getAge(), this.getLocation(), this.getSettlement(), Clock.now());
    }

    @Override
    public String toString() {
        return "Healthy{" + super.toString();
    }
}
