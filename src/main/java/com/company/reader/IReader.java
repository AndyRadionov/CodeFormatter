package com.company.reader;

/**
 * Interface for reading char symbols from given resource.
 */
public interface IReader {
    /**
     * Reads next char symbol from resource.
     *
     * @return next char symbol read from resource
     * @throws ReaderException if next char symbol cannot be read
     */
    char read() throws ReaderException;

    /**
     * Checks if resource has next char symbol to read.
     *
     * @return true if resource has next char symbol to read
     * @throws ReaderException if next char symbol cannot be read
     */
    boolean hasNext() throws ReaderException;
}
