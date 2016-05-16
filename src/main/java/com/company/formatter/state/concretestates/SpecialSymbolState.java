package com.company.formatter.state.concretestates;

import com.company.formatter.context.IContext;
import com.company.formatter.state.IStateMachine;
import com.company.formatter.state.StateException;
import com.company.writer.IWriter;
import com.company.writer.WriterException;

/**
 * Special symbol state of formatter StateMachine
 */
public class SpecialSymbolState implements IState {
    /**
     * Combines special symbols if they appears in a row, or writing space after special symbol.
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
            if (!context.getSpecialSymbols().contains(symbol) &&
                    symbol != ' ') {
                writer.write(' ');
            }
            writer.write(symbol);
            stateMachine.setActiveStateBySymbol(symbol);
        } catch (WriterException e) {
            throw new StateException(e);
        }
    }
}
