package com.company.formatter;

import com.company.reader.IReader;
import com.company.reader.ReaderException;
import com.company.reader.StringReader;
import com.company.writer.IWriter;
import com.company.writer.StringWriter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Junit Test for Formatter.format() method
 */
public class FormatterTest {
    private IContext context;
    private IFormatter formatter;
    private IReader reader;
    private IWriter writer;

    @Before
    public void setUp() throws Exception {
        context = new Context();
        formatter = new Formatter(context);
        reader = new StringReader(TestData.INPUT_STRING);
        writer = new StringWriter();
    }

    @Test
    public void formattingEqualsTest() throws FormatterException {
        reader = new StringReader(TestData.INPUT_STRING);
        writer = new StringWriter();
        formatter.format(reader, writer);
        assertEquals("EqualsTest", TestData.OUTPUT_STRING, writer.getString());
    }

    @Test(expected = FormatterException.class)
    public void exceptionTest() throws ReaderException, FormatterException {
        reader = new StringReader("}}}");
        writer = new StringWriter();
        formatter.format(reader, writer);
    }

}