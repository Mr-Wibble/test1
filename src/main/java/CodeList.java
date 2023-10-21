import java.util.ArrayList;
import java.util.List;

public class CodeList {
    private final ArrayList<String> code = new ArrayList<>();
    private int lineToRead;

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

    public List<String> getCode() {
        return code;
    }

    public int getLineToRead() {
        return lineToRead;
    }
}
