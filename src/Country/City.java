package Country;

import Location.Location;
import Population.Person;

import java.util.List;

public class City extends Settlement {
    public City(String n, Location l, List<Person> p) {
        super(n, l, p);
    }

    @Override
    public RamzorColor calculateRamzorGrade() {
        setMekadem(0.2*Math.pow(4, 1.25*contagiousPercent()));
        return super.calculateRamzorGrade();
    }
}
