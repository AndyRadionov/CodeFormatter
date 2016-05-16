package com.company.formatter;

import com.company.formatter.context.ContextException;
import com.company.formatter.context.IContext;
import com.company.formatter.state.IStateMachine;
import com.company.formatter.state.StateException;
import com.company.reader.IReader;
import com.company.reader.ReaderException;
import com.company.writer.IWriter;

/**
 * Class for formatting symbols fetched from reader and writing them to writer.
 */
public class Formatter implements IFormatter {

    private IContext context;
    private IStateMachine stateMachine;

    /**
     * Constructs new Formatter.
     *
     * @param context      Context Object with current formatting context
     * @param stateMachine formatter state machine
     */
    public Formatter(final IContext context, final IStateMachine stateMachine) {
        this.context = context;
        this.stateMachine = stateMachine;
    }

    /**
     * Formats symbols from reader and writing its to write.
     *
     * @param reader Reader to read symbols from
     * @param writer Writer to write symbols to
     * @throws FormatterException if exception occurs while formatting
     */
    @Override
    public void format(final IReader reader, final IWriter writer) throws FormatterException {
        try {
            while (reader.hasNext()) {
                stateMachine.writeFormattedSymbol(reader.read(), writer, context);
            }
            context.checkIsEnoughBrackets();
        } catch (ContextException | StateException | ReaderException e) {
            throw new FormatterException(e);
        }
    }
}