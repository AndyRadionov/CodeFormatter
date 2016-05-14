package com.company.writer;

/**
 * Interface for using with try-with-resources operator
 */
public interface Closeable extends AutoCloseable {

    /**
     * Closes this resource
     *
     * @throws WriterException if this resource cannot be closed
     */
    @Override
    void close() throws WriterException;
}
