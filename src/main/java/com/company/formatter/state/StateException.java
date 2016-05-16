package com.company.formatter.state;

/**
 * Thrown when occurs problem with formatter state.
 */
public class StateException extends Exception {

    /**
     * Construct StateException containing exception cause.
     *
     * @param cause cause of exception
     */
    public StateException(final Throwable cause) {
        super(cause);
    }
}
