package com.company.formatter.state.concretestates;

import com.company.formatter.context.ContextException;
import com.company.formatter.context.IContext;
import com.company.formatter.state.IStateMachine;
import com.company.formatter.state.StateException;
import com.company.writer.IWriter;
import com.company.writer.WriterException;

/**
 * Start state or Space symbol state of formatter StateMachine
 */
public class StartOrSpaceState implements IState {
    /**
     * Skips unnecessary whitespaces.
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
            if (!Character.isWhitespace(symbol)) {
                if (symbol == '}') {
                    context.decreaseOffset();
                    writer.write('\n');
                    writer.write(context.getOffset());
                } else {
                    writer.write(symbol);
                    stateMachine.setActiveStateBySymbol(symbol);
                }
            }
        } catch (WriterException | ContextException e) {
            throw new StateException(e);
        }
    }
}
