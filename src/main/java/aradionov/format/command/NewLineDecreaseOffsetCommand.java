package aradionov.format.command;

import aradionov.format.FormatException;
import aradionov.format.IFormat;

/**
 * @author Andrey Radionov andyomsk@gmail.com
 */
public class NewLineDecreaseOffsetCommand implements ICommand {
    @Override
    public void execute(char symbol, IFormat format, StringBuilder lineBuilder, StringBuilder resultBuilder) throws FormatException {
        format.decreaseOffset();
        resultBuilder
                .append(format.getOffset())
                .append(lineBuilder)
                .append(symbol)
                .append(format.getNewLineSymbol());
        lineBuilder.setLength(0);
    }
}
