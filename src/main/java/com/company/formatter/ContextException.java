package com.company.formatter;

/**
 * Thrown when occurs problem with current context state
 */
public class ContextException extends Exception {

    /**
     * Construct ContextException containing describing message
     *
     * @param message describing message
     */
    public ContextException(String message) {
        super(message);
    }

    /**
     * Construct ContextException containing describing message and exception cause
     *
     * @param message describing message
     * @param cause   cause of exception
     */
    public ContextException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Construct ContextException containing exception cause
     *
     * @param cause cause of exception
     */
    public ContextException(Throwable cause) {
        super(cause);
    }
}
