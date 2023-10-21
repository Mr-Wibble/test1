import exceptions.SyntaxError;
import org.junit.Assert;
import org.junit.Test;

public class VariableTest {

    @Test(expected = SyntaxError.class)
    public void errorDecrTest() throws SyntaxError {
        Variable v = new Variable();
        v.decr();
    }

    @Test
    public void defaultVarTest() {
        Variable v = new Variable();
        Assert.assertEquals(0, v.value());
    }

    @Test
    public void simpleIncrTest() {
        Variable v = new Variable();
        v.incr();
        Assert.assertEquals(1, v.value());
    }

    @Test
    public void simpleDecrTest() throws SyntaxError {
        Variable v = new Variable();
        v.incr();
        v.decr();
        Assert.assertEquals(0, v.value());
    }

    @Test
    public void simpleClearTest() {
        Variable v = new Variable();
        v.incr();
        v.clear();
        Assert.assertEquals(0, v.value());
    }
}