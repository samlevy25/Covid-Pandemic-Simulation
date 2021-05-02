package Population;

import Country.Settlement;
import Location.Point;
import Simulation.Clock;
import Virus.IVirus;

public class Sick extends Person {
    private final long contagiousTime;
    private final IVirus virus;
    public Person recover() {
        return new Vaccinated(getAge(), getLocation(), getSettlement(), Clock.now());
    }

    public long getContagiousTime() {
        return contagiousTime;
    }

    public boolean tryToDie() {
        return virus.tryToKill(this);
    }
    public Sick(int a, Point l, Settlement s, IVirus v, long t) {
      super(a, l, s);
      this.virus = v;
      contagiousTime = t;
    }

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
}
