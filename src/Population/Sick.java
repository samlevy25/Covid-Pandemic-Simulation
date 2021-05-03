package Population;

import Country.Settlement;
import Location.Point;
import Simulation.Clock;
import Virus.IVirus;

public class Sick extends Person {
    private final long contagiousTime;
    private final IVirus virus;
    public Convalescent recover() {
        return new Convalescent(getAge(), getLocation(), getSettlement(), virus);
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

    public IVirus getVirus() {
        return virus;
    }
}
