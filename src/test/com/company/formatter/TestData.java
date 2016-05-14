package com.company.formatter;

/**
 * Test String data
 */
public class TestData {
    protected static final String INPUT_STRING = "while(true){System.out.println(\"Hello\");if(a<b)a=b;}";
    protected static final String OUTPUT_STRING =
            "while (true) {\n" +
                    "    System.out.println (\"Hello\") ;\n" +
                    "    if (a < b) a = b;\n" +
                    "}";

}
