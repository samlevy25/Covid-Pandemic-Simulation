package IO;

import java.io.*;

public class StatisticsFile {
    private String nameOfFile;

    public  StatisticsFile(String n) {
        nameOfFile = n;
    }

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
