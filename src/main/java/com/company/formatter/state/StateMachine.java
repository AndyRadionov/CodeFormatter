package com.company.formatter.state;

import com.company.formatter.context.IContext;
import com.company.formatter.format.IFormat;
import com.company.formatter.state.concretestates.BraceCloseState;
import com.company.formatter.state.concretestates.BraceOpenState;
import com.company.formatter.state.concretestates.EndLineState;
import com.company.formatter.state.concretestates.IState;
import com.company.formatter.state.concretestates.InsideStringLiteralState;
import com.company.formatter.state.concretestates.NewLineState;
import com.company.formatter.state.concretestates.ParenthesisCloseState;
import com.company.formatter.state.concretestates.ParenthesisOpenState;
import com.company.formatter.state.concretestates.RegularSymbolState;
import com.company.formatter.state.concretestates.SpecialSymbolState;
import com.company.formatter.state.concretestates.StartOrSpaceState;
import com.company.writer.IWriter;

import java.util.HashMap;
import java.util.Map;

/**
 * Class implementing State design pattern.
 * Concrete formatter states formatting incoming symbols.
 */
public class StateMachine implements IStateMachine {

    private Map<Character, IState> formatStates;
    private RegularSymbolState regularSymbolState;
    private IState activeState;


    /**
     * Constructs new StateMachine with Start state.
     *
     * @param format contains properties for Formatter
     */
    public StateMachine(final IFormat format) {
        fillFormatState(format);
        regularSymbolState = new RegularSymbolState();
        activeState = formatStates.get(' ');
    }

    /**
     * Formatting symbols from reader and writes them to writer.
     *
     * @param symbol  Symbol to format and write
     * @param writer  Writer to write symbols
     * @param context current formatter context
     * @throws StateException if exception occurs
     */
    public void writeFormattedSymbol(final char symbol, final IWriter writer, final IContext context) throws StateException {
        activeState.writeFormattedSymbol(symbol, writer, context, this);
    }

    /**
     * Setting active state of StateMachine by given symbol.
     *
     * @param symbol fetched from reader
     */
    public void setActiveStateBySymbol(final char symbol) {
        if (formatStates.containsKey(symbol)) {
            activeState = formatStates.get(symbol);
        } else {
            activeState = regularSymbolState;
        }
    }

    /**
     * Utility method for filling formatStates map.
     *
     * @param format contains special symbols
     */
    private void fillFormatState(final IFormat format) {
        formatStates = new HashMap<>();
        formatStates.put('{', new BraceOpenState());
        formatStates.put('}', new BraceCloseState());
        formatStates.put(';', new EndLineState());
        formatStates.put('\n', new NewLineState());
        formatStates.put('(', new ParenthesisOpenState());
        formatStates.put(')', new ParenthesisCloseState());
        formatStates.put(' ', new StartOrSpaceState());
        formatStates.put('\"', new InsideStringLiteralState());
        for (Character c : format.getSpecialSymbols()) {
            SpecialSymbolState specialSymbolState = new SpecialSymbolState();
            formatStates.put(c, specialSymbolState);
        }
    }
}
