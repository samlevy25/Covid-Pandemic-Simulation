package Country;

import Location.Location;
import Location.Point;
import Population.*;
import Simulation.Clock;
import Virus.IVirus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
    private List<Sick> sickPerson;
    private List<Person> healthyPerson;
    private int numOfDead;

    Settlement(String n, Location l, List<Person> p, int population) {
        name = n;
        location = l;
        people = p;
        mekadem = 1;
        ramzorColor = RamzorColor.Green;
        max_person = population * 1.3;
        settlementConnected = new Settlement[0];
        numberVaccineDose = 0;
        sickPerson = new ArrayList<>();
        healthyPerson = new ArrayList<>(p);
        numOfDead = 0;


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
                sickPerson.add((Sick) newPerson);

            if (newPerson instanceof Healthy)
                healthyPerson.add(newPerson);

            return true;
        }
        else{
            System.out.println("The person cannot be added/transferred in the settlement");
            return false;
        }
    }

    public void isDead(Sick miskine1)
    {
        this.people.remove(miskine1);
        this.sickPerson.remove(miskine1);
        numOfDead++;
    }

    public void isSick(Healthy miskine2, IVirus v)
    {
        healthyPerson.remove(miskine2);
        people.remove(miskine2);
        Sick newSick = new Sick(miskine2.getAge(), miskine2.getLocation(), miskine2.getSettlement() , v, Clock.now());
        sickPerson.add(newSick);
        people.add(newSick);
    }


    public boolean transferPerson(Person person, Settlement newPlace) {

        double stat = newPlace.getRamzorColor().percent * getRamzorColor().percent;
        float i = new Random().nextFloat();
        if (i < stat && newPlace.addPerson(person)) {
            people.remove(person);
            if (person instanceof Sick)
                sickPerson.remove(person);
            else
                healthyPerson.remove(person);

            return true;
        }
        else
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
        return this.numOfSicks() / (double)getPeople().size();
    }

    public int getDead()
    {
        return this.numOfDead;
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

    public void setRamzorColor() {
        this.ramzorColor = ramzorColor;
    }

    public Location getLocation(){
        return location;
    }
    public Settlement[] getNeighbours(){
        return settlementConnected;
    }
    public int getNumOfHealthy() { return healthyPerson.size(); }
    public List<Person> getHealthyPerson() { return this.healthyPerson ;}
    public void checkConvalescents() {
        for (int i = 0; i < sickPerson.size(); i++) {
            if (sickPerson.get(i).getContagiousTime()/Clock.getTicks_per_day() > 25){
                Convalescent convalescent = sickPerson.remove(i).recover();
                healthyPerson.add(convalescent);
                people = new ArrayList<Person>(healthyPerson);
                people.addAll(sickPerson);
            }
        }
    }

    public int getNumberVaccineDose() {
        return numberVaccineDose;
    }

    public void vaccinePopulation(){
        while (numberVaccineDose != 0 && healthyPerson.size() != 0){
            for (int j = 0; j < healthyPerson.size(); j++){
                if (healthyPerson.get(j) instanceof Healthy){
                    Vaccinated person = ((Healthy) healthyPerson.remove(j)).vaccinate();
                    healthyPerson.add(person);
                    numberVaccineDose--;
                }
            }
        }
    }
}

