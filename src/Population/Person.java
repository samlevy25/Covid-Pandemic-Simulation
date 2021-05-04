/**
 *  @creator : Jacob Elbaz , ID : 336068895
 *  @creator : Samuel Elie Levy  , ID : 345112148
 */
package Population;

import Country.Settlement;
import Location.Point;
import Simulation.Clock;
import Virus.IVirus;

public abstract class Person {

    private final int age; // Person's age
    private final Point location; // Person's Location
    private final Settlement settlement; // Person's Settlement

    /**
     * Constructor
     * @param a : Person's age
     * @param p : Person's Location
     * @param s : Person's Settlement
     */
    public Person(int a, Point p, Settlement s) {
        this.age = a;
        location = p;
        settlement = s;
    }

    /**
     * @return Person's age
     */
    public int getAge() {
        return this.age;
    }

    /**
     * @return Person's Location
     */
    public Point getLocation() {
        return this.location;
    }

    /**
     * @return Person's Settlement
     */
    public Settlement getSettlement() {
        return this.settlement;
    }

    /**
     * Abstract function which calculates the probability of contagion according to the type of settlement of the person (This)
     * @return Contagion Probability (Double)
     */
    public abstract double contagionProbability();

    /**
     * Contaminates a healthy person
     * @param virus Contamination virus.
     * @return Contaminated person .
     */
    public Person contagion(IVirus virus)
    {
        try{
            if(!(this instanceof Sick)){
                return new Sick(this.age, this.location, this.settlement, virus, Clock.now());
            }
            else
                throw new Exception("This person is already sick.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public String toString() {
        return  "age=" + age +
                ", location=" + location +
                ", settlement=" + settlement +
                '}';
    }
}
