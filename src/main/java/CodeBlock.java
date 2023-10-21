import exceptions.SyntaxError;

import java.util.Map;

public class CodeBlock {
    private final int level;
    private final Variable varToCheck;
    private final Map<String, Variable> varList;
    private final CodeList codeList;


    public CodeBlock(Map<String, Variable> variableList, CodeList codeList) {
        this.level = 0;
        this.varList = variableList;
        this.codeList = codeList;
        this.varToCheck = null;
    }

    private CodeBlock(int level, Map<String, Variable> variableList, CodeList codeList, Variable var) {
        this.level = level;
        this.varList = variableList;
        this.codeList = codeList;
        this.varToCheck = var;
    }

    public void execute(boolean doExecute) throws SyntaxError {
        int startOfLoop = codeList.getLineNumber();
        while (codeList.getLineToRead() < codeList.getCode().size()) {
            String codeLine = codeList.readLine();

            OperationsAndVariable opAndVar = OperationsAndVariable.getVarAndOp(varList, codeLine, codeList.getLineToRead() + 1);
            if (opAndVar == null) {
                continue;
            }

            switch (opAndVar.getOp()) {
                case Operation.INCR:
                    if (doExecute) opAndVar.getVar().incr();
                    break;

                case Operation.DECR:
                    if (doExecute) opAndVar.getVar().decr();
                    break;

                case Operation.CLEAR:
                    if (doExecute) opAndVar.getVar().clear();
                    break;

                case Operation.WHILE:
                    CodeBlock codeBlock = new CodeBlock(level + 1, varList, codeList, opAndVar.getVar());
                    codeBlock.execute(doExecute && opAndVar.getVar().value() != 0);
                    break;

                case Operation.END:
                    if (varToCheck == null) {
                        throw new SyntaxError("END with no matching \"while\" @ " + codeLine);
                    }
                    else if (varToCheck.value() == 0) {
                        return;
                    } else {
                        codeList.jumpTo(startOfLoop);
                    }
                    break;
            }
        }

        if (this.level != 0) {
            throw new SyntaxError("Error (1): At least one 'while _ not 0 do' command does not have a corresponding 'end' command.");
        }
    }
}
