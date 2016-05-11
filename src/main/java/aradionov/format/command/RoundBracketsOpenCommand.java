package aradionov.format.command;

import aradionov.format.IFormat;

/**
 * @author Andrey Radionov andyomsk@gmail.com
 */
public class RoundBracketsOpenCommand implements ICommand {
    @Override
    public void execute(char symbol, IFormat format, StringBuilder lineBuilder, StringBuilder resultBuilder) {
        format.decreaseRoundBracketsLevel();
        lineBuilder
                .append(format.getSpaceSymbol())
                .append(symbol);
    }
}
