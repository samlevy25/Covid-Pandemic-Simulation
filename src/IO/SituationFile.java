package IO;

import Country.Settlement;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SituationFile {
    private static SituationFile instance = null;
    private static String nameOfFile;
    private static Logger logger;
    private static FileHandler fileHandler;
    private SituationFile(String name) {
        try {
            File file = new File(name);
            if (!file.exists())
                file.createNewFile();
            nameOfFile = name;
            fileHandler = new FileHandler(nameOfFile, true);
            logger = Logger.getLogger(SituationFile.class.getSimpleName());
            logger.setUseParentHandlers(false);
            logger.addHandler(fileHandler);
            fileHandler.setFormatter(new SimpleFormatter());
        }catch (IOException e){System.out.println("Log file error.");}
    }

    public static MementoLog save(){
        return new MementoLog(nameOfFile);
    }


    public static void restore(MementoLog mementoLog) {
        if(instance != null)
            initialize(mementoLog.getLastPath());
    }


    public static void writeToLog(Settlement settlement){
        if(instance != null){
            logger.warning("\nName: " + settlement.getName() + "\nNumber of sicks: " + settlement.numOfSicks() + "\nNumber of dead: " + settlement.getDead() + "\n");
        }
    }

    public static SituationFile getInstance() {
        return instance;
    }


    public static void initialize(String name) {
        try {
            if (instance == null)
                instance = new SituationFile(name);
            else {
                logger.removeHandler(fileHandler);
                nameOfFile = name;
                fileHandler.close();
                fileHandler = new FileHandler(nameOfFile, true);
                fileHandler.setFormatter(new SimpleFormatter());
                logger.addHandler(fileHandler);
            }
        }catch (IOException e){System.out.println("Error occurred.");}
    }
}
