package com.company.writer;

import java.io.IOException;

/**
 * Class for writing given char symbols or String to output resource file
 */
public class FileWriter implements IWriter, Closeable {
    private java.io.FileWriter fileWriter;
    private StringBuilder resultBuilder;

    /**
     * Constructs new FileWriter from given fileName resource
     *
     * @param fileName name of file to write
     * @throws WriterException when file cannot be written
     */
    public FileWriter(String fileName) throws WriterException {
        try {
            this.fileWriter = new java.io.FileWriter(fileName);
            this.resultBuilder = new StringBuilder();
        } catch (IOException e) {
            throw new WriterException(e);
        }
    }

    /**
     * Writes given String to output resource file
     *
     * @param string String to write to output resource file
     * @throws WriterException if String cannot be written
     */
    @Override
    public void write(String string) throws WriterException {
        try {
            fileWriter.write(string);
        } catch (IOException e) {
            throw new WriterException(e);
        }
    }

    /**
     * Writes given String to output resource file
     *
     * @param c char to write to output resource file
     * @throws WriterException if String cannot be written
     */
    @Override
    public void write(char c) throws WriterException {
        try {
            fileWriter.write(c);
        } catch (IOException e) {
            throw new WriterException(e);
        }
    }

    /**
     * @return String written to output resource file
     */
    @Override
    public String getString() {
        return resultBuilder.toString();
    }

    /**
     * Closes this resource file
     *
     * @throws WriterException if this resource file cannot be closed
     */
    @Override
    public void close() throws WriterException {
        try {
            fileWriter.close();
        } catch (IOException e) {
            throw new WriterException(e);
        }
    }
}
