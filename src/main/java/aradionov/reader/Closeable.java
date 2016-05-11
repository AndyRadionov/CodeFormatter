package aradionov.reader;

/**
 * @author Andrey Radionov andyomsk@gmail.com
 */
public interface Closeable extends AutoCloseable {
    @Override
    void close() throws ReaderException;
}
