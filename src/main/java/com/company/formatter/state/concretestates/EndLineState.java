package com.company.formatter.state.concretestates;

import com.company.formatter.context.ContextException;
import com.company.formatter.context.IContext;
import com.company.formatter.state.IStateMachine;
import com.company.formatter.state.StateException;
import com.company.writer.IWriter;
import com.company.writer.WriterException;

/**
 * End line state of formatter StateMachine
 */
public class EndLineState implements IState {
    /**
     * Adds newLine symbol and offset after end line symbol if not inside parentheses.
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
            char currentSymbol = symbol;
            if (context.getParenthesesLevel() > 0) {
                if (currentSymbol == '\n') {
                    currentSymbol = ' ';
                }
                if (currentSymbol != ' ') {
                    writer.write(' ');
                }
            } else {
                if (currentSymbol == '}') {
                    context.decreaseOffset();
                }
                if (currentSymbol != '\n') {
                    writer.write('\n');
                    writer.write(context.getOffset());
                }
            }
            writer.write(currentSymbol);
            stateMachine.setActiveStateBySymbol(currentSymbol);
        } catch (WriterException | ContextException e) {
            throw new StateException(e);
        }
    }
}
