import java.util.ArrayList;

public class CodeList {
    private final ArrayList<String> code = new ArrayList<>();
    private int lineToRead;

    public void addCode(String lineOfCode) {
        getCode().add(lineOfCode);
    }

    public int getLineNumber() {
        return getLineToRead();
    }

    public void jumpTo(int lineNumber) {
        setLineToRead(lineNumber);
    }

    public String readLine() {
        setLineToRead(getLineToRead() + 1);
        return getCode().get(getLineToRead() -1);
    }

    public ArrayList<String> getCode() {
        return code;
    }

    public int getLineToRead() {
        return lineToRead;
    }

    public void setLineToRead(int lineToRead) {
        this.lineToRead = lineToRead;
    }
}
