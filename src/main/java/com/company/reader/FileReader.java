package com.company.reader;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Class for reading char symbols from file.
 */
public class FileReader implements IReader, Closeable {
    private java.io.FileReader fileReader;

    /**
     * Constructs FileReader from given file name.
     *
     * @param fileName name of file to read
     * @throws ReaderException when reading exception occurs
     */
    public FileReader(final String fileName) throws ReaderException {
        try {
            fileReader = new java.io.FileReader(fileName);
        } catch (FileNotFoundException e) {
            throw new ReaderException(e);
        }
    }

    /**
     * Reads next char symbol from file.
     *
     * @return next char symbol read from file
     * @throws ReaderException if next char symbol cannot be read
     */
    @Override
    public char read() throws ReaderException {
        try {
            return (char) fileReader.read();
        } catch (IOException e) {
            throw new ReaderException(e);
        }
    }

    /**
     * Checks if resource file has next char symbol to read.
     *
     * @return true if resource file has next char symbol to read
     * @throws ReaderException if next char symbol cannot be read
     */
    @Override
    public boolean hasNext() throws ReaderException {
        try {
            return fileReader.ready();
        } catch (IOException e) {
            throw new ReaderException(e);
        }
    }

    /**
     * Closes resource file.
     *
     * @throws ReaderException if this resource file cannot be closed
     */
    @Override
    public void close() throws ReaderException {
        try {
            fileReader.close();
        } catch (IOException e) {
            throw new ReaderException(e);
        }
    }
}
