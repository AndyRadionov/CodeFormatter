package aradionov.format;

/**
 * @author Andrey Radionov andyomsk@gmail.com
 */
public class FormatException extends Exception {
    public FormatException(String message) {
        super(message);
    }

    public FormatException(Throwable cause) {
        super(cause);
    }
}
