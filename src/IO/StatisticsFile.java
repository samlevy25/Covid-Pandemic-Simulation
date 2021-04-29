package IO;

import java.io.*;

public class StatisticsFile {
    private String nameOfFile;

    StatisticsFile(String n) {
        nameOfFile = n;
    }

    public void writeInCvs( String[][] saveData) throws IOException {
        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(this.nameOfFile));
            StringBuilder sb = new StringBuilder();

            for (int i = 0 ; i < saveData.length ; i++)
            {
                for (int j = 0; j < saveData[i].length ; j++)
                {
                    sb.append(saveData[i][j]);
                   if ( saveData[i][j] != saveData[i][saveData[i].length - 1])
                       sb.append(";");

                }

            }

        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
