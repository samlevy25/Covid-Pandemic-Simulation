/**
 *  @creator : Jacob Elbaz , ID : 336068895
 *  @creator : Samuel Elie Levy  , ID : 345112148
 */
package IO;

import Country.*;
import Country.Map;
import Location.*;
import Population.Person;

import java.io.*;
import java.util.*;

public class SimulationFile {

    private final String nameOfFile; // file's name
    private Map map;
    private SettlementFactory settlementFactory = new SettlementFactory();
    /**
     * Constructor
     * @param n :  file"s name
     */
    public SimulationFile(String n) {
        nameOfFile = n;
    }

    /**
     * read from file all map's informations , initializes the whole healthy population and gives them an age according to a mathematical formula (x and y)
     * @return List of Settlements
     * @throws IOException : if the file failed to be opened
     */
    public Map readFromFile() throws IOException {
        List<Settlement> settlementsList = new ArrayList<>();
        try {

            FileReader myFile = new FileReader(nameOfFile);
            BufferedReader myReader = new BufferedReader(myFile);
            String data;
            while ((data = myReader.readLine()) != null) {
                String[] arrOfData = data.replace(" ", "").split(";", 0);
                if (!arrOfData[0].equals("#")) {
                    Point p = new Point(Integer.parseInt(arrOfData[2]), Integer.parseInt(arrOfData[3]));
                    Size s = new Size(Integer.parseInt(arrOfData[4]), Integer.parseInt(arrOfData[5]));
                    Location l = new Location(p, s);
                    List<Person> personList = new ArrayList<>();
                    Settlement settlement = settlementFactory.create(arrOfData, l, personList); // call to Factory
                    settlementsList.add(settlement);
                }
                else {
                    int index1 = 0, index2 = 0;
                    for (int i = 0; i < settlementsList.size(); i++) {
                        if (settlementsList.get(i).getName().equals(arrOfData[1]))
                            index1 = i;
                        if (settlementsList.get(i).getName().equals(arrOfData[2]))
                            index2 = i;
                    }
                    settlementsList.get(index1).addNeighbours(settlementsList.get(index2));
                    settlementsList.get(index2).addNeighbours(settlementsList.get(index1));
                }
            }
            myReader.close();
            myFile.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(-1);
        }
        map = new Map(settlementsList.toArray(new Settlement[0]));
        return map;
    }

    @Override
    public String toString() {
        return "SimulationFile{" + nameOfFile + '}';
    }
}
