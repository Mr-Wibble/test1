import exceptions.SyntaxError;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.junit.Assert;

public class CodeBlockTest {

    @Test
    public void skipWhileTest() throws FileNotFoundException, SyntaxError {
        CodeList list = loadResourceFile("skipWhileTest");
        Map<String, Variable> varList = new HashMap<>();

        CodeBlock cb = new CodeBlock(varList, list);
        cb.execute(true);
        Assert.assertEquals(2, varList.get("B").value());
    }

    @Test
    public void doOneWhileTest() throws FileNotFoundException, SyntaxError {
        CodeList list = loadResourceFile("doOneWhileTest");
        Map<String, Variable> varList = new HashMap<>();

        CodeBlock cb = new CodeBlock(varList, list);
        cb.execute(true);
        Assert.assertEquals(0, varList.get("A").value());
        Assert.assertEquals(1, varList.get("B").value());
    }

    @Test
    public void doTwoWhileTest() throws FileNotFoundException, SyntaxError {
        CodeList list = loadResourceFile("doTwoWhileTest");
        Map<String, Variable> varList = new HashMap<>();

        CodeBlock cb = new CodeBlock(varList, list);
        cb.execute(true);
        Assert.assertEquals(0, varList.get("A").value());
        Assert.assertEquals(2, varList.get("B").value());
    }

    @Test
    public void doMultiplicationTest() throws FileNotFoundException, SyntaxError {
        CodeList list = loadResourceFile("simpleMultiplicationTest");
        Map<String, Variable> varList = new HashMap<>();

        CodeBlock cb = new CodeBlock(varList, list);
        cb.execute(true);
        Assert.assertEquals(6, varList.get("Z").value());
    }

    private CodeList loadResourceFile(String fileName) throws FileNotFoundException {

        String filename = this.getClass().getResource(fileName + ".txt").getFile();

        File codeToRead = new File(filename);
        Scanner fileReader = new Scanner(codeToRead);

        CodeList codeList = new CodeList();
        while (fileReader.hasNext()) {
            codeList.addCode(fileReader.nextLine());
        }
        return codeList;
    }
}
