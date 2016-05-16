package com.company.formatter.state.concretestates;

import com.company.formatter.context.IContext;
import com.company.formatter.state.IStateMachine;
import com.company.formatter.state.StateException;
import com.company.writer.IWriter;

/**
 * Interface for formatter StateMachine state.
 */
public interface IState {
    /**
     * Formatting symbols from reader and writes them to writer.
     * Switching formatter StateMachine state.
     *
     * @param symbol       to Format and write
     * @param writer       to write symbols
     * @param context      current formatter context
     * @param stateMachine formatter stateMachine
     * @throws StateException if exception occurs
     */
    void writeFormattedSymbol(char symbol, IWriter writer, IContext context, IStateMachine stateMachine) throws StateException;
}
