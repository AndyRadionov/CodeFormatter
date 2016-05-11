package aradionov.format;

import aradionov.format.command.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Andrey Radionov andyomsk@gmail.com
 */
public class JavaCommandExecutor implements ICommandExecutor {
    private Map<Character, ICommand> allCommands = new HashMap<>();
    private RegularSymbolCommand alphaNumericSymbolCommand = new RegularSymbolCommand();


    public void execute(char symbol, IFormat format, StringBuilder lineBuilder, StringBuilder resultBuilder) throws FormatException {
        if (allCommands.containsKey(symbol)) {
            allCommands.get(symbol).execute(symbol, format, lineBuilder, resultBuilder);
        } else {
            alphaNumericSymbolCommand.execute(symbol, format, lineBuilder, resultBuilder);
        }
    }

    public JavaCommandExecutor() {
        loadProperties();
    }

    private void loadProperties() {
        allCommands.put('+', new SurroundSymbolWithSpaceCommand());
        allCommands.put('-', allCommands.get('+'));
        allCommands.put('/', allCommands.get('+'));
        allCommands.put('*', allCommands.get('+'));
        allCommands.put('%', allCommands.get('+'));
        allCommands.put('^', allCommands.get('+'));
        allCommands.put('=', allCommands.get('+'));

        allCommands.put('{', new NewLineIncreaseOffsetCommand());
        allCommands.put('}', new NewLineDecreaseOffsetCommand());
        allCommands.put(';', new NewLineCommand());
        allCommands.put('(', new RoundBracketsOpenCommand());
        allCommands.put(')', new RoundBracketsCloseCommand());

        allCommands.put(' ', new SpaceSymbolCommand());
    }
}
