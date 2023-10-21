import exceptions.SyntaxError;

import java.util.Map;

public class OperationsAndVariable {
    private final Variable var;
    private final Operation op;

    private OperationsAndVariable(Variable var, Operation op) {
        this.var = var;
        this.op = op;
    }

    public static OperationsAndVariable getVarAndOp(Map<String, Variable> varList, String codeLine, int lineNum) throws SyntaxError {

        codeLine = codeLine.strip();
        if (codeLine.isEmpty()) {
            return null;
        }
        if (!codeLine.endsWith(";")) {
            throw new SyntaxError("Unparsable line %d, does not terminate with \";\"".formatted(lineNum));
        }

        Operation op = Operation.fromString(codeLine, lineNum);

        Variable var = null;
        if (op.hasVariable()) {
            String varName = codeLine.substring(op.getBeginIndex(), codeLine.length() - op.getEndOffset()).strip();
            var = varList.computeIfAbsent(varName, name -> new Variable());
        }

        return new OperationsAndVariable(var, op);
    }

    Variable getVar() {
        return var;
    }

    Operation getOp() {
        return  op;
    }
}
