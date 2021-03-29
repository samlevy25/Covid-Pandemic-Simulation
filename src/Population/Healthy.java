package Population;

import Country.Settlement;
import Location.Point;
import Simulation.Clock;

public class Healthy extends Person {
    public Healthy(int a, Point p, Settlement s) {
        super(a, p, s);
    }
    public Person vaccinate() {
        return new Vaccinated(this.getAge(), this.getLocation(), this.getSettlement(), Clock.now());
    }
}
