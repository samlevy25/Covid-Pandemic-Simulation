package Country;

import Location.Location;
import Population.Person;

import java.util.List;

public class Moshav extends Settlement {
    private double c = 0.3+3*(Math.pow(1.2,mekadem)*(this.contagiousPercent() - 0.35));
    public Moshav(String n, Location l, List<Person> p) {
        super(n, l, p);
        mekadem = 0.3+3*(Math.pow(1.2,mekadem)*Math.pow((this.contagiousPercent() - 0.35), 5));
    }
}
