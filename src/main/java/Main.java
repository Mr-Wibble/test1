import exceptions.SyntaxError;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        String filename;
        Map<String, Variable> varList = new HashMap<>();

        System.out.println("Input file name:"); /* Assumption: code is in a .txt file, and the ".txt" is not included when file name is input */
        filename = new Scanner(System.in).nextLine() + ".txt";

        try {
            CodeList codeList = loadCode(filename);

            CodeBlock codeBlock = new CodeBlock(varList, codeList);
            codeBlock.execute(true);

            // Print variables
            for (Map.Entry<String, Variable> entry : varList.entrySet()) {
                System.out.println(entry.getKey() + " = " + entry.getValue());
            }

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
        }
        catch (SyntaxError se) {
            System.err.println(se.getMessage());
        }
    }

    private static CodeList loadCode(String filename) throws FileNotFoundException {
        File codeToRead = new File(filename);
        Scanner fileReader = new Scanner(codeToRead);

        CodeList codeList = new CodeList();
        while (fileReader.hasNext()) {
            codeList.addCode(fileReader.nextLine());
        }
        return codeList;
    }
}