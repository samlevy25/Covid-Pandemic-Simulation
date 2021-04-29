package Country;

import Location.Location;
import Population.Person;
import java.util.List;

public class Moshav extends Settlement {
    public Moshav(String n, Location l, List<Person> p, int population) {
        super(n, l, p, population);
    }

    @Override
    public RamzorColor calculateRamzorGrade() {
        setMekadem(0.3+3*Math.pow(Math.pow(1.2,getMekadem())*(this.contagiousPercent() - 0.35), 5));
        return super.calculateRamzorGrade();
    }

    @Override
    public String toString() {
        return "Moshav '" + getName() + "': population:" + getPeople().size() + ", sicks: " + numOfSicks() + ", RamzorColor: " +  getRamzorColor() + "\n";
    }
}
