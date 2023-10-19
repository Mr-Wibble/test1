public class Packet {
    private VariableList variableList;
    private CodeList codeList;
    public Packet(VariableList variableList, CodeList codeList) {
        this.setVariableList(variableList);
        this.setCodeList(codeList);
    }

    public VariableList getVarList() {
        return variableList;
    }

    public void setVariableList(VariableList variableList) {
        this.variableList = variableList;
    }

    public CodeList getCodeList() {
        return codeList;
    }

    public void setCodeList(CodeList codeList) {
        this.codeList = codeList;
    }
    
    public int getLineNumber() {
        return getCodeList().getLineNumber();
    }
    
    public int getLineToRead() {
        return getCodeList().getLineToRead();
    }
    
    public int getCodeLength() {
        return getCodeList().getCode().size();
    }
    
    public String getLine() {
        return getCodeList().readLine().trim(); /* Remove whitespace, as optional and removing it allows the code to read the command */
    }
    
    public void checkForVar(String varName, boolean isWhile) {
        if (getVarList().noVarCheck(varName)) {
            getVarList().addVar(varName, new Variable());
            if (isWhile) {
                System.out.println();
                System.out.println("((Warning (1): Variable " + varName + " not declared via 'clear' prior to 'while [var] not 0 do' command.))");
                System.out.println();
            }
        }
    }
    
    public void printAllVars() {
        getVarList().printAllVars();
    }
    
    public void clear(String varName) {
        getVarList().clear(varName);
    }

    public void incr(String varName) {
        getVarList().incr(varName);
    }

    public void decr(String varName) {
        getVarList().decr(varName);
    }
}
