import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        String filename;
        VariableList varList = new VariableList();
        CodeBlock codeBlock = new CodeBlock(0, "");
        System.out.println("Input file name:");
        filename = new Scanner(System.in).nextLine() + ".txt";
        Packet packet;
        boolean hasError;
        try {
            File codeToRead = new File(filename);
            Scanner fileReader = new Scanner(codeToRead);
            CodeList codeList = new CodeList();
            while (fileReader.hasNext()) {
                codeList.addCode(fileReader.nextLine());
            }
            packet = new Packet(varList, codeList, false);
            packet = codeBlock.loop(packet);
            hasError = packet.hasError;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            hasError = true;
        }
        if (!hasError) {
            System.out.println("Code execution complete!");
            System.out.println("Final variable states are as follows:");
            varList.printAllVars();
        }
    }
}