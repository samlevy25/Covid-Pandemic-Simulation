/**
 *  @creator : Jacob Elbaz , ID : 336068895
 *  @creator : Samuel Elie Levy  , ID : 345112148
 */
package Population;

import Country.Settlement;
import Location.Point;
import Virus.IVirus;

public class Sick extends Person {

    private final long contagiousTime;
    private final IVirus virus;

    /**
     * Constructor
     * @param a : Sick's age
     * @param l : Sick's Location
     * @param s : Sick's settlement
     * @param v : Sick's virus
     * @param t : Total time when the person became sick
     */
    public Sick(int a, Point l, Settlement s, IVirus v, long t) {
        super(a, l, s);
        this.virus = v;
        contagiousTime = t;
    }

    /**
     * Making a sick person convalescent.
     * @return Convalescent person
     */
    public Convalescent recover() {
        return new Convalescent(getAge(), getLocation(), getSettlement(), virus);
    }

    /**
     * @return Total time when the person became sick
     */
    public long getContagiousTime() {
        return contagiousTime;
    }

    /**
     * @return
     */
    public boolean tryToDie() {
        return virus.tryToKill(this);
    }

    /**
     * @return Probability to be sick
     */
    @Override
    public double contagionProbability() {
        return 0;
    }

    @Override
    public String toString() {
        return "Sick{" +
                "contagiousTime=" + contagiousTime +
                ", virus=" + virus +
                 super.toString();
    }

    /**
     * @return Sick's Virus
     */
    public IVirus getVirus() {
        return virus;
    }
}
