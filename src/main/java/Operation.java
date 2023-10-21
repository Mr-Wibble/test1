import exceptions.SyntaxError;

import java.util.ArrayList;
import java.util.List;

public enum Operation {

    CLEAR(true, 1),
    INCR(true, 1),
    DECR(true, 1),
    WHILE(true, "not 0 do;".length()),
    END(false, 1);

    private final boolean hasVariable;
    private final int endOffset;

    Operation(boolean hasVariable, int endOffset) {
        this.hasVariable = hasVariable;
        this.endOffset = endOffset;
    }

    private static final List<Operation> operations = new ArrayList<>() {{
        this.add(CLEAR);
        this.add(INCR);
        this.add(DECR);
        this.add(WHILE);
        this.add(END);
    }};

    public static Operation fromString(String val, int lineNum) throws SyntaxError {
        for (Operation op : operations) {
            if (val.strip().startsWith(op.name().toLowerCase())) {
                return op;
            }
        }

        throw new SyntaxError("Unable to determine operation from \"%s\" @ line %d.".formatted(val, lineNum));
    }

    public int getBeginIndex() {
        return this.name().length();
    }

    public int getEndOffset() {
        return this.endOffset;
    }

    public boolean hasVariable() {
        return this.hasVariable;
    }
}
