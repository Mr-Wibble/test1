public class Packet {
    VariableList variableList;
    CodeList codeList;
    boolean hasError;
    public Packet(VariableList variableList, CodeList codeList, boolean hasError) {
        this.variableList = variableList;
        this.codeList = codeList;
        this.hasError = hasError;
    }
}
