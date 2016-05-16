package com.company.writer;

/**
 * Thrown when occurs problem while writing symbols to resource.
 */
public class WriterException extends Exception {

    /**
     * Construct WriterException with containing exception cause.
     *
     * @param cause cause of exception
     */
    public WriterException(final Throwable cause) {
        super(cause);
    }
}
