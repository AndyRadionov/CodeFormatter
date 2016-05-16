package com.company.writer;

/**
 * Interface for writing given char symbols or String to output resource.
 */
public interface IWriter {
    /**
     * Writes given String to output resource.
     *
     * @param string String to write to output resource
     * @throws WriterException if String cannot be written
     */
    void write(String string) throws WriterException;

    /**
     * Writes given char symbol to output resource.
     *
     * @param c char to write to output resource
     * @throws WriterException if char symbol cannot be written
     */
    void write(char c) throws WriterException;

    /**
     * @return String written to output resource
     */
    String getString();
}
