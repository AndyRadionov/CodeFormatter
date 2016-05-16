package com.company.formatter;

import com.company.formatter.context.Context;
import com.company.formatter.context.IContext;
import com.company.formatter.format.Format;
import com.company.formatter.format.IFormat;
import com.company.formatter.state.IStateMachine;
import com.company.formatter.state.StateMachine;
import com.company.reader.IReader;
import com.company.reader.StringReader;
import com.company.writer.FileWriter;
import com.company.writer.IWriter;
import com.company.writer.StringWriter;
import com.company.writer.WriterException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * JUnit tests for Formatter writeFormattedSymbol() method
 */
public class FormatterTest {
    private IFormat format;
    private IContext context;
    private IStateMachine stateMachine;
    private IFormatter formatter;
    private IReader reader;
    private IWriter writer;

    @Before
    public void setUp() throws Exception {
        format = new Format();
        context = new Context(format);
        stateMachine = new StateMachine(format);
        formatter = new Formatter(context, stateMachine);
    }

    /**
     * Test equality of String after formatting with sample String
     * Testing string contains 'for' operator
     *
     * @throws FormatterException
     */
    @Test
    public void formattingEqualsTest1ForOperator() throws FormatterException {
        reader = new StringReader(TestData.INPUT_STRING1);
        writer = new StringWriter();
        formatter.format(reader, writer);
        assertEquals("EqualsTest2", TestData.OUTPUT_STRING1, writer.getString());
    }

    /**
     * Equality test checks correct String literal formatting
     *
     * @throws FormatterException
     */
    @Test
    public void formattingEqualsTest2StringLiteral() throws FormatterException {
        reader = new StringReader(TestData.INPUT_STRING2);
        writer = new StringWriter();
        formatter.format(reader, writer);
        assertEquals("EqualsTest3", TestData.OUTPUT_STRING2, writer.getString());
    }

    /**
     * Testing correct brace balance
     * When close brace counted more than open brace
     *
     * @throws FormatterException
     */
    @Test(expected = FormatterException.class)
    public void tooMuchCloseBraceTest() throws FormatterException {
        reader = new StringReader("}}}");
        writer = new StringWriter();
        formatter.format(reader, writer);
    }

    /**
     * Testing correct brace balance
     * When open brace counted more than close brace
     *
     * @throws FormatterException
     */
    @Test(expected = FormatterException.class)
    public void tooMuchOpenBraceTest() throws FormatterException {
        reader = new StringReader("{{{");
        writer = new StringWriter();
        formatter.format(reader, writer);
    }

    /**
     * Testing correct parentheses balance
     * When close parenthesis counted more than open parenthesis
     *
     * @throws FormatterException
     */
    @Test(expected = FormatterException.class)
    public void tooMuchCloseParenthesisTest() throws FormatterException {
        reader = new StringReader(")))");
        writer = new StringWriter();
        formatter.format(reader, writer);
    }

    /**
     * Testing correct parentheses balance
     * When open parenthesis counted more than close parenthesis
     *
     * @throws FormatterException
     */
    @Test(expected = FormatterException.class)
    public void tooMuchOpenParenthesisTest() throws FormatterException {
        reader = new StringReader("(((");
        writer = new StringWriter();
        formatter.format(reader, writer);
    }

    /**
     * Testing correct writeFormattedSymbol method working with empty reader stream
     */
    @Test
    public void EmptySymbolTest() {
        reader = new StringReader("");
        writer = new StringWriter();
        try {
            formatter.format(reader, writer);
        } catch (FormatterException e) {
            assertTrue(false);
        }
    }

    /**
     * Testing mocked FileWriter on exception occurrence while using write() method
     *
     * @throws WriterException
     * @throws FormatterException
     */
    @Test(expected = FormatterException.class)
    public void FileWriterMockTest() throws WriterException, FormatterException {
        reader = new StringReader("Test");
        writer = Mockito.mock(FileWriter.class);
        Mockito.doThrow(WriterException.class).when(writer).write(Mockito.anyChar());
        formatter.format(reader, writer);
    }
}