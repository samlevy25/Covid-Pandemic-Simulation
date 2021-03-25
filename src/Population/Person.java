package Population;

import Country.Settlement;
import Location.Point;
import Virus.IVirus;

public abstract class Person {
    private int age;
    private Point location;
    private Settlement settlement;
    Person(int a, Point p, Settlement s) {
        this.age = a;
        location = p;
        settlement = s;
    }
    private double contagionProbability() {
        return 1;
    }
    public Person contagion(IVirus virus) {
        return new Sick(this.age, this.location, this.settlement, virus);
    }
}
