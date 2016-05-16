package com.company.writer;

/**
 * Class for writing given char symbols or String to output String.
 */
public class StringWriter implements IWriter {
    private StringBuilder resultBuilder;

    /**
     * Constructs new StringWriter.
     */
    public StringWriter() {
        this.resultBuilder = new StringBuilder();
    }

    /**
     * Writes given String to output String.
     *
     * @param string String to write to String
     * @throws WriterException if String cannot be written
     */
    @Override
    public void write(final String string) throws WriterException {
        resultBuilder.append(string);
    }

    /**
     * Writes given String to output String.
     *
     * @param c char to write to output String
     * @throws WriterException if String cannot be written
     */
    @Override
    public void write(final char c) throws WriterException {
        resultBuilder.append(c);
    }

    /**
     * @return String written to output String
     */
    @Override
    public String getString() {
        return resultBuilder.toString();
    }
}
