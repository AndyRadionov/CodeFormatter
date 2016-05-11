package aradionov.format.command;

import aradionov.format.IFormat;

/**
 * @author Andrey Radionov andyomsk@gmail.com
 */
public class RegularSymbolCommand implements ICommand {
    @Override
    public void execute(char symbol, IFormat format, StringBuilder lineBuilder, StringBuilder resultBuilder) {
        if (!Character.isWhitespace(symbol)) {
            lineBuilder.append(symbol);
        }
    }
}
