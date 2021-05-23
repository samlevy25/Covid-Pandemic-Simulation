/**
 *  @creator : Jacob Elbaz , ID : 336068895
 *  @creator : Samuel Elie Levy  , ID : 345112148
 */
package Country;

import Location.Location;
import Location.Point;
import Population.*;
import Simulation.Clock;
import Simulation.Main;
import Virus.BritishVariant;
import Virus.ChineseVariant;
import Virus.IVirus;
import Virus.SouthAfricanVariant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class Settlement
{
    private final String name; // Settlement's name
    private final Location location;// Settlement's location
    private List<Person> people; // People's list of the settlement
    private RamzorColor ramzorColor; // Settlement's ramzor color
    private double mekadem; // Settlement's mekadem
    private double max_person; // Max person's capacity in the settlement.
    private int numberVaccineDose ; // Settlement's number vaccine dose
    private Settlement[] settlementConnected; // array of settlement connected between them
    private List<Sick> sickPerson; // List of sick people in the settlement
    private List<Person> healthyPerson; // List of healthy people in the settlement
    private int numOfDead; //  number of dead in the settlement
    private final IVirus[] virus = new IVirus[]{new BritishVariant(), new ChineseVariant(), new SouthAfricanVariant()}; // Array of all virus variants.

    /**
     * Constructor
     * @param n : Settlement's name
     * @param l : Settlement's Location
     * @param p : Settlement's list people
     * @param population number of people in the Settlement
     */
    Settlement(String n, Location l, List<Person> p, int population) {
        name = n; // Settlement's name
        location = l; // Settlement's location
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

    /**
     * Give the ramzor color of the settlement by mekadem.
     * @return : ramzor color
     */
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

    /**
     * Calculation of the contagious percent of the settlement between 0 and 1 (including) .
     * @return contagious percent.
     */
    public double contagiousPercent() {
        return numOfSicks()/(double) people.size();
    }

    public Point randomLocation() {
        int range_x = location.getPosition().getM_x() + location.getSize().getWidth();
        int range_y = location.getPosition().getM_y() + location.getSize().getHeight();
        int x = (int)(Math.random()*range_x)+location.getPosition().getM_x();
        int y = (int)(Math.random()*range_y)+location.getPosition().getM_y();
        return new Point(x, y);
    }

    /**
     * The function add a new person in the list's people of the settlement.
     * @param newPerson The new person to be added.
     * @return If the addition was successful return true otherwise false.
     */
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

    public IVirus[] getVirus() {
        return virus;
    }
    /**
     * Remove the dead person from the settlement list and also from the sick list and increase the death count by 1.
     * @param miskine1 : Dead person
     */
    public void isDead(Sick miskine1)
    {
        this.people.remove(miskine1);
        this.sickPerson.remove(miskine1);
        numOfDead++;
    }

    /**
     * Remove the person from the Healthy list of the settlement also from the list of everyone at the settlement.
     * The person becomes sick, they are added to the list of people who are in the settlement and also that of sicks.
     * @param miskine2 : The person who becomes sick.
     * @param v :  Person's virus
     */
    public void isSick(Person miskine2, IVirus v)
    {
        healthyPerson.remove(miskine2);
        people.remove(miskine2);
        Sick newSick = (Sick) miskine2.contagion(v);
        sickPerson.add(newSick);
        people.add(newSick);
    }

    /**
     * Transfers a person from one settlement to another settlement (This).
     * @param person : The person to be transferred.
     * @param newPlace . New settlement of the person.
     * @return Return True if the transfer was successful otherwise False.
     */
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

    /**
     * Add a settlement to the settlement connected array.
     * @param newNeighbours The settlement to be added.
     */
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

    /**
     * @return Settlement's name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Settlement's mekadem
     */
    public double getMekadem() {
        return mekadem;
    }

    /**
     * (Protected function for save the encapsulation .) Change Settlement's mekadem (This).
     * @param mekadem New mekadem
     */
    protected void setMekadem(double mekadem) {
        this.mekadem = mekadem;
    }

    /**
     * @return People's list of the settlement.
     */
    public List<Person> getPeople() {
        return people;
    }

    /**
     * @return Size of Sick's list of the settlement.
     */
    public int numOfSicks() {
        return sickPerson.size();
    }

    /**
     * @return Ramzor color of the settlement.
     */
    public RamzorColor getRamzorColor() {
        return calculateRamzorGrade();
    }

    /**
     * @return Sick percent of the settlement.
     */
    public double getSickPercent()
    {
        return this.numOfSicks() / (double)people.size();
    }

    /**
     * @return Number of dead in the Settlement.
     */
    public int getDead()
    {
        return this.numOfDead;
    }

    /**
     * @return Number of vaccine dose in the settlement.
     */
    public int getGivenVaccineDose()
    {
        return numberVaccineDose;
    }

    /**
     * Add vaccine doses to the settlement.
     * @param numberVaccineDose Number of doses of vaccine that must be added.
     */
    public void setNumberVaccineDose(int numberVaccineDose) {
        this.numberVaccineDose += numberVaccineDose;
    }

    public void setRamzorColor() {
        this.ramzorColor = ramzorColor;
    }

    /**
     * @return Settlement's Location .
     */
    public Location getLocation(){
        return location;
    }

    /**
     * @return Array of settlement connected between them
     */
    public Settlement[] getNeighbours(){
        return settlementConnected;
    }

    /**
     * @return Number of people on the Healthy list
     */
    public int getNumOfHealthy() { return healthyPerson.size(); }

    /**
     * @return Healthy list.
     */
    public List<Person> getHealthyPerson() { return this.healthyPerson ;}

    /**
     * Check in the list of sick people,
     * if each person has the conditions to become Convalescents then add in the list of Healthy
     */
    public void checkConvalescents() {
        for (int i = 0; i < sickPerson.size(); i++) {
            if ((Clock.now() - sickPerson.get(i).getContagiousTime())/Clock.getTicks_per_day() > 25){
                Convalescent convalescent = sickPerson.remove(i).recover();
                healthyPerson.add(convalescent);
                people = new ArrayList<Person>(healthyPerson);
                people.addAll(sickPerson);
            }
        }
    }
    public synchronized void simulation_1(){
        int percent = (int) Math.floor(getSickPerson().size() * 0.2);
        IVirus virus;

        for (int j = 0; j < percent; j++) {
            int k = 0;
            while (k < 3 && getHealthyPerson().size() > 0) { // Pick 3 random healthy person and try to contagion them
                int rand = new Random().nextInt(getHealthyPerson().size());
                k++;
                virus = getSickPerson().get(j).getVirus();
                if (virus.tryToContagion(getSickPerson().get(j), getHealthyPerson().get(rand))) {
                    isSick(getHealthyPerson().get(rand), virus.getRandomVariant(this));
                }
            }
        }
    }
    public synchronized void simulation_2(){
        checkConvalescents();
    }
    public synchronized void simulation_3() {
        Person p = null;
        for (int j = 0; j < getPeople().size() * 0.03; j++)
            p = getPeople().get(j);
        transferPerson(p, settlementConnected[new Random().nextInt(settlementConnected.length)]);
    }
    public synchronized void simulation_4(){
        vaccinePopulation();
    }

    public synchronized void runSimulation(){
        simulation_1();
        simulation_2();
        simulation_3();
        simulation_4();
    }

    /**
     * @return Sick List
     */
    public List<Sick> getSickPerson() {
        return sickPerson;
    }

    /**
     * Make a person Healthy with the donation of a vaccine,
     * transfer the person to the list of healthy people
     * and decrease the number of vaccine doses in the settlement by one.
     */
    public void vaccinePopulation() {
        for (int j = 0; j < healthyPerson.size(); j++) {
            if (healthyPerson.get(j) instanceof Healthy && numberVaccineDose > 0) {
                Vaccinated person = ((Healthy) healthyPerson.remove(j)).vaccinate();
                healthyPerson.add(person);
                numberVaccineDose--;
            }
        }
    }
}

