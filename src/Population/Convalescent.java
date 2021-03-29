package Population;

import Country.Settlement;
import Location.Point;
import Virus.IVirus;

public class Convalescent extends Person {
    private IVirus virus;
    public Convalescent(int a, Point p, Settlement s, IVirus v) {
        super(a, p, s);
        virus = v;
    }

    @Override
    public double contagionProbability() {
        return 0.2;
    }

    @Override
    public String toString() {
        return "Convalescent{" +
                "virus=" + virus +
                '}' + super.toString();
    }
}
