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
    Person(int a, Point p, Settlement s) {
        this.age = a;
        location = p;
        settlement = s;
    }
    public double contagionProbability() {
        return 1;
    }
    public Person contagion(IVirus virus) {
        return new Sick(this.age, this.location, this.settlement, virus, Clock.now());
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", location=" + location +
                ", settlement=" + settlement +
                '}';
    }
}
