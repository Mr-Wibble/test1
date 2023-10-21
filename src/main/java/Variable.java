import exceptions.SyntaxError;

public class Variable {

    private int value = 0;

    public void clear() {
        value = 0;
    }

    public void incr() {
        value ++;
    }

    public void decr() throws SyntaxError {
        if (value <= 0) {
            throw new SyntaxError("Error (4): Negative integer creation attempted. All variables must be non-negative integers.");
        }

        value--;
    }

    public int value() {
        return value;
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
