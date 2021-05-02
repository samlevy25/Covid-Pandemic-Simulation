package Population;

import Country.Settlement;
import Location.Point;
import Simulation.Clock;

public class Healthy extends Person {
    public Healthy(int a, Point p, Settlement s) {
        super(a, p, s);
    }

    @Override
    public double contagionProbability() {
        return 1;
    }

    public Vaccinated vaccinate() // the person becomes vaccinated
    {
        return new Vaccinated(this.getAge(), this.getLocation(), this.getSettlement(), Clock.now());
    }

    @Override
    public String toString() {
        return "Healthy{" + super.toString();
    }
}
