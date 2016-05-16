package com.company.formatter.context;

/**
 * Thrown when occurs problem with current formatter context.
 */
public class ContextException extends Exception {

    /**
     * Construct ContextException containing describing message.
     *
     * @param message describing message
     */
    public ContextException(final String message) {
        super(message);
    }

    /**
     * Construct ContextException containing describing message and exception cause.
     *
     * @param message describing message
     * @param cause   cause of exception
     */
    public ContextException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Construct ContextException containing exception cause.
     *
     * @param cause cause of exception
     */
    public ContextException(final Throwable cause) {
        super(cause);
    }
}
