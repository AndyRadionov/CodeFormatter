package com.company.formatter.state.concretestates;

import com.company.formatter.context.IContext;
import com.company.formatter.state.IStateMachine;
import com.company.formatter.state.StateException;
import com.company.writer.IWriter;
import com.company.writer.WriterException;

/**
 * Inside String Literal state of formatter StateMachine
 */
public class InsideStringLiteralState implements IState {
    /**
     * Do not switch state until next symbol equals closing quote.
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
            if (symbol == '\n') {
                writer.write('\\');
                writer.write('n');
            } else {
                writer.write(symbol);
            }
            if (symbol == '\"') {
                stateMachine.setActiveStateBySymbol(' ');
            }
        } catch (WriterException e) {
            throw new StateException(e);
        }
    }
}
