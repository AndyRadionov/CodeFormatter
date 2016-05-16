package com.company.formatter;

/**
 * Test Strings for Formatter format() method tests
 */
public class TestData {
    public static final String INPUT_STRING1 = "for(int i=0;i<5;i++){\n\n\nhello();}";
    public static final String OUTPUT_STRING1 =
            "for (int i = 0; i < 5; i ++ ) {\n" +
                    "    hello ();\n" +
                    "}";
    public static final String INPUT_STRING2 = "String string = \"abc;{}literal\";";
    public static final String OUTPUT_STRING2 = INPUT_STRING2;
}
