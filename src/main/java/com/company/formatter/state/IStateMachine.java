package com.company.formatter.state;

import com.company.formatter.context.IContext;
import com.company.writer.IWriter;

/**
 * Interface for State design pattern.
 * Concrete formatter states formatting incoming symbols.
 */
public interface IStateMachine {
    /**
     * Formatting symbols from reader and writes them to writer.
     *
     * @param symbol Symbol to Format and write
     * @param writer Writer to write symbols
     * @param context      current formatter context
     * @throws StateException if exception occurs
     */
    void writeFormattedSymbol(char symbol, IWriter writer, IContext context) throws StateException;

    /**
     * Setting active state of StateMachine by given symbol.
     *
     * @param symbol fetched from reader
     */
    void setActiveStateBySymbol(char symbol);
}
