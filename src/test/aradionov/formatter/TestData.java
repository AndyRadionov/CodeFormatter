package aradionov.formatter;

/**
 * @author Andrey Radionov andyomsk@gmail.com
 */
public class TestData {
    public static final String INPUT_STRING = "while(true){System.out.println(\"Hello!\");if(a<b)a=b;}";
    public static final String OUTPUT_STRING =
            "while (true) {\n" +
            "    System.out.println (\"Hello!\") ;\n" +
            "    if (a<b) {\n" +
            "        a = b;\n" +
            "    }\n" +
            "}";

}
