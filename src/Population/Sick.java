package Population;

import Country.Settlement;
import Location.Point;
import Simulation.Clock;
import Virus.IVirus;

import java.util.Random;

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
    Sick(int a, Point l, Settlement s, IVirus v, long t) {
      super(a, l, s);
      this.virus = v;
      contagiousTime = t;
    }

    @Override
    public String toString() {
        return "Sick{" +
                "contagiousTime=" + contagiousTime +
                ", virus=" + virus +
                '}' + super.toString();
    }
}
