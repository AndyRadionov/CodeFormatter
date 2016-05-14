package com.company.reader;

/**
 * Interface for using with try-with-resources operator
 */
public interface Closeable extends AutoCloseable {

    /**
     * Closes this resource
     *
     * @throws ReaderException if this resource cannot be closed
     */
    @Override
    void close() throws ReaderException;
}
