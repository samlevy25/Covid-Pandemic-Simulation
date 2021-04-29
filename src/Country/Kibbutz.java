package Country;

import Location.Location;
import Population.Person;
import java.util.List;

public class Kibbutz extends Settlement {
    public Kibbutz(String n, Location l, List<Person> p, int population) {
        super(n, l , p, population);
    }

    @Override
    public RamzorColor calculateRamzorGrade() {
        setMekadem(0.45+Math.pow(Math.pow(1.5,getMekadem())*(this.contagiousPercent() - 0.4), 3));
        return super.calculateRamzorGrade();
    }

    @Override
    public String toString() {
        return "Kibbutz '" + getName() + "': population:" + getPeople().size() + ", sicks: " + numOfSicks() + ", RamzorColor: " +  getRamzorColor() + "\n";
    }
}
