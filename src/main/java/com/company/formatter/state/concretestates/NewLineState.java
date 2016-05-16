package com.company.formatter.state.concretestates;

import com.company.formatter.context.ContextException;
import com.company.formatter.context.IContext;
import com.company.formatter.state.IStateMachine;
import com.company.formatter.state.StateException;
import com.company.writer.IWriter;
import com.company.writer.WriterException;

/**
 * New Line state of formatter StateMachine
 */
public class NewLineState implements IState {
    /**
     * Writes offset after new line symbol. Skips whitespaces.
     *
     * @param symbol       to Format and write
     * @param writer       to write symbols
     * @param context      current formatter context
     * @param stateMachine formatter stateMachine
     * @throws StateException
     */
    @Override
    public void writeFormattedSymbol(final char symbol, final IWriter writer, final IContext context,
                                     final IStateMachine stateMachine) throws StateException {
        try {
            writer.write(context.getOffset());
            if (!Character.isWhitespace(symbol)) {
                writer.write(symbol);
                stateMachine.setActiveStateBySymbol(symbol);
            } else {
                stateMachine.setActiveStateBySymbol(' ');
            }
        } catch (WriterException | ContextException e) {
            throw new StateException(e);
        }
    }
}
