package Country;

import Location.Location;
import Location.Point;
import Population.Person;
import Population.Sick;

import java.util.List;
import java.util.Random;

public class Settlement {
    private String name;
    private Location location;
    private List<Person> people;
    private RamzorColor ramzorColor;
    protected double mekadem;
    Settlement(String n, Location l, List<Person> p) {
        name = n;
        location = l;
        people = p;
        ramzorColor = calculateRamzorGrade();
        mekadem = 1;
    }
    public RamzorColor calculateRamzorGrade() {
        if ( mekadem < 0.4)
            return RamzorColor.Green;
        else if(mekadem < 0.6)
            return RamzorColor.Yellow;
        else if(mekadem < 0.8)
            return RamzorColor.Orange;
        else
            return RamzorColor.Red;
    }
    public double contagiousPercent() {
        double count = 0;
        for (Person person : people) {
            if (person instanceof Sick) {
                count++;
            }
        }
        return count/people.size();
    }
    public Location randomLocation() {
        Random rand = new Random();
        int range_x = location.getPosition().getM_x() + location.getSize().getWidth();
        int range_y = location.getPosition().getM_y() + location.getSize().getHeight();
        int x = (int)(Math.random()*range_x)+location.getPosition().getM_x();
        int y = (int)(Math.random()*range_y)+location.getPosition().getM_y();
        Point p = new Point(x, y);
        return new Location(p, location.getSize());
    }
    public boolean addPerson(Person newPerson) {
        people.add(newPerson);
        return true;
    }
    public boolean transferPerson(Person person, Settlement newPlace) {
        people.remove(person);
        newPlace.addPerson(person);
        return true;
    }

    @Override
    public String toString() {
        return "Settlement{" +
                "name='" + name + '\'' +
                ", location=" + location +
                ", people=" + people +
                ", ramzorColor=" + ramzorColor +
                ", mekadem=" + mekadem +
                '}';
    }
    public boolean equals(Settlement other) {
        return this.name.equals(other.name);
    }

}
