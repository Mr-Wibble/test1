import java.util.HashMap;
import java.util.Map;

public class VariableList {
    protected Map<String, Variable> varList = new HashMap<>();

    public void addVar(String name, Variable var) {
        varList.put(name, var);
    }

    public boolean noVarCheck(String name) {
        return !varList.containsKey(name);
    }

    public void clear(String name) {
        Variable var = varList.get(name);
        var.clear();
    }

    public void incr(String name) {
        Variable var = varList.get(name);
        var.incr();
    }

    public void decr(String name) {
        Variable var = varList.get(name);
        var.decr();
    }

    public int getVarValue(String name) {
        Variable var = varList.get(name);
        return var.value;
    }

    public void printAllVars() {
        for(String name : varList.keySet()) {System.out.println(name+": "+getVarValue(name));}
    }
}
