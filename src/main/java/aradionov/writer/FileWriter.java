package aradionov.writer;

import java.io.IOException;

/**
 * @author Andrey Radionov andyomsk@gmail.com
 */
public class FileWriter implements IWriter, Closeable {
    private java.io.FileWriter fileWriter;
    private StringBuilder resultBuilder;

    public FileWriter(String fileName) throws WriterException {
        try {
            this.fileWriter = new java.io.FileWriter(fileName);
            this.resultBuilder = new StringBuilder();
        } catch (IOException e) {
            throw new WriterException(e);
        }
    }

    @Override
    public void write(String string) throws WriterException {
        try {
            fileWriter.write(string);
        } catch (IOException e) {
            throw new WriterException(e);
        }
    }

    @Override
    public String getString() {
        return resultBuilder.toString();
    }

    @Override
    public void close() throws WriterException {
        try {
            fileWriter.close();
        } catch (IOException e) {
            throw new WriterException(e);
        }
    }
}
