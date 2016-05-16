package com.company.formatter.state.concretestates;

import com.company.formatter.context.ContextException;
import com.company.formatter.context.IContext;
import com.company.formatter.state.IStateMachine;
import com.company.formatter.state.StateException;
import com.company.writer.IWriter;
import com.company.writer.WriterException;

/**
 * Brace close state of formatter StateMachine.
 */
public class BraceCloseState implements IState {
    /**
     * Adds newLine symbol and offset after close brace symbol.
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
            if (symbol != '\n') {
                writer.write('\n');
                writer.write(context.getOffset());
            }
            writer.write(symbol);
            stateMachine.setActiveStateBySymbol(symbol);
        } catch (WriterException | ContextException e) {
            throw new StateException(e);
        }
    }
}
