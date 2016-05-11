package aradionov.reader;

/**
 * @author Andrey Radionov andyomsk@gmail.com
 */
public class StringReader implements IReader {
    private String string;
    private int position;

    public StringReader(String string) {
        this.string = string;
        this.position = 0;
    }

    @Override
    public char read() throws ReaderException {
        return string.charAt(position++);
    }

    @Override
    public boolean hasNext() throws ReaderException {
        return position <= (string.length() - 1);
    }
}
