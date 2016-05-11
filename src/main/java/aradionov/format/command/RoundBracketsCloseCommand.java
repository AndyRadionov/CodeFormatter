package aradionov.format.command;

import aradionov.format.IFormat;

/**
 * @author Andrey Radionov andyomsk@gmail.com
 */
public class RoundBracketsCloseCommand implements ICommand {
    @Override
    public void execute(char symbol, IFormat format, StringBuilder lineBuilder, StringBuilder resultBuilder) {
        format.increaseRoundBracketsLevel();
        lineBuilder
                .append(symbol)
                .append(format.getSpaceSymbol());
    }
}
