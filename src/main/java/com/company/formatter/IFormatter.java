package com.company.formatter;

import com.company.reader.IReader;
import com.company.writer.IWriter;

/**
 * Interface for formatting symbols fetched from reader and writing them to writer.
 */
public interface IFormatter {

    /**
     * Formats symbols from reader and writing its to write.
     *
     * @param reader Reader to read symbols from
     * @param writer Writer to write symbols to
     * @throws FormatterException if exception occurs while formatting
     */
    void format(final IReader reader, final IWriter writer) throws FormatterException;
}
