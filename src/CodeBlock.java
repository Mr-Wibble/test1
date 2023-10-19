public class CodeBlock {
    private final int level;
    private final String varToCheck;
    private boolean vTCFlag = true;
    private int startOfLoop;

    public CodeBlock(int level, String varToCheck) {
        this.level = level;
        this.varToCheck = varToCheck;
    }

    public Packet loop(Packet packet) {
        setStartOfLoop(packet.getLineNumber());
        while (isvTCFlag()) {
            if (packet.getLineToRead() < packet.getCodeLength()) {
                String codeLine = packet.getLine();
                //check that code ends with semicolon
                if (codeLine.endsWith(";")) {
                    //clear command
                    if (codeLine.startsWith("clear")) {
                        String varName = codeLine.substring(6, codeLine.length() - 1);
                        packet.checkForVar(varName, false);
                        packet.clear(varName);
                    //incr command
                    } else if (codeLine.startsWith("incr")) {
                        String varName = codeLine.substring(5, codeLine.length() - 1);
                        packet.checkForVar(varName, false);
                        packet.incr(varName);
                    //decr command
                    } else if (codeLine.startsWith("decr")) {
                        String varName = codeLine.substring(5, codeLine.length() - 1);
                        packet.checkForVar(varName, false);
                        if (packet.getVarList().getVarValue(varName) <= 0) {
                            System.out.println("Error (4): Negative integer creation attempted. All variables must be non-negative integers.");
                            System.exit(0);
                        } else {
                            packet.decr(varName);
                        }
                    //while _ not 0 do command    
                    } else if (codeLine.startsWith("while") && codeLine.endsWith("not 0 do;")) {
                        String varName = codeLine.substring(6, codeLine.length() - 10);
                        packet.checkForVar(varName, true);
                        if (packet.getVarList().getVarValue(varName) != 0) {
                            CodeBlock loop = new CodeBlock(getLevel() + 1, varName);
                            packet = loop.loop(packet);
                        }
                    //end command    
                    } else if (codeLine.startsWith("end")) {
                        if (packet.getVarList().getVarValue(getVarToCheck()) == 0) {
                            setvTCFlag(false);
                        } else {
                            packet.getCodeList().jumpTo(getStartOfLoop());
                        }
                    //command not valid    
                    } else {
                        System.out.println("Error (3): Command " + codeLine + " not recognised. Please check the spelling of your command, and that the 'not 0 do' section of a while command is present.");
                        System.exit(0);
                    }
                //no semicolon at end of command    
                } else {
                    System.out.println("Error (2): Line of code does not end in ';'.");
                    System.exit(0);
                }
            } else {
                if (this.getLevel() != 0) {
                    System.out.println("Error (1): At least one 'while _ not 0 do' command does not have a corresponding 'end' command.");
                    System.exit(0);
                }
                break;
            }
        }
        return packet;
    }

    public int getLevel() {
        return level;
    }

    public String getVarToCheck() {
        return varToCheck;
    }

    public boolean isvTCFlag() {
        return vTCFlag;
    }

    public void setvTCFlag(boolean vTCFlag) {
        this.vTCFlag = vTCFlag;
    }

    public int getStartOfLoop() {
        return startOfLoop;
    }

    public void setStartOfLoop(int startOfLoop) {
        this.startOfLoop = startOfLoop;
    }
}