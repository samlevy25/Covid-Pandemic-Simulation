package Population;

import Country.Settlement;
import Location.Point;
import Virus.IVirus;

public class Sick extends Person {
    private long contagiousTime;
    private IVirus virus;
    Sick(int a, Point l, Settlement s, IVirus v) {
      super(a, l, s);
      this.virus = v;
    }
}
