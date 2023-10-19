public class Variable {
    private int value = 0;

    public void clear() {
        setValue(0);
    }

    public void incr() {
        setValue(getValue() + 1);
    }

    public void decr() {
        setValue(getValue() - 1);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
