package com.company.formatter;

import com.company.reader.IReader;
import com.company.writer.IWriter;

/**
 * Class for formatting symbols from reader and writing them to writer
 */
public class Formatter implements IFormatter {

    private IContext context;

    /**
     * Constructs new Formatter
     *
     * @param context Context Object with current context and format settings
     */
    public Formatter(IContext context) {
        this.context = context;
    }

    /**
     * Formats symbols from reader and writing its to write
     *
     * @param reader Reader to read symbols from
     * @param writer Writer to write symbols to
     * @throws FormatterException if exception occurs while formatting
     */
    @Override
    public void format(IReader reader, IWriter writer) throws FormatterException {
        try {
            context.writeFormattedSymbol(reader, writer);
        } catch (ContextException e) {
            throw new FormatterException(e);
        }
    }
}
