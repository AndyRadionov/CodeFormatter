package com.company.reader;

/**
 * Class for reading char symbols from String
 */
public class StringReader implements IReader {
    private String string;
    private int position;

    /**
     * Constructs StringReader from given String
     *
     * @param string String to read
     */
    public StringReader(String string) {
        this.string = string;
        this.position = 0;
    }

    /**
     * Reads next char symbol from String
     *
     * @return next char symbol read from String
     * @throws ReaderException if next char symbol cannot be read
     */
    @Override
    public char read() throws ReaderException {
        return string.charAt(position++);
    }

    /**
     * Checks if resource String has next char symbol to read
     *
     * @return true if resource String has next char symbol to read
     * @throws ReaderException if next char symbol cannot be read
     */
    @Override
    public boolean hasNext() throws ReaderException {
        return position <= (string.length() - 1);
    }
}
