package Population;

import Country.Settlement;
import Location.Point;
import Simulation.Clock;
import Virus.IVirus;

public abstract class Person {
    private final int age;
    private final Point location;
    private final Settlement settlement;
    public int getAge() {
        return this.age;
    }
    public Point getLocation() {
        return this.location;
    }
    public Settlement getSettlement() {
        return this.settlement;
    }
    public Person(int a, Point p, Settlement s) {
        this.age = a;
        location = p;
        settlement = s;
    }
    public abstract double contagionProbability();

    public Person contagion(IVirus virus) {
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
