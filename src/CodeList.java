import java.util.ArrayList;

public class CodeList {
    protected ArrayList<String> code = new ArrayList<>();
    protected int lineToRead;

    public void addCode(String lineOfCode) {
        code.add(lineOfCode);
    }

    public int getLineNumber() {
        return lineToRead;
    }

    public void jumpTo(int lineNumber) {
        lineToRead = lineNumber;
    }

    public String readLine() {
        lineToRead += 1;
        return code.get(lineToRead-1);
    }
}
