/*
 *  @creator : Jacob Elbaz , ID : 336068895
 *  @creator : Samuel Elie Levy  , ID : 345112148
 */
package IO;

import Country.Settlement;

import java.io.*;

public class StatisticsFile
{
    private final String nameOfFile; // name of the file

    /**
     * Constructor
     * @param n : name of the file
     */
    public  StatisticsFile(String n) {
        nameOfFile = n;
    }

    /**
     * write in the file cvs that save data of the map after modification( simulation, add sick , vaccinate etc.)
     * @param settlements : Array of settlements
     */
    public void writeInCvs(Settlement[] settlements) throws IOException {
        try {
            String header = "Name,Type,RamzorColor,Sick Percent,Vaccine Dose,Dead,Current Population";
            String separator = "\n";
            String delimiter = ",";
            FileWriter pw = new FileWriter(nameOfFile);
            pw.append(header);
            pw.append(separator);
            for (Settlement settlement : settlements) {
                pw.append(settlement.getName());
                pw.append(delimiter);
                pw.append(settlement.getClass().getSimpleName());
                pw.append(delimiter);
                pw.append(settlement.getRamzorColor().toString());
                pw.append(delimiter);
                pw.append(String.valueOf(settlement.getSickPercent()));
                pw.append(delimiter);
                pw.append(String.valueOf(settlement.getGivenVaccineDose()));
                pw.append(delimiter);
                pw.append(String.valueOf(settlement.getDead()));
                pw.append(delimiter);
                pw.append(String.valueOf(settlement.getPeople().size()));
                pw.append(separator);
            }
            pw.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
