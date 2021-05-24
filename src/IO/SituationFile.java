package IO;

import Country.Settlement;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SituationFile {
    private static SituationFile instance;
    private String nameOfFile;
    private static Logger logger;
    private FileHandler fileHandler;
    private SituationFile(String name) throws IOException {
        File file = new File(name);
        if(!file.exists())
            file.createNewFile();
        nameOfFile = name;
        fileHandler = new FileHandler(nameOfFile, true);
        logger = Logger.getLogger(SituationFile.class.getSimpleName());
        logger.setUseParentHandlers(false);
        logger.addHandler(fileHandler);
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        fileHandler.setFormatter(simpleFormatter);
    }


    public static void writeToLog(Settlement settlement){
        if(instance != null){
            logger.warning("\nName: " + settlement.getName() + "\nNumber of sicks: " + settlement.numOfSicks() + "\nNumber of dead: " + settlement.getDead() + "\n");
        }
    }

    public static SituationFile getInstance() {
        return instance;
    }


    public static void initialize(String name) throws IOException {
        if(instance == null)
            instance = new SituationFile(name);
    }
}
