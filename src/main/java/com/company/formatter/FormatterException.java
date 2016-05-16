package com.company.formatter;

/**
 * Thrown when occurs problem while formatting symbols.
 */
public class FormatterException extends Exception {

    /**
     * Construct FormatterException containing exception cause.
     *
     * @param cause cause of exception
     */
    public FormatterException(final Throwable cause) {
        super(cause);
    }
}
