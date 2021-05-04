/**
 *  @creator : Jacob Elbaz , ID : 336068895
 *  @creator : Samuel Elie Levy  , ID : 345112148
 */
package Country;

import Location.Location;
import Population.Person;
import java.util.List;

public class Kibbutz extends Settlement {
    /**
     * Constructor
     * @param n : Kibbutz's name
     * @param l : Kibbutz's Location
     * @param p : Kibbutz's list people
     * @param population number of people in the Kibbutz
     */
    public Kibbutz(String n, Location l, List<Person> p, int population) {
        super(n, l , p, population);
    }

    /**
     * calculate the ramzor color of the city by function
     * @return  ramzor color of the city
     */
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
