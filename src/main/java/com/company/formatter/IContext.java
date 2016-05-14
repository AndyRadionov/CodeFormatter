package com.company.formatter;

import com.company.reader.IReader;
import com.company.writer.IWriter;

/**
 * Interface for formatting symbols according to current format settings and current context
 */
public interface IContext {
    /**
     * Formatting symbols from reader and writes them to writer
     * @param reader Reader to read symbols
     * @param writer Writer to write symbols
     * @throws ContextException if exception occurs
     */
    void writeFormattedSymbol(IReader reader, IWriter writer) throws ContextException;
}
