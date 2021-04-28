package Country;

import Location.Location;
import Location.Point;
import Population.Healthy;
import Population.Person;
import Population.Sick;
import Population.Vaccinated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Settlement
{
    private final String name;
    private final Location location;
    private List<Person> people;
    private RamzorColor ramzorColor;
    private double mekadem;
    // new
    private double max_person;
    private int numberVaccineDose ;
    private Settlement[] settlementConnected;
    private List<Person> sickPerson;
    private List<Person> healthyPerson;
    private int numOfDeads;

    Settlement(String n, Location l, List<Person> p) {
        name = n;
        location = l;
        people = p;
        mekadem = 1;
        ramzorColor = RamzorColor.Green;
        // new
        max_person = this.getPeople().size() * 1.3;
        settlementConnected = null;
        numberVaccineDose = 0;
        sickPerson = null;
        healthyPerson = null ;
        numOfDeads = 0;


    }
    public RamzorColor calculateRamzorGrade() {
        if ( mekadem < 0.4) {
            ramzorColor = RamzorColor.Green;
            return RamzorColor.Green;
        }
        else if(mekadem < 0.6) {
            ramzorColor = RamzorColor.Yellow;
            return RamzorColor.Yellow;
        }
        else if(mekadem < 0.8) {
            ramzorColor = RamzorColor.Orange;
            return RamzorColor.Orange;
        }
        else {
            ramzorColor = RamzorColor.Red;
            return RamzorColor.Red;
        }
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
    public Point randomLocation() {
        int range_x = location.getPosition().getM_x() + location.getSize().getWidth();
        int range_y = location.getPosition().getM_y() + location.getSize().getHeight();
        int x = (int)(Math.random()*range_x)+location.getPosition().getM_x();
        int y = (int)(Math.random()*range_y)+location.getPosition().getM_y();
        return new Point(x, y);
    }
    public boolean addPerson(Person newPerson)
    {
        if (getPeople().size() < max_person )
        {
            people.add(newPerson);

            if (newPerson instanceof Sick)
                sickPerson.add(newPerson);

            if (newPerson instanceof Healthy)
                healthyPerson.add(newPerson);

            return true;
        }
        else{
            System.out.println("The person cannot be added/transferred in the settlement");
            return false;
        }
    }

    public void isDead(Sick miskine)
    {
        this.people.remove(miskine);
        this.sickPerson.remove(miskine);
        numOfDeads++;

    }


    public boolean transferPerson(Person person, Settlement newPlace) {

        if( newPlace.addPerson(person))
        {
            people.remove(person);

            if (person instanceof Sick)
                sickPerson.remove(person);

            if (person instanceof Healthy)
                healthyPerson.remove(person);

            return true;
        }
        return false;
    }

    public void addNeighbours(Settlement newNeighbours)
    {
        ArrayList<Settlement> updateArray = new ArrayList<Settlement>(Arrays.asList(this.settlementConnected));
        updateArray.add(newNeighbours);
        this.settlementConnected = updateArray.toArray(this.settlementConnected);
    }
    @Override
    public String toString() {
        return "Settlement{" +
                "name='" + name + "', " +
                 location +
                ", ramzorColor=" + ramzorColor +
                ", mekadem=" + mekadem +
                '}';
    }
    public boolean equals(Settlement other) {
        return this.name.equals(other.name);
    }

    public String getName() {
        return name;
    }

    public double getMekadem() {
        return mekadem;
    }

    protected void setMekadem(double mekadem) {
        this.mekadem = mekadem;
    }

    public List<Person> getPeople() {
        return people;
    }

    public int numOfSicks() {
        int count = 0;
        for(int i = 0; i < getPeople().size(); i++) {
            if(getPeople().get(i) instanceof Sick)
                count++;
        }
        return count;
    }

    public RamzorColor getRamzorColor() {
        return calculateRamzorGrade();
    }

    public double getSickPercent()
    {
        return (double)(this.numOfSicks() / getPeople().size());
    }

    public int getDead()
    {
        return this.numOfDeads;
    }

    public int getGivenVaccineDose()
    {
        int count = 0;
        for(int i = 0; i < getPeople().size(); i++) {
            if(getPeople().get(i) instanceof Vaccinated)
                count++;
        }
        return count;
    }
}