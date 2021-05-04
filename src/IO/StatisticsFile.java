/**
 *  @creator : Jacob Elbaz , ID : 336068895
 *  @creator : Samuel Elie Levy  , ID : 345112148
 */
package IO;

import java.io.*;

public class StatisticsFile
{
    private String nameOfFile; // name of the file

    /**
     * Constructor
     * @param n : name of the file
     */
    public  StatisticsFile(String n) {
        nameOfFile = n;
    }

    /**
     * write in the file cvs that save data of the map after modification( simulation, add sick , vaccinate etc.)
     * @param saveData : Double array of string contains all the map after modification
     * @throws IOException
     */
    public void writeInCvs( String[][] saveData) throws IOException {
        try {
            String header = "Name,Type,RamzorColor,Sick Percent,Vaccine Dose,Dead,Current Population";
            PrintWriter pw = new PrintWriter(nameOfFile + ".csv");
            pw.write(header);

            for (String[] saveDatum : saveData) {
                StringBuilder row = new StringBuilder();
                for (String s : saveDatum) {
                    row.append(s).append(",");
                }
                row.append("\n");
                pw.write(row.toString());
            }
            pw.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
