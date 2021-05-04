/**
 *  @creator : Jacob Elbaz , ID : 336068895
 *  @creator : Samuel Elie Levy  , ID : 345112148
 */
package Population;

import Country.Settlement;
import Location.Point;
import Simulation.Clock;

public class Vaccinated extends Person {
    private final long vaccinationTime;
    /**
     * Constructor
     * @param a : Sick's age
     * @param p : Sick's Location
     * @param s : Sick's settlement
     * @param t : Total time when the person became vaccinated
     */
    public Vaccinated(int a, Point p, Settlement s, long t) {
        super(a, p, s);
        vaccinationTime = t;
    }

    /**
     * @return Total time when the person became vaccinated
     */
    public long getVaccinationTime() {
        return vaccinationTime;
    }

    @Override
    public String toString() {
        return "Vaccinated{" +
                "vaccinationTime=" + vaccinationTime +
                 super.toString();
    }

    /**
     * Calculation of the Probability to be Convalescent
     * @return Probability to be Convalescent
     */
    @Override
    public double contagionProbability() {
        long t = Clock.now() - vaccinationTime;
        if (t < 21)
            return Math.min(1, 0.56 + 0.15*Math.sqrt(21-t));
        else
            return Math.max(0.05, 1.05/(t- 14));
    }
}
