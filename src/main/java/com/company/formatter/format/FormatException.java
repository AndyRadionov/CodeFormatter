package com.company.formatter.format;

/**
 * Thrown when occurs problem with loading Format properties from file.
 */
public class FormatException extends Exception {
    /**
     * Construct FormatException containing describing message and exception cause.
     *
     * @param message describing message
     * @param cause   cause of exception
     */
    public FormatException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
