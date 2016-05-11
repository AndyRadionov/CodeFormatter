package aradionov.format.command;

import aradionov.format.FormatException;
import aradionov.format.IFormat;

/**
 * @author Andrey Radionov andyomsk@gmail.com
 */
public interface ICommand {
    void execute(char symbol, IFormat format, StringBuilder lineBuilder, StringBuilder resultBuilder) throws FormatException;
}
