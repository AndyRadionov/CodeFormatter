package aradionov.writer;

/**
 * @author Andrey Radionov andyomsk@gmail.com
 */
public interface Closeable extends AutoCloseable {
    @Override
    void close() throws WriterException;
}
