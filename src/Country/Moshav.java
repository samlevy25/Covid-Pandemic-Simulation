/*
 *  @creator : Jacob Elbaz , ID : 336068895
 *  @creator : Samuel Elie Levy  , ID : 345112148
 */
package Country;

import Location.Location;
import Population.Person;
import java.util.List;

public class Moshav extends Settlement {

    /**
     * Constructor
     * @param n : Moshav's name
     * @param l : Moshav's Location
     * @param p : Moshav's list people
     * @param population number of people in the Moshav
     */
    public Moshav(String n, Location l, List<Person> p, int population) {
        super(n, l, p, population);
    }

    /**
     * calculate the ramzor color of the city by function
     * @return  ramzor color of the city
     */
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
