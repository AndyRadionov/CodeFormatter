package aradionov.format.command;

import aradionov.format.FormatException;
import aradionov.format.IFormat;

/**
 * @author Andrey Radionov andyomsk@gmail.com
 */
public class NewLineCommand implements ICommand {
    @Override
    public void execute(char symbol, IFormat format, StringBuilder lineBuilder, StringBuilder resultBuilder) throws FormatException {
        lineBuilder.append(symbol);
        if (format.getRoundBracketsLevel() == 0) {
            checkOperator(lineBuilder, format);
            resultBuilder
                    .append(format.getOffset())
                    .append(lineBuilder)
                    .append(format.getNewLineSymbol());
            lineBuilder.setLength(0);
        }
    }

    private void checkOperator(StringBuilder sb, IFormat format) throws FormatException {
        for (String operator : format.getOperators()) {
            if (sb.indexOf(operator) == 0) {
                format.increaseOffset();
                sb.insert(sb.indexOf(")") + 2, "{" + format.getNewLineSymbol() + format.getOffset())
                        .append(format.getNewLineSymbol());
                format.decreaseOffset();
                sb.append(format.getOffset())
                        .append("}");
                break;
            }
        }
    }
}
