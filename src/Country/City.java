/*
 *  @creator : Jacob Elbaz , ID : 336068895
 *  @creator : Samuel Elie Levy  , ID : 345112148
 */
package Country;

import Location.Location;
import Population.Person;
import java.util.List;

public class City extends Settlement {

    /**
     * Constructor
     * @param n : City's name
     * @param l : City's Location
     * @param p : City's list people
     * @param population number of people in the City
     */
    public City(String n, Location l, List<Person> p, int population) {
        super(n, l, p, population);
    }

    /**
     * calculate the ramzor color of the city by function
     * @return  ramzor color of the city
     */
    @Override
    public RamzorColor calculateRamzorGrade() {
        setMekadem(0.2*Math.pow(4, 1.25*contagiousPercent()));
        return super.calculateRamzorGrade();

    }

    @Override
    public String toString() {
        return "City '" + getName() + "': population:" + getPeople().size() + ", sicks: " + numOfSicks() + ", RamzorColor: " +  getRamzorColor() + "\n";
    }
}


