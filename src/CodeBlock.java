public class CodeBlock {
    protected int level;
    String varToCheck;
    protected boolean vTCFlag = true;
    protected int startOfLoop;
    VariableList varList;
    CodeList codeList;
    protected boolean hasError = false;

    public CodeBlock(int level, String varToCheck) {
        this.level = level;
        this.varToCheck = varToCheck;
    }

    public void unpack(Packet packet) {
        varList = packet.variableList;
        codeList = packet.codeList;
        hasError = packet.hasError;
    }

    public Packet loop(Packet packet) {
        unpack(packet);
        startOfLoop = codeList.getLineNumber();
        while (vTCFlag) {
            if (codeList.lineToRead < codeList.code.size()) {
                String codeLine = codeList.readLine();
                //remove leading whitespace, as optional and removing it allows the code to read the command
                while(codeLine.startsWith(" "))
                    codeLine = codeLine.substring(1);
                //check that code ends with semicolon
                if(codeLine.endsWith(";")) {
                    //clear command
                    if(codeLine.startsWith("clear")) {
                        String varName = codeLine.substring(6,codeLine.length()-1);
                        if (varList.noVarCheck(varName)) {
                            varList.addVar(varName, new Variable());
                        }
                        varList.clear(varName);
                        //incr command
                    } else if(codeLine.startsWith("incr")) {
                        String varName = codeLine.substring(5,codeLine.length()-1);
                        if (varList.noVarCheck(varName)) {
                            varList.addVar(varName, new Variable());
                        }
                        varList.incr(varName);
                        //decr command
                    } else if(codeLine.startsWith("decr")) {
                        String varName = codeLine.substring(5,codeLine.length()-1);
                        if (varList.noVarCheck(varName)) {
                            varList.addVar(varName, new Variable());
                        }
                        if(varList.getVarValue(varName) <= 0) {
                            System.out.println("Error (4): Negative integer creation attempted. All variables must be non-negative integers.");
                            hasError = true;
                            break;
                        } else {varList.decr(varName);}
                        //while _ not 0 do command    
                    } else if(codeLine.startsWith("while") && codeLine.endsWith("not 0 do;")) {
                        String varName = codeLine.substring(6,codeLine.length()-10);
                        if (varList.noVarCheck(varName)) {
                            varList.addVar(varName, new Variable());
                            System.out.println();
                            System.out.println("((Warning (1): Variable "+varName+" not declared via 'clear' prior to 'while [var] not 0 do' command.))");
                            System.out.println();
                        }
                        if(varList.getVarValue(varName) != 0) {
                            CodeBlock loop = new CodeBlock(level + 1, varName);
                            packet = loop.loop(new Packet(varList, codeList,hasError));
                            unpack(packet);
                            if (hasError) {
                                break;
                            }
                        }
                        //end command    
                    } else if(codeLine.startsWith("end")) {
                        if(varList.getVarValue(varToCheck) == 0) {
                            vTCFlag = false;
                        } else {codeList.jumpTo(startOfLoop);}
                        //command not valid    
                    } else {
                        System.out.println("Error (3): Command " + codeLine + " not recognised. Please check the spelling of your command, and that the 'not 0 do' section of a while command is present.");
                        hasError = true;
                        break;
                    }
                    //no semicolon at end of command    
                } else {
                    System.out.println("Error (2): Line of code does not end in ';'."); /* Assumption: Every line contains code, and all code only takes up one line. */
                    hasError = true;
                    vTCFlag = false;
                }
            } else {
                if (this.level != 0) {
                    System.out.println("Error (1): At least one 'while _ not 0 do' command does not have a corresponding 'end' command.");
                    hasError = true;
                }
                break;
            }
        }
        return new Packet(varList,codeList,hasError);
    }
}
