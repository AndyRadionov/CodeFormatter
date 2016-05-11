package aradionov.formatter;

import aradionov.reader.IReader;
import aradionov.writer.IWriter;

/**
 * @author Andrey Radionov andyomsk@gmail.com
 */
public interface IFormatter {
    void format(final IReader reader, final IWriter writer) throws FormatterException;
}
