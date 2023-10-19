import java.util.HashMap;
import java.util.Map;

public class VariableList {
    private final Map<String, Variable> varList = new HashMap<>();

    public void addVar(String name, Variable var) {
        getVarList().put(name, var);
    }

    public boolean noVarCheck(String name) {
        return !getVarList().containsKey(name);
    }

    public void clear(String name) {
        Variable var = getVarList().get(name);
        var.clear();
    }

    public void incr(String name) {
        Variable var = getVarList().get(name);
        var.incr();
    }

    public void decr(String name) {
        Variable var = getVarList().get(name);
        var.decr();
    }

    public int getVarValue(String name) {
        Variable var = getVarList().get(name);
        return var.getValue();
    }

    public void printAllVars() {
        for(String name : getVarList().keySet()) {System.out.println(name+": "+getVarValue(name));}
    }

    public Map<String, Variable> getVarList() {
        return varList;
    }
}
