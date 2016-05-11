package aradionov.reader;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Andrey Radionov andyomsk@gmail.com
 */
public class FileReader implements IReader, Closeable {
    private java.io.FileReader fileReader;

    public FileReader(String fileName) throws ReaderException {
        try {
            fileReader = new java.io.FileReader(fileName);
        } catch (FileNotFoundException e) {
            throw new ReaderException(e);
        }
    }

    @Override
    public char read() throws ReaderException {
        try {
            return (char) fileReader.read();
        } catch (IOException e) {
            throw new ReaderException(e);
        }
    }

    @Override
    public boolean hasNext() throws ReaderException {
        try {
            return fileReader.ready();
        } catch (IOException e) {
            throw new ReaderException(e);
        }
    }


    @Override
    public void close() throws ReaderException {
        try {
            fileReader.close();
        } catch (IOException e) {
            throw new ReaderException(e);
        }
    }
}
