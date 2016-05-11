package aradionov.formatter;

import aradionov.format.FormatException;
import aradionov.format.ICommandExecutor;
import aradionov.format.IFormat;
import aradionov.reader.IReader;
import aradionov.reader.ReaderException;
import aradionov.writer.IWriter;
import aradionov.writer.WriterException;

/**
 * @author Andrey Radionov andyomsk@gmail.com
 */
public class Formatter implements IFormatter {

    IFormat format;
    ICommandExecutor commandExecutor;

    public Formatter(IFormat format, ICommandExecutor commandExecutor) {
        this.format = format;
        this.commandExecutor = commandExecutor;
    }

    @Override
    public void format(IReader reader, IWriter writer) throws FormatterException {
        StringBuilder resultBuilder = new StringBuilder();
        StringBuilder lineBuilder = new StringBuilder();
        try {
            while (reader.hasNext()) {
                char c = reader.read();
                commandExecutor.execute(c, format, lineBuilder, resultBuilder);
            }
            writer.write(resultBuilder.toString().trim());
        } catch (ReaderException | WriterException | FormatException e) {
            throw new FormatterException(e);
        }
    }

}
