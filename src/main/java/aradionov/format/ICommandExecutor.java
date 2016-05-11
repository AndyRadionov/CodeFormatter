package aradionov.format;

/**
 * @author Andrey Radionov andyomsk@gmail.com
 */
public interface ICommandExecutor {
    void execute(char symbol, IFormat format, StringBuilder lineBuilder, StringBuilder resultBuilder) throws FormatException;
}
