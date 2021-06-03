package IO;

public class MementoLog {
    private String name;
    public MementoLog(String nameOfFile) {
        this.name = nameOfFile;
    }

    public String getLastPath(){
        return this.name;
    }
}
