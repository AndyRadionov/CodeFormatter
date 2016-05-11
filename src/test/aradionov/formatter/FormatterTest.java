package aradionov.formatter;

import aradionov.format.ICommandExecutor;
import aradionov.format.IFormat;
import aradionov.format.JavaCommandExecutor;
import aradionov.format.JavaFormat;
import aradionov.reader.FileReader;
import aradionov.reader.IReader;
import aradionov.reader.ReaderException;
import aradionov.reader.StringReader;
import aradionov.writer.IWriter;
import aradionov.writer.StringWriter;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;

/**
 * @author Andrey Radionov andyomsk@gmail.com
 */
public class FormatterTest {
    private IFormat format;
    private ICommandExecutor commandExecutor;
    private IFormatter formatter;
    private IReader reader;
    private IWriter writer;

    @Before
    public void setUp() {
        format = new JavaFormat();
        commandExecutor = new JavaCommandExecutor();
        formatter = new Formatter(format, commandExecutor);
    }

    @Test
    public void formattingEqualsTest() throws FormatterException {
        reader = new StringReader(TestData.INPUT_STRING);
        writer = new StringWriter();
        formatter.format(reader, writer);
        assertEquals("EqualsTest",TestData.OUTPUT_STRING, writer.getString());
    }

    @Test(expected = FormatterException.class)
    public void exceptionTest() throws ReaderException, FormatterException, IOException {
        reader = new StringReader("}}}");
        writer = new StringWriter();
        formatter.format(reader, writer);
    }
}