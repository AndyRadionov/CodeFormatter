package aradionov.writer;

/**
 * @author Andrey Radionov andyomsk@gmail.com
 */
public class StringWriter implements IWriter {
    private StringBuilder resultBuilder;

    public StringWriter() {
        this.resultBuilder = new StringBuilder();
    }

    @Override
    public void write(String s) throws WriterException {
        resultBuilder.append(s);
    }

    @Override
    public String getString() {
        return resultBuilder.toString();
    }
}
