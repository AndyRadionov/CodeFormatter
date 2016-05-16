package com.company.reader;

/**
 * Thrown when occurs problem while reading symbols from resource.
 */
public class ReaderException extends Exception {

    /**
     * Construct ReaderException with containing exception cause.
     *
     * @param cause cause of exception
     */
    public ReaderException(final Throwable cause) {
        super(cause);
    }
}
