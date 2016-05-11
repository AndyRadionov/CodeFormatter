package aradionov.writer;

/**
 * @author Andrey Radionov andyomsk@gmail.com
 */
public interface IWriter {
    void write(String s) throws WriterException;

    String getString();
}
