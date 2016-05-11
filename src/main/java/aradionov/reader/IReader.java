package aradionov.reader;

/**
 * @author Andrey Radionov andyomsk@gmail.com
 */
public interface IReader {
    char read() throws ReaderException;

    boolean hasNext() throws ReaderException;
}
