package IO;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MementoLog {
    private Stack<String> stack = new Stack<String>();
    public MementoLog(String nameOfFile) {
        this.stack.push(nameOfFile);
    }

    public String getLastPath(){
        return this.stack.pop();
    }
}
