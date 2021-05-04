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
            FileWriter fw = new FileWriter(nameOfFile);
            PrintWriter pw = new PrintWriter(fw);

            for (int i = 0 ; i < saveData.length ; i++)
            {
                for (int j = 0; j < saveData[i].length ; j++)
                {
                    pw.append(saveData[i][j]);
                   if (!saveData[i][j].equals(saveData[i][saveData[i].length - 1]))
                       pw.append(";");
                }
                pw.println("");
            }
            pw.close();
            fw.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
